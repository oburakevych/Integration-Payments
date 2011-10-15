package org.pollbox.poll.statuses;

import java.util.Collection;


public interface StatusDao {
    public Status getById(Long id);
    
    public Status getStatus(String name, String type);

    public Collection<Status> getStatuses();

    public Status save(Status status);

    public void delete(Status status);

}
