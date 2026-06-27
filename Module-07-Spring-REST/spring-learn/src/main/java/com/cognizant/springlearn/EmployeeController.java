package com.cognizant.springlearn;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String root() {
        return "Available endpoints: GET /employees, GET /departments";
    }

    @GetMapping("/employees")
    public ArrayList<Employee> getAllEmployees() {
        LOGGER.info("START");
        ArrayList<Employee> employees = employeeService.getAllEmployees();
        LOGGER.info("END");
        return employees;
    }
}