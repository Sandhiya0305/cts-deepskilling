package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public List<Employee> getAllEmployees() {
        LOGGER.info("START");
        LOGGER.info("END");
        return employeeDao.getAllEmployees();
    }

    @Transactional
    public Employee getEmployee(int id) {
        LOGGER.info("START");
        Employee employee = employeeDao.getEmployee(id);
        LOGGER.info("END");
        return employee;
    }

    @Transactional
    public void addEmployee(Employee employee) {
        LOGGER.info("START");
        employeeDao.addEmployee(employee);
        LOGGER.info("END");
    }

    @Transactional
    public void updateEmployee(Employee employee) {
        LOGGER.info("START");
        employeeDao.updateEmployee(employee);
        LOGGER.info("END");
    }

    @Transactional
    public void deleteEmployee(int id) {
        LOGGER.info("START");
        employeeDao.deleteEmployee(id);
        LOGGER.info("END");
    }
}
