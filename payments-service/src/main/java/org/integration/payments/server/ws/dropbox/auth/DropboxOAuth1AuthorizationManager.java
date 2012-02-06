package org.integration.payments.server.ws.dropbox.auth;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.integration.payments.server.ws.auth.CredentialsStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth1.AuthorizedRequestToken;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;

/*
 * Access Token: key gzfxqwiw9yq1csp, secret xz8mif19iucocyr for account e38d91cd-1732-4bb8-ad83-b0d1d2e5d725, uid=56819311
 */

public class DropboxOAuth1AuthorizationManager {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    private DropboxServiceProvider serviceProvider;
    private final String consumerKey;
    private final String consumerSecret;
    private final CredentialsStorage<OAuthToken> requestTokenStorage;
    private final CredentialsStorage<OAuthToken> credentialsStorage;
    
    public DropboxOAuth1AuthorizationManager(String consumerKey, String consumerSecret, 
            CredentialsStorage<OAuthToken> requestTokenStorage,
            CredentialsStorage<OAuthToken> credentialsStorage, DropboxServiceProvider serviceProvider) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.requestTokenStorage = requestTokenStorage;
        this.credentialsStorage = credentialsStorage;
        this.serviceProvider = serviceProvider;
    }
    
    public void fetchRequestToken(UUID companyAccountId) {
        OAuthToken requestToken = getServiceProvider().getOAuthOperations().fetchRequestToken(null, null);
        
        if (requestToken != null) {
            log.debug("Request Token Fetched: key: {}, secret: {}", requestToken.getValue(), requestToken.getSecret());
            getRequestTokenStorage().save(companyAccountId, requestToken);
        } else {
            log.warn("Request token could not be fetched!");
        }
    }
    
    public String buildAuthorizeUrl(UUID companyAccountId) {
        String url = null;
        
        if (!getRequestTokenStorage().exists(companyAccountId)) {
            log.debug("The Request Token is not in the storage... Requesting...");
            fetchRequestToken(companyAccountId);
            
            if (!getRequestTokenStorage().exists(companyAccountId)) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        
        if (getRequestTokenStorage().exists(companyAccountId)) {
            OAuthToken requestToken = getRequestTokenStorage().get(companyAccountId);

            OAuth1Parameters params = new OAuth1Parameters();
            params.setCallbackUrl("https://enterprise-sandbox.tradeshift.com/app/SASHSD.DpConn");
            
            url = getServiceProvider().getOAuthOperations().buildAuthorizeUrl(requestToken.getValue(), params);
            
            log.debug("Authorize URL for company account ID {} constructed {}", companyAccountId, url);
        }
        
        return url;
    }
    
    public OAuthToken getAccessToken(UUID companyAccountId) {
        OAuthToken requestToken = getRequestTokenStorage().get(companyAccountId);
        OAuthToken accessToken = getServiceProvider().getOAuthOperations().exchangeForAccessToken(new AuthorizedRequestToken(requestToken, null), OAuth1Parameters.NONE);
        
        log.debug("Received Access Token: key {}, secret {} for account {}", new Object[] {accessToken.getValue(), accessToken.getSecret(), companyAccountId});
        
        getCredentialsStorage().save(companyAccountId, accessToken);
        
        return accessToken;
    }
    
    public Set<UUID> getCachedCompanyAccounts() {
        return getCredentialsStorage().getCachedKeys();
    }

    protected void setServiceProvider(DropboxServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    protected DropboxServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public String getConsumerSecret() {
        return consumerSecret;
    }

    public CredentialsStorage<OAuthToken> getRequestTokenStorage() {
        return requestTokenStorage;
    }

    public CredentialsStorage<OAuthToken> getCredentialsStorage() {
        return credentialsStorage;
    }
}
