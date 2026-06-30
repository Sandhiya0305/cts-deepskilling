package com.cts.employee.audit;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.event.spi.PostInsertEventListener;
import org.hibernate.persister.entity.EntityPersister;

@Slf4j
public class AuditPostInsertListener implements PostInsertEventListener {

    @Override
    public void onPostInsert(PostInsertEvent event) {
        log.info("[AUDIT] CREATE - Entity: {}, ID: {}",
                event.getEntity().getClass().getSimpleName(), event.getId());
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }
}
