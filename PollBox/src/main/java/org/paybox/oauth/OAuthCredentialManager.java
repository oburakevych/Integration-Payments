package org.paybox.oauth;

import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.collections.map.LRUMap;
import org.paybox.tradeshift.TradeshiftAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public abstract class OAuthCredentialManager {
    protected  abstract TradeshiftAPI getTradeshiftAPI();
    protected abstract long getTimeout();
    
    private static final Logger log = LoggerFactory.getLogger(OAuthCredentialManager.class);
    
    private LRUMap cache;
    
    private int cacheSize = 1000;
    
    @PostConstruct public void start() {
        cache = new LRUMap(cacheSize);
    }
    
    public void setCacheSize(int cacheSize) {
        this.cacheSize = cacheSize;
    }
    
    public void store(UUID companyAccountId, OAuthCredentials credentials) {
        log.info("Received Oauth callback for " + companyAccountId + " with token " + credentials.getToken());
        cache.put(companyAccountId, credentials);
        synchronized(this) {
            notifyAll();
        }
    }
    
    /**
     * Returns the OAuth credentials for the given company account ID, if they're currently in the cache.
     */
    public OAuthCredentials peek(UUID companyAccountId) {
        return (OAuthCredentials) cache.get(companyAccountId);
    }
    
    /**
     * Retrieve the OAuth credentials for the given company account ID, either by getting them from cache,
     * or by requesting the proxy to call us back with the information.
     * @param timeout Time in ms to wait for our callback, before returning null
     * @return The credentials, or null if no access or timeout occurred
     */
    public OAuthCredentials retrieve(UUID companyAccountId) {
        long start = System.currentTimeMillis();
        
        OAuthCredentials credentials = peek(companyAccountId);
        
        if (credentials != null) {
            return credentials;
        }
        
        do {
            log.debug ("Requesting resend of token for " + companyAccountId);
            try {
                // send a request to the api.tradeshift.com to resend the token
                getTradeshiftAPI().resendOAuthToken(companyAccountId);
            } catch (RuntimeException x) {
                log.error ("Error requesting resend of OAuth token for " + companyAccountId, x);
                throw x; // TODO: retry?
            }
            synchronized(this) {
                long timeleft = Math.max(1, System.currentTimeMillis() - start);
                try {
                    wait(timeleft);
                } catch(InterruptedException e) {
                    log.warn("Interrupted while waiting on OAuth callback for company account " + companyAccountId, e);
                    throw new RuntimeException (e);
                }
            }
            credentials = peek(companyAccountId);
            if (credentials != null) {
                return credentials;
            }
        } while (System.currentTimeMillis() < start + getTimeout());
        
        return null;
    }

    /**
     * Remove the given company account id from the cache.
     */
    public void remove(UUID companyAccountId) {
        cache.remove(companyAccountId);        
    }

}
