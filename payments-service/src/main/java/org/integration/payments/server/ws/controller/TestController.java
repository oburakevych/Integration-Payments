package org.integration.payments.server.ws.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.integration.payments.server.ws.auth.CredentialsStorage;
import org.integration.payments.server.ws.auth.OAuth1AccessCredentials;
import org.integration.payments.server.ws.tradeshift.TradeshiftApiService;
import org.integration.payments.server.ws.tradeshift.dto.AppSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("test/tradeshift")
public class TestController {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TradeshiftApiService tradeshiftApiService;

	@Autowired
	private CredentialsStorage<OAuth1AccessCredentials> credentialsStorage;

	public void setTradeshiftApiService(TradeshiftApiService tradeshiftApiService) {
		this.tradeshiftApiService = tradeshiftApiService;
	}

	public void setCredentialsStorage(CredentialsStorage<OAuth1AccessCredentials> credentialsStorage) {
		this.credentialsStorage = credentialsStorage;
	}

	@RequestMapping(value = "/appsettings", method = RequestMethod.GET)
	public void getAppSettings(@RequestParam("companyAccountId") UUID companyAccountId,
			HttpServletResponse response) {
		log.debug("TEST: getAppSettings, REQUEST:{companyAccountId: " + companyAccountId + "}");

		AppSettings appSettings = tradeshiftApiService.getAppSettings(companyAccountId);

		log.debug("TEST: getAppSettings, RESPONSE:" + appSettings);

		writeStringResponse(response, appSettings);
	}

	@RequestMapping(value = "/auth/accessCredentials", method = RequestMethod.GET)
	public void getAccessCredentials(@RequestParam("companyAccountId") UUID companyAccountId,
			HttpServletResponse response) {

		log.debug("TEST: getAccessCredentials, REQUEST:{companyAccountId: " + companyAccountId + "}");

		OAuth1AccessCredentials accessCredentials = credentialsStorage.get(companyAccountId);

		log.debug("TEST: getAccessCredentials, RESPONSE:" + accessCredentials);

		writeStringResponse(response, accessCredentials);
	}

	private void writeStringResponse(HttpServletResponse response, Object respBody) {
		try {
			response.getWriter().print(respBody);
		} catch (IOException ex) {
			log.error(ex.getMessage(), ex);
		}
		
		
	}

	/*
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView indexPage() {
		return new ModelAndView("test-index.jsp");
	}
	*/
	
}
