package com.cognizant.springlearn;

import java.util.ArrayList;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DepartmentDao.class);
    private static ArrayList<Department> DEPARTMENT_LIST;

    public DepartmentDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("employee.xml");
        DEPARTMENT_LIST = (ArrayList<Department>) context.getBean("departmentList");
    }

    public ArrayList<Department> getAllDepartments() {
        return DEPARTMENT_LIST;
    }
}