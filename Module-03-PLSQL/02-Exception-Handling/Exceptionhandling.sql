-- Scenario 1: Safe Fund Transfer
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_from IN NUMBER,
    p_to IN NUMBER,
    p_amount IN NUMBER
) AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_from;

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001, 'Insufficient funds');
    END IF;

    UPDATE Accounts SET Balance = Balance - p_amount WHERE AccountID = p_from;
    UPDATE Accounts SET Balance = Balance + p_amount WHERE AccountID = p_to;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer completed');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- Scenario 2: Update Salary
CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_emp_id IN NUMBER,
    p_percent IN NUMBER
) AS
BEGIN
    UPDATE Employees
    SET Salary = Salary * (1 + p_percent / 100)
    WHERE EmployeeID = p_emp_id;

    IF SQL%ROWCOUNT = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Employee not found');
    END IF;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Salary updated');
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: ' || SQLERRM);
END;
/

-- Scenario 3: Add New Customer
CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_id IN NUMBER,
    p_name IN VARCHAR2,
    p_dob IN DATE,
    p_balance IN NUMBER
) AS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer added');
EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Customer ID already exists');
END;
/