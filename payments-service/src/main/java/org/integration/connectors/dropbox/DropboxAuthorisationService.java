package org.integration.connectors.dropbox;

import java.util.UUID;

import org.integration.payments.server.ws.dropbox.auth.DropboxOAuth1AuthorizationManager;


public class DropboxAuthorisationService {
    private DropboxOAuth1AuthorizationManager oauthManager;
    
    public void fetchRequestToken(UUID companyAccountId) {
        oauthManager.fetchRequestToken(companyAccountId);
    }
    
    public String getAuthorisationUrl(UUID companyAccountId) {
        return oauthManager.buildAuthorizeUrl(companyAccountId);
    }
    
    public void requestAccessToken(UUID companyAccountId) {
        oauthManager.getAccessToken(companyAccountId);
    }
    

    public DropboxOAuth1AuthorizationManager getOauthManager() {
        return oauthManager;
    }

    public void setOauthManager(DropboxOAuth1AuthorizationManager oauthManager) {
        this.oauthManager = oauthManager;
    }
}
