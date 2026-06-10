# Task Description

Create a complete service layer for REST CRUD operations. Methods: createEmployee with duplicate email validation, getAllEmployees with optional filtering, getEmployeeById returning Optional or throwing custom exception, updateEmployee with optimistic locking (@Version field), deleteEmployee with soft delete (set active=false instead of physical delete). Include pagination support in getAllEmployees. Add @Transactional on class level. Use constructor injection for repositories. Include logging of operations using SLF4J.
