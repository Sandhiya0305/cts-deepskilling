package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllEmployees();
        LOGGER.info("End");
        return employees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        Employee employee = employeeService.getEmployee(id);
        if (employee == null) {
            throw new EmployeeNotFoundException("Employee not found");
        }
        LOGGER.info("End");
        return employee;
    }

    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody @Valid Employee employee) {
        LOGGER.info("Start");
        LOGGER.debug("Employee: {}", employee);
        employeeService.addEmployee(employee);
        LOGGER.info("End");
        return employee;
    }

    @PutMapping("/employees")
    public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeService.updateEmployee(employee);
        LOGGER.info("End");
    }

    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeService.deleteEmployee(id);
        LOGGER.info("End");
    }
}
