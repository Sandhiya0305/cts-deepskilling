package com.cognizant.springlearn;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DepartmentController {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DepartmentController.class);

    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/departments")
    public ArrayList<Department> getAllDepartments() {
        LOGGER.info("START");
        ArrayList<Department> departments = departmentService.getAllDepartments();
        LOGGER.info("END");
        return departments;
    }
}