package org.integration.connectors.dropbox.account;

import java.util.UUID;

import org.integration.payments.server.ws.dropbox.DropboxApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropboxAccountService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    private DropboxApiService apiService;
    
    public DropboxUserProfile getUserProfile(UUID companyAccountId) {
        DropboxUserProfile userProfile = apiService.getUserProfile(companyAccountId);
        
        log.debug("User profile received {} for Account {}", userProfile, companyAccountId);
        
        return userProfile;
    }

    public DropboxApiService getApiService() {
        return apiService;
    }

    public void setApiService(DropboxApiService apiService) {
        this.apiService = apiService;
    }
}
