package com.cognizant.springlearn;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {
    private static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(DepartmentService.class);

    @Autowired
    private DepartmentDao departmentDao;

    public ArrayList<Department> getAllDepartments() {
        return departmentDao.getAllDepartments();
    }
}