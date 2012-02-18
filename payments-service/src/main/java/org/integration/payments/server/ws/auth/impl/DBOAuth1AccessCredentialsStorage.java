package org.integration.payments.server.ws.auth.impl;

import org.integration.payments.server.ws.auth.CredentialsStorage;
import org.integration.payments.server.ws.auth.SecurityDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth1.OAuthToken;

public class DBOAuth1AccessCredentialsStorage implements CredentialsStorage<OAuthToken> {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    private SecurityDao securityDao;

    public DBOAuth1AccessCredentialsStorage(SecurityDao securityDao) {
        this.securityDao = securityDao;

        log.info("Initialized DBOAuth1AccessCredentialsStorage");
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
    public OAuthToken resendAndGet(String uuid) {
        return get(uuid);
    }
}
