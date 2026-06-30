package com.cts.employee.audit;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.spi.PostUpdateEvent;
import org.hibernate.event.spi.PostUpdateEventListener;
import org.hibernate.persister.entity.EntityPersister;

@Slf4j
public class AuditPostUpdateListener implements PostUpdateEventListener {

    @Override
    public void onPostUpdate(PostUpdateEvent event) {
        log.info("[AUDIT] UPDATE - Entity: {}, ID: {}",
                event.getEntity().getClass().getSimpleName(), event.getId());
    }

    @Override
    public boolean requiresPostCommitHandling(EntityPersister persister) {
        return false;
    }
}
