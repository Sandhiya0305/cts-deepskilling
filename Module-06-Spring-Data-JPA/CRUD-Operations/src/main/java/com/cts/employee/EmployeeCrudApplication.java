package com.cts.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmployeeCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeCrudApplication.class, args);
    }
}
