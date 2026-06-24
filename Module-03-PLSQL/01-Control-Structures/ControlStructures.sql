-- Scenario 1: Senior Citizen Discount
BEGIN
    FOR rec IN (SELECT CustomerID, Name, DOB FROM Customers) LOOP
        IF MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12 > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - 1
            WHERE CustomerID = rec.CustomerID;
            DBMS_OUTPUT.PUT_LINE(rec.Name || ' received 1% discount');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: VIP Status
BEGIN
    FOR rec IN (SELECT CustomerID, Name, Balance FROM Customers) LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET IsVIP = 1
            WHERE CustomerID = rec.CustomerID;
            DBMS_OUTPUT.PUT_LINE(rec.Name || ' is now VIP');
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Loan Due Reminders
BEGIN
    FOR rec IN (
        SELECT c.Name, l.LoanID, l.EndDate, l.LoanAmount
        FROM Loans l
        JOIN Customers c ON l.CustomerID = c.CustomerID
        WHERE l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
    ) LOOP
        DBMS_OUTPUT.PUT_LINE('Reminder: ' || rec.Name || 
            ' - Loan ' || rec.LoanID || ' due on ' || rec.EndDate ||
            ' - Amount: $' || rec.LoanAmount);
    END LOOP;
END;
/