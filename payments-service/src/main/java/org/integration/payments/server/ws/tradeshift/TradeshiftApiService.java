package org.integration.payments.server.ws.tradeshift;

import java.util.UUID;

import org.integration.payments.server.ws.tradeshift.dto.AppSettings;

public interface TradeshiftApiService {
	public AppSettings getAppSettings();
	
	public byte[] getDocument(UUID id, String locale);
}
