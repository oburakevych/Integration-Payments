package org.integration.paymentgateway.dibs.flexwin.conf.impl;

import static org.integration.paymentgateway.dibs.flexwin.conf.FlexWinParam.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.integration.paymentgateway.dibs.flexwin.conf.FlexWinBean;
import org.integration.paymentgateway.dibs.flexwin.conf.FlexWinConfigurator;
import org.integration.paymentgateway.dibs.flexwin.conf.FlexWinParam;
import org.integration.payments.server.document.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FlexWinBasicConfigurator implements FlexWinConfigurator {
    private static Logger log = LoggerFactory.getLogger(FlexWinBasicConfigurator.class);
    
	@Override
	public Set<FlexWinParam> getParams() {
		Set<FlexWinParam> params = new HashSet<FlexWinParam>();

		params.add(MERCHANT_ID);
		params.add(SESSION_ID);
		params.add(ACCEPT_URL);
		params.add(CANCEL_URL);
		params.add(ORDER_ID);
		params.add(IS_UNIQUE_ODRER_ID);
		params.add(PAY_TYPE);
		params.add(AMOUNT);
		params.add(CURRENCY_NUMBER_CODE);
		params.add(LANGUAGE);
		params.add(IS_TEST_MODE);

		return params;
	}

    @Override
    public FlexWinBean getDefaultFlexWin(Document document, String merchantId, String orderId, String acceptUrl, String cancelUrl, String callbackUrl, String sessionId) {
        // TODO Auto-generated method stub
        return null;
    }

}
