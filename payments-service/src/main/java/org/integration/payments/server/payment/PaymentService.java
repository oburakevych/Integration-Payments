package org.integration.payments.server.payment;

import java.util.UUID;

import org.integration.payments.server.document.Document;
import org.integration.payments.server.ws.tradeshift.TradeshiftApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PaymentService {
    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);
    
    private TradeshiftApiService tradeshiftApiService;
    
    public void createPayment(UUID companyAccountId, Document document) {
        log.debug("Creating payment for a document");
        
        // TODO: create a history record in the DB about the payment request
        
        
    }

    public void setTradeshiftApiService(TradeshiftApiService tradeshiftApiService) {
        this.tradeshiftApiService = tradeshiftApiService;
    }

    public TradeshiftApiService getTradeshiftApiService() {
        return tradeshiftApiService;
    }

}
