# Simple Bank Flask Application

This is a basic web application simulating online banking functionalities, built using Flask. It allows users to log in, view their accounts and balances, see transaction details, and transfer funds between accounts.

## Features

- User Authentication (Login/Logout)
- Account Dashboard
- View Account Balance/Details
- Fund Transfer

## Security Features Implemented

This application incorporates several security measures to protect against common web vulnerabilities:

- **Cross-Site Request Forgery (CSRF) Protection:** Uses `Flask-WTF` to generate and validate CSRF tokens for all forms submitting data (Login, Transfer, Logout), ensuring requests originate from the application itself.
- **Cross-Site Scripting (XSS) Prevention:**
  - Relies on Jinja2's default auto-escaping for rendering user data in templates.
  - Implements a strict **Content Security Policy (CSP)** via HTTP headers to restrict the sources of content (scripts, styles, etc.), mitigating XSS attacks.
- **SQL Injection Prevention:** All database queries are performed using **parameterized statements**. User input is never directly concatenated into SQL strings; instead, it's passed safely as parameters to the database driver, preventing malicious SQL execution.
- **Secure Authentication:**
  - Uses JSON Web Tokens (JWT) for managing user sessions via secure cookies.
  - Passwords are securely hashed using **PBKDF2 with SHA256**.
- **Timing Attack Resistance:** Login, balance retrieval, and transfer operations are designed to execute in **constant time** (using dummy operations and random delays) regardless of whether a user/account exists or credentials are valid. This prevents attackers from inferring sensitive information based on response time differences.
- **Secure Authorization:** Ensures users can only access their own account data. Attempts to access unauthorized resources result in a generic "Access Denied" (403) error, preventing information leakage about account existence.
- **Secure Headers:** Implements additional security headers:
  - `X-Content-Type-Options: nosniff`: Prevents browsers from MIME-sniffing responses away from the declared content type.
  - `X-Frame-Options: DENY`: Protects against clickjacking attacks by preventing the site from being embedded in frames.
  - `X-XSS-Protection: 1; mode=block`: Enables XSS filtering in older browsers that support this header.
- **Input Validation:** Basic validation is performed on user input (e.g., transfer amounts) to prevent unexpected behavior or potential abuse.
- **Generic Error Messages:** Uses non-specific error messages for failed operations (login, access denied, transfer failed) to avoid revealing internal system state or valid user/account information.
- **Secure Logout:** Logout functionality requires a POST request (protected by CSRF) to prevent attackers from logging users out maliciously.

## Running the Application (Placeholder)

_(Instructions on how to set up and run the project would go here)_

```bash
# Example setup commands
# python -m venv venv
# source venv/bin/activate # or venv\Scripts\activate on Windows
# pip install -r requirements.txt # Assuming a requirements file exists
# flask run
```

## Disclaimer

This is a demonstrative application. While it implements several security best practices, ensure thorough security reviews and testing before deploying any application handling sensitive financial data in a real-world scenario. The `SECRET_KEY` in `app.py` should be replaced with a strong, unique, and securely managed secret.
