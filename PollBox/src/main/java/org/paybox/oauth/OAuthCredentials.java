package org.paybox.oauth;

public class OAuthCredentials {
    private String token;
    
    private String tokenSecret;

    public OAuthCredentials(String token, String tokenSecret) {
        this.token = token;
        this.tokenSecret = tokenSecret;
    }

    public String getToken() {
        return token;
    }
    
    public String getTokenSecret() {
        return tokenSecret;
    }
}
