package org.integration.payments.server.ws.tradeshift.auth;

import java.util.UUID;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.integration.payments.server.ws.auth.CredentialsStorage;
import org.integration.payments.server.ws.auth.OAuth1AccessCredentials;
import org.integration.payments.server.ws.tradeshift.TradeshiftApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class TradehiftCredentialsUpdateManager {

	protected Logger log = LoggerFactory.getLogger(this.getClass());

	private CredentialsStorage<OAuth1AccessCredentials> credentialsStorage;

	private TradeshiftApiService apiService;

	private int callbackTimeout;

	public TradehiftCredentialsUpdateManager(TradeshiftApiService apiService, CredentialsStorage<OAuth1AccessCredentials> credentialsStorage) {
		this.credentialsStorage = credentialsStorage;
		this.apiService = apiService;
	}

	@Before("execution(public * org.integration.payments.server.ws.auth.CredentialsStorage.get(..)) and bean(ws.tradeshift.credentialsStorage) and args(companyAccountId)")
	public void checkAndRequestResendCredentials(UUID companyAccountId) {
		if (log.isTraceEnabled()) {
			log.trace("Checking credentials for:{companyAccountId:" + companyAccountId + "}");
		}

		boolean credentialsExists = credentialsStorage.isExists(companyAccountId);

		if (!credentialsExists) {
			requestResendCredentials(companyAccountId);
		}
	}

	public void forseRequestResendCredentials(UUID companyAccountId) {
		if (log.isTraceEnabled()) {
			log.trace("Forsing resend credentials for {companyAccountId:" + companyAccountId + "}");
		}

		credentialsStorage.delete(companyAccountId);

		requestResendCredentials(companyAccountId);
	}

	private void requestResendCredentials(UUID companyAccountId) {
		if (log.isTraceEnabled()) {
			log.trace("Requesting resend credentials:{companyAccountId:" + companyAccountId + "}");
		}

		apiService.resendOAuthAccessToken(companyAccountId);

		boolean isNotCallbackTimeout = true;
		long startTime = System.currentTimeMillis();

		try {
			do {
				long currentTime = System.currentTimeMillis();

				long waitTimeout = Math.max(1, currentTime - startTime);

				if (log.isTraceEnabled()) {
					log.trace("Waiting on credentials callback:{companyAccountId:" + companyAccountId + "}");
				}

				wait(waitTimeout);

				OAuth1AccessCredentials credentials = credentialsStorage.get(companyAccountId);

				if (credentials != null) {
					if (log.isTraceEnabled()) {
						log.trace("Detected credentials update: {companyAccountId:" + companyAccountId
							+ ", credentials:" + credentials + "}");
					}

					break;
				}

				isNotCallbackTimeout = callbackTimeout > System.currentTimeMillis() - startTime;
			} while (isNotCallbackTimeout);

			if (!isNotCallbackTimeout && log.isWarnEnabled()) {
				log.warn("Callback timeout excedded: {companyAccountId:" + companyAccountId 
					+ ", callbackTimeout(ms):" + callbackTimeout + "}");
			}
		} catch (InterruptedException ex) {
			log.error(ex.getMessage(), ex);

			throw new RuntimeException(ex.getMessage(), ex);
		}
	}
}
