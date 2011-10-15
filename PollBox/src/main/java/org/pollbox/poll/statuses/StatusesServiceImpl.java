package org.pollbox.poll.statuses;

import java.util.Collection;

import org.pollbox.poll.managers.DaoManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StatusesServiceImpl implements StatusesService {
    @Autowired(required = true)
    DaoManager daoManager;

    @Override
    @Transactional(readOnly = true)
    public Status getStatusById(Long id) {
        return this.daoManager.getStatusDao().getById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Status getStatus(String name, String type) {
        return this.daoManager.getStatusDao().getStatus(name, type);
    }

    @Override
    @Transactional(readOnly = true)
    public StatusList getStatuses() {
        Collection<Status> statuses = this.daoManager.getStatusDao().getStatuses();
        
        return new StatusList(statuses);
    }

    @Override
    @Transactional
    public Status save(Status status) {
        return this.daoManager.getStatusDao().save(status);
    }

    @Override
    @Transactional
    public void delete(Status status) {
        this.daoManager.getStatusDao().delete(status);
    }
}
