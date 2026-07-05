package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);
    private static List<Employee> EMPLOYEE_LIST;

    public EmployeeDao() {
        LOGGER.info("START");
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        EMPLOYEE_LIST = context.getBean("employeeList", List.class);
        LOGGER.info("END");
    }

    public List<Employee> getAllEmployees() {
        LOGGER.info("START");
        LOGGER.info("END");
        return EMPLOYEE_LIST;
    }

    public Employee getEmployee(int id) {
        LOGGER.info("START");
        Employee employee = EMPLOYEE_LIST.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);
        LOGGER.info("END");
        return employee;
    }

    public void addEmployee(Employee employee) {
        LOGGER.info("START");
        EMPLOYEE_LIST.add(employee);
        LOGGER.info("END");
    }

    public void updateEmployee(Employee employee) {
        LOGGER.info("START");
        for (int i = 0; i < EMPLOYEE_LIST.size(); i++) {
            if (EMPLOYEE_LIST.get(i).getId() == employee.getId()) {
                EMPLOYEE_LIST.set(i, employee);
                break;
            }
        }
        LOGGER.info("END");
    }

    public void deleteEmployee(int id) {
        LOGGER.info("START");
        EMPLOYEE_LIST.removeIf(e -> e.getId() == id);
        LOGGER.info("END");
    }
}
