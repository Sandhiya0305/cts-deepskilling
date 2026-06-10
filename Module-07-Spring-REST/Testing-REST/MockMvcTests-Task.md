# Task Description

Write comprehensive MockMvc tests for REST endpoints. Test: GET /api/employees with @WithMockUser, POST /api/employees with JSON content and contentType(MediaType.APPLICATION_JSON), PUT /api/employees/{id}, DELETE /api/employees/{id}. Use @SpringBootTest with @AutoConfigureMockMvc. Mock service layer with @MockBean. Use ObjectMapper to convert objects to JSON. Test security: accessing protected endpoint without token returns 401, with valid token returns 200. Include test for validation errors returning 400 with field errors.
