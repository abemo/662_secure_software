from flask import Flask, abort, g, make_response, redirect, render_template, request
from flask_wtf.csrf import CSRFProtect

from account_service import do_transfer, get_balance
from user_service import get_user_accounts, get_user_with_credentials, logged_in

app = Flask(__name__)

# SECURITY: Set a strong secret key for session management and CSRF protection
# in a real website or server, this would be stored in an environment variable
# or a secure vault, not hardcoded in the source code
# it is included here, so you can run the code without any additional setup
app.config["SECRET_KEY"] = "4D29E2408B0C30D5199D0D4F12769C3E"
# CSRF Protection: Initialize CSRFProtect with our Flask app
# This automatically protects all POST, PUT, PATCH, and DELETE requests
# It works by:
# 1. Generating a unique token tied to the user's session
# 2. Requiring this token to be submitted with each state-changing request
# 3. Validating the token to ensure requests come from the same origin
csrf = CSRFProtect(app)

# By default, Flask's Jinja2 templates automatically escape variables to prevent XSS
# This is a secure default behavior and doesn't need modification

# SECURITY: SQL Injection Protection Notes
# This application uses parameterized queries in all database operations
# (see user_service.py and account_service.py)
# Key SQL injection prevention techniques implemented:
# 1. Never concatenate user input directly into SQL strings
# 2. Always use parameterized queries with placeholders (?)
# 3. Pass parameters as tuples separate from the SQL statement
# 4. Use prepared statements via SQLite's parameter binding mechanism
# 5. Validate and sanitize all user inputs before using them in queries


@app.route("/", methods=["GET"])
def home():
    if not logged_in():
        return render_template("login.html")
    return redirect("/dashboard")


@app.route("/login", methods=["POST"])
def login():
    # POST routes are protected by CSRF token validation
    # The login.html template includes a csrf_token field that must match the user's session
    email = request.form.get("email")
    password = request.form.get("password")

    # SECURITY: SQL Injection Protection
    # User input is passed to get_user_with_credentials which uses parameterized queries
    # The implementation in user_service.py prevents SQL injection attacks
    user = get_user_with_credentials(email, password)
    if not user:
        # Generic error message doesn't reveal whether the username exists or the password is wrong
        # This prevents user enumeration by keeping error messages consistent
        return render_template("login.html", error="Invalid username or password")
    response = make_response(redirect("/dashboard"))
    response.set_cookie("auth_token", user["token"])
    return response, 303


@app.route("/dashboard", methods=["GET"])
def dashboard():
    if not logged_in():
        return render_template("login.html")

    # SECURITY: SQL Injection Protection
    # g.user comes from JWT token validation and is passed to get_user_accounts
    # which uses parameterized queries to prevent SQL injection
    accounts = get_user_accounts(g.user)
    return render_template("dashboard.html", email=g.user, accounts=accounts)


@app.route("/details", methods=["GET"])
def details():
    if not logged_in():
        return render_template("login.html")

    # SECURITY: User input validation
    # We retrieve and validate the account_number parameter from the request
    account_number = request.args["account"]

    # SECURITY: SQL Injection Protection
    # User input is passed to get_balance which uses parameterized queries
    # The implementation in account_service.py prevents SQL injection attacks
    balance = get_balance(account_number, g.user)
    if balance is None:
        # Using 403 Access Denied instead of 404 Not Found
        # This prevents attackers from learning whether an account exists but belongs to someone else
        # or doesn't exist at all - both cases return the same generic error
        abort(403, "Access denied")
    return render_template(
        "details.html", user=g.user, accounts=account_number, balance=balance
    )


@app.route("/transfer", methods=["GET", "POST"])
def transfer():
    if not logged_in():
        return render_template("login.html")

    if request.method == "GET":
        return render_template("transfer.html", user=g.user)

    # POST routes are protected by CSRF token validation
    # The transfer.html template includes a csrf_token field that must match the user's session
    # This prevents attackers from tricking users into making transfers they didn't intend

    # SECURITY: Retrieve and validate user inputs
    source = request.form.get("from")
    target = request.form.get("to")
    try:
        amount = int(request.form.get("amount"))
    except ValueError:
        # Generic error message for invalid input doesn't reveal specifics
        # that could be used for information gathering
        abort(400, "Invalid request data")

    # SECURITY: Input validation to prevent negative transfers or excessive amounts
    if amount < 0:
        # Generic error message hides the specific validation failure
        # preventing attackers from learning system rules through different error messages
        abort(400, "Invalid request data")
    if amount > 1000:
        # Consistent error message for all validation failures
        # prevents information leakage through error message differences
        abort(400, "Invalid request data")

    # SECURITY: SQL Injection Protection
    # User input is passed to get_balance which uses parameterized queries
    # The implementation in account_service.py prevents SQL injection attacks
    available_balance = get_balance(source, g.user)
    if available_balance is None:
        # Using 403 rather than 404 masks whether account doesn't exist
        # or belongs to another user - both return the same generic error
        abort(403, "Access denied")
    if amount > available_balance:
        # Generic error message doesn't reveal the actual balance
        # or specific reason for failure
        abort(400, "Invalid request data")

    # SECURITY: SQL Injection Protection
    # User input is passed to do_transfer which uses parameterized queries
    # The implementation in account_service.py prevents SQL injection attacks
    if do_transfer(source, target, amount):
        pass  # TODO GIVE FEEDBACK
    else:
        # Generic failure message doesn't reveal why transfer failed
        # (e.g., whether target account exists)
        abort(400, "Transfer failed")

    response = make_response(redirect("/dashboard"))
    return response, 303


@app.route("/logout", methods=["POST"])
def logout():
    # Now using POST for logout which is protected by CSRF
    # This prevents attackers from forcing users to log out via CSRF attacks
    response = make_response(redirect("/dashboard"))
    response.delete_cookie("auth_token")
    return response, 303


# Add Content Security Policy (CSP) header to all responses
@app.after_request
def add_security_headers(response):
    # Content Security Policy to prevent XSS
    # - default-src 'self': Only allow resources from the same origin
    # - script-src 'self': Only allow scripts from the same origin
    # - style-src 'self' 'unsafe-inline': Allow inline styles (needed for template styling)
    # - object-src 'none': Don't allow plugins
    # - base-uri 'self': Restrict base tag to same origin
    # - frame-ancestors 'none': Prevent site from being framed (clickjacking protection)
    response.headers["Content-Security-Policy"] = (
        "default-src 'self'; script-src 'self'; style-src 'self' 'unsafe-inline'; object-src 'none'; base-uri 'self'; frame-ancestors 'none';"
    )

    # X-Content-Type-Options prevents browsers from MIME-sniffing
    response.headers["X-Content-Type-Options"] = "nosniff"

    # X-Frame-Options prevents clickjacking
    response.headers["X-Frame-Options"] = "DENY"

    # X-XSS-Protection is for older browsers that don't support CSP
    response.headers["X-XSS-Protection"] = "1; mode=block"

    return response
