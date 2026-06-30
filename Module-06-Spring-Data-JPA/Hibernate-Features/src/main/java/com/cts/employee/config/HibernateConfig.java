package com.cts.employee.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class HibernateConfig {
    // Interceptor registered via Hibernate Integrator (META-INF/services)
    // See AuditIntegrator.java for event listener registration
}
