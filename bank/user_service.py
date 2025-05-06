import datetime
import random
import sqlite3
import time

import jwt
from flask import g, request
from passlib.hash import pbkdf2_sha256

# in a real website or server, this would be stored in an environment variable
# or a secure vault, not hardcoded in the source code
# it is included here, so you can run the code without any additional setup
SECRET = "bfg28y7efg238re7r6t32gfo23vfy7237yibdyo238do2v3"


def get_user_with_credentials(email, password):
    start_time = time.time()
    result = None

    try:
        con = sqlite3.connect("bank.db")
        cur = con.cursor()

        # SECURITY: SQL Injection Protection
        # Using parameterized queries with placeholders (?) and parameters as a tuple
        # This ensures user inputs are properly escaped and treated as data, not executable SQL
        # The database driver handles the escaping, preventing attackers from injecting malicious SQL
        cur.execute(
            """
            SELECT email, name, password FROM users where email=?""",
            (email,),  # Parameters passed as a tuple, even for single values
        )
        row = cur.fetchone()

        # Perform a constant-time password verification even if user doesn't exist
        # This prevents timing attacks that could reveal whether a user exists or not
        if row is not None:
            email, name, hash = row
            if pbkdf2_sha256.verify(password, hash):
                result = {"email": email, "name": name,
                          "token": create_token(email)}
        else:
            # If user doesn't exist, still perform a hash verification to maintain constant time
            # This dummy verification uses a constant hash that will never match
            # Critical: This ensures authentication takes the same time whether a user exists or not,
            # preventing timing-based user enumeration attacks
            pbkdf2_sha256.verify(
                password,
                "$pbkdf2-sha256$29000$N2YMIWQsBWBMae09x1jrPQ$1t8iyB2A.WF/Z5JZv.lfCIhXXN33N23OSgQYThBYRfk",
            )
    finally:
        # SECURITY: Always close database connections to prevent resource exhaustion
        con.close()

    # Add random delay to ensure constant-time response
    # This additional randomized delay further masks any subtle timing differences
    # in processing that might leak information about user existence
    elapsed = time.time() - start_time
    if elapsed < 0.5:  # 500ms threshold
        time.sleep(random.uniform(0, 0.5 - elapsed))

    # Return None for non-existent users, which will be handled with generic error in app.py
    return result


def get_user_accounts(user) -> list:
    try:
        con = sqlite3.connect("bank.db")
        cur = con.cursor()

        # SECURITY: SQL Injection Protection
        # Using parameterized query with placeholder (?) and parameter as a tuple
        # This prevents SQL injection by ensuring the user parameter is treated as data, not code
        cur.execute(
            """
            SELECT id FROM accounts where owner=?""",
            (user,),  # Parameter passed as a tuple
        )
        rows = cur.fetchall()
        return [row[0] for row in rows]
    finally:
        # SECURITY: Always close database connections
        con.close()


def logged_in():
    token = request.cookies.get("auth_token")
    try:
        data = jwt.decode(token, SECRET, algorithms=["HS256"])
        g.user = data["sub"]
        return True
    except jwt.exceptions.InvalidTokenError:
        return False


def create_token(email):
    now = datetime.datetime.now(datetime.timezone.utc)
    payload = {"sub": email, "iat": now,
               "exp": now + datetime.timedelta(minutes=60)}
    token = jwt.encode(payload, SECRET, algorithm="HS256")
    return token
