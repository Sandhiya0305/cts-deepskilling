-- Scenario 1: Process Monthly Interest
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
BEGIN
    FOR rec IN (SELECT AccountID, Balance FROM Accounts WHERE AccountType = 'Savings') LOOP
        UPDATE Accounts
        SET Balance = Balance * 1.01,
            LastModified = SYSDATE
        WHERE AccountID = rec.AccountID;
        DBMS_OUTPUT.PUT_LINE('Account ' || rec.AccountID || ': $' || rec.Balance || ' -> $' || rec.Balance * 1.01);
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Update Employee Bonus
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_dept IN VARCHAR2,
    p_bonus IN NUMBER
) AS
BEGIN
    FOR rec IN (SELECT EmployeeID, Name, Salary FROM Employees WHERE Department = p_dept) LOOP
        UPDATE Employees
        SET Salary = Salary * (1 + p_bonus / 100)
        WHERE EmployeeID = rec.EmployeeID;
        DBMS_OUTPUT.PUT_LINE(rec.Name || ': $' || rec.Salary || ' -> $' || rec.Salary * (1 + p_bonus / 100));
    END LOOP;
    COMMIT;
END;
/

-- Scenario 3: Transfer Funds
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_from IN NUMBER,
    p_to IN NUMBER,
    p_amount IN NUMBER
) AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20003, 'Insufficient balance');
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transferred $' || p_amount);
END;
/