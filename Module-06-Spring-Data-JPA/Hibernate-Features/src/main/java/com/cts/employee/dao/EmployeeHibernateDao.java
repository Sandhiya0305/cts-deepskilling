package com.cts.employee.dao;

import com.cts.employee.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Repository
public class EmployeeHibernateDao {

    private static final int BATCH_SIZE = 50;
    private static final int TOTAL_EMPLOYEES = 1000;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void batchInsertEmployees() {
        Session session = entityManager.unwrap(Session.class);
        long startTime = System.currentTimeMillis();

        log.info("Starting batch insert of {} employees with flush every {} records", TOTAL_EMPLOYEES, BATCH_SIZE);

        for (int i = 1; i <= TOTAL_EMPLOYEES; i++) {
            Employee employee = Employee.builder()
                    .name("Employee_" + i)
                    .email("employee" + i + "@cts.com")
                    .salary(30000.0 + (i % 100) * 500)
                    .department("Department_" + ((i % 10) + 1))
                    .active(true)
                    .build();

            session.persist(employee);

            if (i % BATCH_SIZE == 0) {
                log.info("Flushing at record {}", i);
                session.flush();
                session.clear();
            }
        }

        long endTime = System.currentTimeMillis();
        log.info("Batch insert completed. Total records: {}, Time taken: {}ms", TOTAL_EMPLOYEES, (endTime - startTime));
    }
}
