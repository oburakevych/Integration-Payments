package org.integration.payments.server.ws.auth.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.integration.payments.server.ws.auth.CredentialsStorage;
import org.integration.payments.server.ws.auth.SecurityDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth1.OAuthToken;

public class DBOAuth1AccessCredentialsStorage implements CredentialsStorage<OAuthToken> {
    private static final int GET_ACCOUNT_LIMIT = 50;

    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    private SecurityDao securityDao;
    private int getAccountsLimit;

    public DBOAuth1AccessCredentialsStorage(SecurityDao securityDao) {
        this(securityDao, GET_ACCOUNT_LIMIT);
    }

    public DBOAuth1AccessCredentialsStorage(SecurityDao securityDao, int getAccountsLimit) {
        this.securityDao = securityDao;
        this.getAccountsLimit = getAccountsLimit;

        log.info("Initialized DBOAuth1AccessCredentialsStorage, getAccountsLimit:" + getAccountsLimit);
    }

    public DBOAuth1AccessCredentialsStorage(SecurityDao securityDao, int getAccountsLimit, Map<String, OAuthToken> initCredentials) {
        this(securityDao, getAccountsLimit);

        for (Map.Entry<String, OAuthToken> credentialsEntry: initCredentials.entrySet()) {
            save(credentialsEntry.getKey(), credentialsEntry.getValue());
        }
    }

    @Override
    public OAuthToken get(String uuid) {
        OAuthToken credentials = securityDao.get(uuid);

        log.trace("Retrivied credentials:[uuid: {}, credentials: {}]", uuid, credentials);
        
        return credentials;
    }

    @Override
    public void save(String uuid, OAuthToken credentials) {
        securityDao.save(uuid, credentials);
        log.trace("Saved credentials:[uuid:" + uuid + ", credentials:" + credentials + "]");
    }

    @Override
    public void delete(String uuid) {
        securityDao.delete(uuid);

        log.trace("Removed credentials for uuid: {}", uuid);
    }

    @Override
    public boolean exists(String uuid) {
        return securityDao.exists(uuid);
    }
    
    @Override
    public Collection<String> getKeys() {
        return Collections.unmodifiableList(securityDao.getUpdatedAccounts(getAccountsLimit));
    }
}
