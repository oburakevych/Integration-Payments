package org.integration.payments.server.ws.tradeshift;

import java.util.List;
import java.util.UUID;

import org.integration.connectors.documentfiles.DocumentFileList;
import org.integration.connectors.documentfiles.DocumentFileState;
import org.integration.payments.server.document.DocumentMetadata;
import org.integration.payments.server.ws.tradeshift.dto.AppSettings;


public interface TradeshiftApiService {
	public AppSettings getAppSettings(UUID companyAccountId);

	public void resendOAuthAccessToken(UUID companyAccountId);
	
	public DocumentMetadata getDocumentMetadata(UUID companyAccountId, UUID documentId);

	public byte[] getDocument(UUID companyAccountId, UUID documentId, String locale);
	
	public void putDocumentFile(UUID companyAccountId, String directory, String filename, String mimeType, byte[] content);
	
	public void dispatchDocumentFile(UUID companyAccountId, String directory, String filename);
	
	public byte[] getDocumentFile(UUID companyAccountId, String directory, String filename);
	
	public DocumentFileList getDocumentFiles(UUID companyAccountId, String since, int limit, int page, DocumentFileState state, String directory, String filename);
}
