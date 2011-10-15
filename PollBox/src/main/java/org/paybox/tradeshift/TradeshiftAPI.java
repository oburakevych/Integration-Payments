package org.paybox.tradeshift;

import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.paybox.oauth.OAuthCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.oauth.client.OAuthClientFilter;
import com.sun.jersey.oauth.signature.OAuthParameters;
import com.sun.jersey.oauth.signature.OAuthSecrets;


@Component
public abstract class TradeshiftAPI {
    private static final Logger log = LoggerFactory.getLogger(TradeshiftAPI.class);
    
    private static final String SIGNATURE_METHOD = "HMAC-SHA1";
    
    private Client client;
    
    private String proxyURL = "http://localhost:8889/tradeshift-proxy/rest";
    
    protected abstract String getConsumerKey();

    protected abstract String getConsumerSecret();
    
    public String getProxyURL() {
        return proxyURL;
    }
    
    @PostConstruct public void start() {
        ClientConfig cc = new DefaultClientConfig();
        cc.getClasses().add(JacksonJsonProvider.class);
        client = Client.create(cc);
        client.setFollowRedirects(false);
        client.setChunkedEncodingSize(64*1024);
    }
    
    /**
     * Gets a jersey WebResource, prepared for OAuth signature, but without X-Tradeshift-TenantId header (add that yourself).
     * @param OAuthCredentials OAuth token to apply, or null to only use consumer key + secret
     */
    protected WebResource getResource(OAuthCredentials credentials) {
        OAuthParameters params = new OAuthParameters().signatureMethod(SIGNATURE_METHOD).consumerKey(getConsumerKey()).version();
        if (credentials != null) params = params.token(credentials.getToken());
        OAuthSecrets secrets = new OAuthSecrets().consumerSecret(getConsumerSecret());
        if (credentials != null) secrets = secrets.tokenSecret(credentials.getTokenSecret());
        OAuthClientFilter filter = new OAuthClientFilter(client.getProviders(), params, secrets);

        WebResource resource = client.resource(proxyURL);
        resource.addFilter(filter);
        resource.addFilter(new LoggingFilter());
        return resource;
    }
        
    public void resendOAuthToken (UUID companyAccountId) {
        getResource(null).path("external/consumer/accounts").path(companyAccountId.toString()).path("resendtoken").post();
    }
    
    public void ping() {
        getResource(null).path("external/info/timezones").accept(MediaType.APPLICATION_JSON).get(String.class);
    }
    
    public Account getAccount (UUID companyAccountId, OAuthCredentials oauth) {
        return new Account (companyAccountId, oauth);
    }
    
    public final class Account {
        private UUID companyAccountId;
        private OAuthCredentials oauth;
        
        public Account(UUID companyAccountId, OAuthCredentials oauth) {
            this.companyAccountId = companyAccountId;
            this.oauth = oauth;
        }

        public String getAppSetting(String setting) {
            return getResource(oauth).path("external/account/appsettings").path(setting).header("X-Tradeshift-TenantId", companyAccountId).get(String.class);
        }
    }
    
}
