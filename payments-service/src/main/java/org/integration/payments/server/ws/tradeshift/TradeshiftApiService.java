package org.integration.payments.server.ws.tradeshift;

import java.util.UUID;

import org.integration.payments.server.document.DocumentMetadata;
import org.integration.payments.server.ws.tradeshift.dto.AppSettings;


public interface TradeshiftApiService {
	public AppSettings getAppSettings(UUID companyAccountId);

	public void resendOAuthAccessToken(UUID companyAccountId);
	
	public byte[] getDocument(UUID id, String locale);
	
	public DocumentMetadata getDocumentMetadata(UUID documentId);

	public byte[] getDocument(UUID companyAccountId, UUID documentId, String locale);
}
