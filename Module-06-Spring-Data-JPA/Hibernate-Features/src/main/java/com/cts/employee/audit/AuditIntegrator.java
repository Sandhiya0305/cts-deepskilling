package com.cts.employee.audit;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;

@Slf4j
public class AuditIntegrator implements Integrator {

    @Override
    public void integrate(Metadata metadata, SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        EventListenerRegistry registry = serviceRegistry
                .getService(EventListenerRegistry.class);

        registry.appendListeners(EventType.POST_INSERT, new AuditPostInsertListener());
        registry.appendListeners(EventType.POST_UPDATE, new AuditPostUpdateListener());

        log.info("[AUDIT] Hibernate Audit Event Listeners registered successfully");
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // cleanup if needed
    }
}
