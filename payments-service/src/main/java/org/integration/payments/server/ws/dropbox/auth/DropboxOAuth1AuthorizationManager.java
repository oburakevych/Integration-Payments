package org.integration.payments.server.ws.dropbox.auth;

import java.util.UUID;

import org.integration.payments.server.ws.auth.CredentialsStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.oauth1.OAuth1Parameters;
import org.springframework.social.oauth1.OAuthToken;

public class DropboxOAuth1AuthorizationManager {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    private DropboxServiceProvider serviceProvider;
    private final String consumerKey;
    private final String consumerSecret;
    private final CredentialsStorage<OAuthToken> requestTokenStorage;
    
    public DropboxOAuth1AuthorizationManager(String consumerKey, String consumerSecret, CredentialsStorage<OAuthToken> requestTokenStorage, DropboxServiceProvider serviceProvider) {
        this.consumerKey = consumerKey;
        this.consumerSecret = consumerSecret;
        this.requestTokenStorage = requestTokenStorage;
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
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
}
