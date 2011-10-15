package org.pollbox.poll.statuses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.pollbox.conf.language.Language;
import org.pollbox.conf.language.LanguageDef;


public enum StatusDef {
    INITIAL_ACCOUNT(1100L, "INITIAL", "Created, but not activated yet. Limited functionality available.", Type.ACCOUNT),
    ACTIVE_ACCOUNT(1200L, "ACTIVE", "Active and fully functionable. Full functionality available.", Type.ACCOUNT),
    SUSPENDED_ACCOUNT(1300L, "SUSPENDED", "Suspended for any reason. Limited functionality available.", Type.ACCOUNT),
    BLOCKED_ACCOUNT(1400L, "BLOCKED", "Blocked for any reason. Limited functionality available.", Type.ACCOUNT),
    CLOSING_PENDING_ACCOUNT(1500L, "CLOSING_PENDING", "Scheduled for closing. Limited functionality available.", Type.ACCOUNT),
    CLOSED_ACCOUNT(1600L, "CLOSED", "Closed, not accessable.", Type.ACCOUNT),
    
    ACTIVE_PROJECT(2200L, "ACTIVE", "Active and fully functionable. Full functionality available.", Type.PROJECT),
    INACTIVE_PROJECT(2300L, "INACTIVE", "Suspended for any reason. Limited functionality available.", Type.PROJECT),
    CLOSED_PROJECT(2600L, "CLOSED", "Closed, not accessable.", Type.PROJECT),
    
    ACTIVE_POLL(3200L, "ACTIVE", "Active and fully functionable. Full functionality available.", Type.POLL),
    INACTIVE_POLL(3300L, "INACTIVE", "Suspended for any reason. Limited functionality available.", Type.POLL),
    CLOSED_POLL(3600L, "CLOSED", "Closed, not accessable.", Type.POLL);
    
    private final static Map<Long, StatusDef> itemMap = new HashMap<Long, StatusDef>();
    
    static {
        for(StatusDef item : values()) {
            itemMap.put(item.getId(), item);
        }
    }
    
    private Long id;
    private String name;
    private String description;
    private Type type;
    
    private StatusDef(Long id, String name, String description, Type type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }
    
    public static Collection<StatusDef> getStatuses() {
        return itemMap.values();
    }
    
    public static Collection<StatusDef> getStatuses(Type type) {
        Collection<StatusDef> resultStatuses = new ArrayList<StatusDef>();
        
        Collection<StatusDef> statuses = getStatuses();
        
        for(StatusDef status : statuses) {
            if (status.getType() == type) {
                resultStatuses.add(status);
            }
        }
        
        return resultStatuses;    
    }
    
    public static StatusDef getStatusDef(Long id) {
        StatusDef item = itemMap.get(id);
        
        if (item == null) {
            throw new IllegalArgumentException("No enum constant of class " + StatusDef.class.getSimpleName() + " with id " + id);
        }
        
        return item;
    }
    
    public static Status getStatus(Long id) {
        StatusDef statusDef = getStatusDef(id);
        
        Status status = null;
        
        if (statusDef != null) {
            status = new Status(statusDef);    
        }
        
        return status;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }    
}
