# Task Description

Create a complete Spring Boot REST controller for Employee resource. Use @RestController, @RequestMapping(""/api/employees""). Implement endpoints: GET /api/employees (return all employees, 200 OK), GET /api/employees/{id} (return single employee, 200 OK or 404 Not Found), POST /api/employees (create employee, 201 Created with Location header), PUT /api/employees/{id} (full update, 200 OK), PATCH /api/employees/{id} (partial update), DELETE /api/employees/{id} (204 No Content). Use ResponseEntity for precise HTTP control. Include @Valid for request body validation.
