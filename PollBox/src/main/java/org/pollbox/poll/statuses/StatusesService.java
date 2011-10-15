package org.pollbox.poll.statuses;


public interface StatusesService {
    public Status getStatusById(Long id);
    
    public Status getStatus(String name, String type);

    public StatusList getStatuses();
    
    public Status save(Status status);
    
    public void delete(Status status);   
}
