package com.cts.employee.runner;

import com.cts.employee.dao.EmployeeHibernateDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class AppRunner implements CommandLineRunner {

    private final EmployeeHibernateDao employeeHibernateDao;

    @Override
    public void run(String... args) {
        log.info("=== Hibernate Features Demo Started ===");

        log.info("--- Feature 1: Hibernate Properties Configured (application.properties) ---");
        log.info("--- Feature 2: Batch Insert of 1000 Employees ---");
        employeeHibernateDao.batchInsertEmployees();

        log.info("--- Feature 3: Second-Level Cache configured with EhCache ---");
        log.info("--- Feature 4: Audit Interceptor registered for logging ---");

        log.info("=== Hibernate Features Demo Completed ===");
    }
}
