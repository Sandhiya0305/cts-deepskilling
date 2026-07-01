package com.cts.employee.service;

import com.cts.employee.entity.Employee;
import com.cts.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    @Override
    public void run(String... args) {
        if (employeeRepository.count() == 0) {
            List<Employee> employees = new ArrayList<>();
            String[] departments = {"Engineering", "Marketing", "Sales", "Finance", "HR"};

            for (int i = 1; i <= 50; i++) {
                employees.add(Employee.builder()
                        .name("Employee_" + i)
                        .email("employee" + i + "@cts.com")
                        .salary(30000.0 + (i % 20) * 2000)
                        .department(departments[i % departments.length])
                        .joinDate(LocalDate.of(2020 + (i % 4), 1 + (i % 12), 1 + (i % 28)))
                        .active(i % 5 != 0)
                        .build());
            }

            employeeRepository.saveAll(employees);
            log.info("Seeded 50 employees for pagination demo");
        }
    }
}
