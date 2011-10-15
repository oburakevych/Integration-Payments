package org.paybox.payments.moneybookers;

import org.paybox.tradeshift.TradeshiftAPI;
import org.springframework.stereotype.Component;


@Component
public class MoneybookersTradeshiftAPI extends TradeshiftAPI {
    // Enterprise-sandbox
    private String consumerKey = "0hDgCGj2TywrexgQ0G0E"; // purpose = "Online Payments Moneybookers";
    private String consumerSecret = "VOee1MdCJ6E86ApEswfSjiAXp57Oj1A1MMQ41Do5";
    
    @Override
    protected String getConsumerKey() {
        return consumerKey;
    }

    @Override
    protected String getConsumerSecret() {
        return consumerSecret;
    }

}
