package org.integration.payments.server.ws.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpStatus;
import org.integration.payments.server.ws.auth.CredentialsStorage;
import org.integration.payments.server.ws.auth.OAuth1AccessCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * https://178.136.240.133:3443/sandbox-payments-server/callback/tradeshift/oauth
 * 
 * http://localhost:3080       /sandbox-payments-server/callback/tradeshift/oauth
 */
@Controller
@RequestMapping("callback/tradeshift/")
public class TradeshiftCallbackController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CredentialsStorage<OAuth1AccessCredentials> credentialsStorage;

	public void setCredentialsStorage(CredentialsStorage<OAuth1AccessCredentials> credentialsStorage) {
		this.credentialsStorage = credentialsStorage;
	}

	@RequestMapping(value = "oauth", method = RequestMethod.POST)
	public void oAuth1Callback(@RequestParam("companyAccountId") UUID companyAccountId, 
		@RequestParam("oauth_token") String accessToken, 
		@RequestParam("oauth_token_secret") String accessTokenSecret, 
		HttpServletResponse response) {

		if (log.isTraceEnabled()) {
			log.trace("Received Tradeshift OAuth1 callback:{companyAccountId:" + companyAccountId 
				+ ", accessToken:" + accessToken + ", accessTokenSecret:" + accessTokenSecret);
		}

		credentialsStorage.save(companyAccountId, new OAuth1AccessCredentials(accessToken, accessTokenSecret));

		response.setStatus(HttpStatus.SC_OK);
	}
}
