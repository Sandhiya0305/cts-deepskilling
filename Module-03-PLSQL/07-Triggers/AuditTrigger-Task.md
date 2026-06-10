# Task Description

Create a database trigger for audit logging. Create an AUDIT_LOG table with columns: log_id, table_name, operation, old_values, new_values, changed_by, changed_at. Create a trigger that fires AFTER UPDATE on the EMPLOYEES table. For each row updated, insert a record into AUDIT_LOG capturing the old and new values of salary and department_id, the current user (USER), and SYSTIMESTAMP. Show row-level trigger syntax (FOR EACH ROW) and how to access :OLD and :NEW pseudo-records. Also create a BEFORE INSERT trigger that validates salary is positive.
