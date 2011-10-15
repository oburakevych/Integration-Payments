package org.paybox.payments.moneybookers;

import org.paybox.oauth.OAuthCredentialManager;
import org.paybox.tradeshift.TradeshiftAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


// authentication to moneybookers account will be implemented here
@Component
public class MoneyBookersCredentialManager extends OAuthCredentialManager {
    @Autowired private MoneybookersTradeshiftAPI api;
    private long timeout = 10000; // Timeout in milliseconds
    
    @Override
    protected TradeshiftAPI getTradeshiftAPI() {
        return api;
    }
    
    protected long getTimeout() {
        return timeout;
    }
    
}
