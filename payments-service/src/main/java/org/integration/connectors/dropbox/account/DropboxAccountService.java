package org.integration.connectors.dropbox.account;

import java.util.List;

import org.integration.payments.server.ws.dropbox.DropboxApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropboxAccountService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    private DropboxApiService apiService;
    private DropboxAccountDao accountDao;
    
    public DropboxAccount retrieveAccount(String companyAccountId) {
        DropboxAccount userProfile = apiService.getUserProfile(companyAccountId);
        
        log.debug("User profile received {} for Account {}", userProfile, companyAccountId);
        
        return userProfile;
    }
    
    public void saveAccount(DropboxAccount account) {
        accountDao.save(account);
    }
    
    public List<DropboxAccount> getAccounts(int limit) {
        log.debug("Getting the list of all available Dropbox accounts limited to {} entries", limit);
        return accountDao.getAccounts(limit);
    }

    public DropboxApiService getApiService() {
        return apiService;
    }

    public void setApiService(DropboxApiService apiService) {
        this.apiService = apiService;
    }
}
