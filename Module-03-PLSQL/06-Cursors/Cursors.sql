-- Scenario 1: Generate Monthly Statements
DECLARE
    CURSOR c_statements IS
        SELECT a.AccountID, c.Name, t.TransactionDate, t.Amount, t.TransactionType
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE EXTRACT(MONTH FROM t.TransactionDate) = EXTRACT(MONTH FROM SYSDATE)
        AND EXTRACT(YEAR FROM t.TransactionDate) = EXTRACT(YEAR FROM SYSDATE);
BEGIN
    FOR rec IN c_statements LOOP
        DBMS_OUTPUT.PUT_LINE('Account: ' || rec.AccountID || ' | Customer: ' || rec.Name);
        DBMS_OUTPUT.PUT_LINE('Date: ' || rec.TransactionDate || ' | ' || rec.TransactionType || ' | $' || rec.Amount);
    END LOOP;
END;
/

-- Scenario 2: Apply Annual Fee
DECLARE
    CURSOR c_accounts IS SELECT AccountID, Balance FROM Accounts;
    v_fee NUMBER := 50;
BEGIN
    FOR rec IN c_accounts LOOP
        UPDATE Accounts
        SET Balance = Balance - v_fee,
            LastModified = SYSDATE
        WHERE AccountID = rec.AccountID;
        DBMS_OUTPUT.PUT_LINE('Account ' || rec.AccountID || ': Fee $' || v_fee || ' applied');
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Update Loan Interest Rates
DECLARE
    CURSOR c_loans IS SELECT LoanID, InterestRate FROM Loans;
BEGIN
    FOR rec IN c_loans LOOP
        UPDATE Loans
        SET InterestRate = InterestRate + 0.5
        WHERE LoanID = rec.LoanID;
        DBMS_OUTPUT.PUT_LINE('Loan ' || rec.LoanID || ': Rate ' || rec.InterestRate || '% -> ' || (rec.InterestRate + 0.5) || '%');
    END LOOP;
    COMMIT;
END;
/