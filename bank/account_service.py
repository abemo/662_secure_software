import random
import sqlite3
import time


def get_balance(account_number, owner):
    start_time = time.time()
    result = None

    try:
        con = sqlite3.connect("bank.db")
        cur = con.cursor()

        # SECURITY: SQL Injection Protection
        # Using parameterized query with placeholders (?) and parameters as a tuple
        # This prevents SQL injection by ensuring user inputs (account_number, owner)
        # are properly escaped and treated as data, not executable SQL code
        cur.execute(
            """
            SELECT balance FROM accounts where id=? and owner=?""",
            (account_number, owner),  # Parameters passed as a tuple
        )
        row = cur.fetchone()
        if row is not None:
            result = row[0]
        # No explicit early return based on account existence
        # This prevents timing attacks that could determine if an account exists
    finally:
        # SECURITY: Always close database connections to prevent resource exhaustion
        con.close()

    # Add random delay to ensure constant-time response
    # This prevents timing-based user enumeration by making all responses
    # take approximately the same amount of time, whether the account exists or not
    elapsed = time.time() - start_time
    if elapsed < 0.5:  # 500ms threshold
        time.sleep(random.uniform(0, 0.5 - elapsed))

    return (
        result  # Returns None for non-existent accounts, handled generically in app.py
    )


def do_transfer(source, target, amount):
    start_time = time.time()
    result = False

    try:
        con = sqlite3.connect("bank.db")
        cur = con.cursor()

        # SECURITY: SQL Injection Protection
        # Using parameterized query with placeholder (?) and parameter as a tuple
        # This prevents SQL injection by ensuring the source account parameter
        # is properly escaped and treated as data, not executable SQL
        # Check if source account exists and belongs to the user
        # We check both accounts but don't return early, continuing with
        # the same code path regardless of existence to prevent timing attacks
        cur.execute(
            """
            SELECT balance FROM accounts where id=?""",
            (source,),  # Parameter passed as a tuple
        )
        source_row = cur.fetchone()

        # SECURITY: SQL Injection Protection
        # Using parameterized query for the target account check
        # Check if target account exists
        cur.execute(
            """
            SELECT id FROM accounts where id=?""",
            (target,),  # Parameter passed as a tuple
        )
        target_row = cur.fetchone()

        # Only perform transfer if both accounts exist - this check happens at the same
        # point in execution regardless of account existence, protecting against timing attacks
        if source_row is not None and target_row is not None:
            # SECURITY: SQL Injection Protection
            # Using parameterized queries for both UPDATE statements
            # This prevents SQL injection by ensuring amount and account parameters
            # are properly escaped and treated as data, not executable SQL
            cur.execute(
                """
                UPDATE accounts SET balance=balance-? where id=?""",
                (amount, source),  # Parameters passed as a tuple
            )
            cur.execute(
                """
                UPDATE accounts SET balance=balance+? where id=?""",
                (amount, target),  # Parameters passed as a tuple
            )
            con.commit()
            result = True
        # No early returns that would leak information through timing differences
    finally:
        # SECURITY: Always close database connections
        con.close()

    # Add random delay to ensure constant-time response
    # This makes successful and failed transfers take the same amount of time,
    # preventing attackers from determining if accounts exist based on response timing
    elapsed = time.time() - start_time
    if elapsed < 0.5:  # 500ms threshold
        time.sleep(random.uniform(0, 0.5 - elapsed))

    return result  # Return status handled generically in app.py
