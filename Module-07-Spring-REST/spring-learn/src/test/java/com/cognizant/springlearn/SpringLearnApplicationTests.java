package com.cognizant.springlearn;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class SpringLearnApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private EmployeeController employeeController;

    @Autowired
    private DepartmentController departmentController;

    @Test
    void contextLoads() {
        assertNotNull(employeeController);
        assertNotNull(departmentController);
    }

    @Test
    void testGetAllEmployees() throws Exception {
        ResultActions actions = mvc.perform(get("/employees"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$").isArray());
        actions.andExpect(jsonPath("$[0].id").exists());
        actions.andExpect(jsonPath("$[0].name").exists());
    }

    @Test
    void testGetAllDepartments() throws Exception {
        ResultActions actions = mvc.perform(get("/departments"));
        actions.andExpect(status().isOk());
        actions.andExpect(jsonPath("$").isArray());
        actions.andExpect(jsonPath("$[0].id").exists());
        actions.andExpect(jsonPath("$[0].name").exists());
    }
}
