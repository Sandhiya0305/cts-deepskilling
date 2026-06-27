package com.cognizant.springlearn;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmployeeService {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public ArrayList<Employee> getAllEmployees() {
        LOGGER.info("START");
        ArrayList<Employee> employees = employeeDao.getAllEmployees();
        LOGGER.info("END");
        return employees;
    }
}