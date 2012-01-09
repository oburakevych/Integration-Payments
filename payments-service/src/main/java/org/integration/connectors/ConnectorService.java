package org.integration.connectors;

import java.util.List;
import java.util.UUID;

import org.integration.connectors.documentfiles.DocumentFileList;
import org.integration.connectors.documentfiles.DocumentFileState;
import org.integration.payments.server.ws.tradeshift.TradeshiftApiService;


public abstract class ConnectorService {
    private TradeshiftApiService tradeshiftApiService;
    
    public void transferDocumentFile(UUID companyAccountId, String directory, String fileName, String mimeType, byte[] document) {
        getTradeshiftApiService().putDocumentFile(companyAccountId, directory, fileName, mimeType, document);
    }
    
    public void dispatchDocumentFile(UUID companyAccountId, String directory, String fileName) {
        getTradeshiftApiService().dispatchDocumentFile(companyAccountId, directory, fileName);
    }
    
    public DocumentFileList getDocumentFiles(UUID companyAccountId, String since, int limit, int page, DocumentFileState state, String directory, String filename) {
        return getTradeshiftApiService().getDocumentFiles(companyAccountId, since, limit, page, state, directory, filename);
    }
    
    public byte[] getDocumentFile(UUID companyAccountId, String directory, String filename) {
        return getTradeshiftApiService().getDocumentFile(companyAccountId, directory, filename);
    }

    public void setTradeshiftApiService(TradeshiftApiService tradeshiftApiService) {
        this.tradeshiftApiService = tradeshiftApiService;
    }

    public TradeshiftApiService getTradeshiftApiService() {
        return tradeshiftApiService;
    }
    
    
}
