package org.integration.connectors.tradeshift.security;

import java.util.Date;

import org.springframework.social.oauth1.OAuthToken;

public class TradeshiftAccessToken extends OAuthToken {
    private static final long serialVersionUID = 3048055122499068957L;
    
    private String accountId;
    private String consumerKey;
    private Date created;
    
    public TradeshiftAccessToken() {
        super(null, null);
    }

    public TradeshiftAccessToken(String accountId, String value, String secret) {
        super(value, secret);
        this.accountId = accountId;
        this.created = new Date();
    }
    
    public TradeshiftAccessToken(String accountId, OAuthToken token) {
        this(accountId, token.getValue(), token.getSecret());
    }
    
    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getConsumerKey() {
        return consumerKey;
    }

    public void setConsumerKey(String consumerKey) {
        this.consumerKey = consumerKey;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
