package org.integration.payments.server.document;

import java.util.UUID;

public abstract class Document {
    private UUID id;
    private UUID companyAccountId;
    
    public abstract Object getContent();
    public abstract void setContent(Object content);

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
    public void setCompanyAccountId(UUID companyAccountId) {
        this.companyAccountId = companyAccountId;
    }
    public UUID getCompanyAccountId() {
        return companyAccountId;
    }
    
    
}
