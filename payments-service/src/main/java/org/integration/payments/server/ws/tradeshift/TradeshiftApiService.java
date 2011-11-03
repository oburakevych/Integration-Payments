package org.integration.payments.server.ws.tradeshift;

import java.util.UUID;

import org.integration.payments.server.ws.tradeshift.dto.AppSettings;

public interface TradeshiftApiService {
	public AppSettings getAppSettings(UUID companyAccountId);

	public void resendOAuthAccessToken(UUID companyAccountId);
}
