# Task Description

Write Spring Boot WebMvcTest for a REST controller. Use @WebMvcTest(EmployeeController.class) and @Autowired MockMvc. Test endpoints: GET /api/employees returns HTTP 200 and JSON array, GET /api/employees/{id} returns single employee or 404 when not found, POST /api/employees returns 201 Created with Location header, PUT /api/employees/{id} returns updated employee, DELETE /api/employees/{id} returns 204 No Content. Mock the EmployeeService with @MockBean. Use MockMvcRequestBuilders and MockMvcResultMatchers for requests and assertions.
