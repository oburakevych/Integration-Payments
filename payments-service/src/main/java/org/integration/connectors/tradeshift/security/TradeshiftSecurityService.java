package org.integration.connectors.tradeshift.security;

import org.integration.connectors.SecurityService;
import org.integration.payments.server.ws.auth.CredentialsStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth1.OAuthToken;


public class TradeshiftSecurityService implements SecurityService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    private CredentialsStorage<OAuthToken> creadentialStorage;
    

    public TradeshiftSecurityService() {}
    
    public TradeshiftSecurityService(CredentialsStorage<OAuthToken> creadentialStorage) {
        this.creadentialStorage = creadentialStorage;
    }
    
    @Override
    public void save(String accountId, OAuthToken credentials) {
        creadentialStorage.save(accountId, credentials);
    }

    @Override
    public void update(String accountId, OAuthToken credentials) {
        creadentialStorage.save(accountId, credentials);
    }

    @Override
    public OAuthToken get(String accountId) {
        return creadentialStorage.get(accountId);
    }

    @Override
    public void delete(String accountId) {
        creadentialStorage.delete(accountId);
    }

    @Override
    public boolean exists(String accountId) {
        return creadentialStorage.exists(accountId);
    }
}
