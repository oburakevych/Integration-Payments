package org.integration.connectors.tradeshift.account;

import org.integration.payments.server.ws.tradeshift.TradeshiftApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TradeshiftAccountService {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    private TradeshiftApiService apiService;
    private TradeshiftAccountDao accountDao;
    
    /**
     * Retrieves Account from Tradeshift API
     * @param companyAccountId
     * @return
     */
    public TradeshiftAccount retrieveAccount(String companyAccountId) {
        log.info("Retrieving Account Info for {}", companyAccountId);
        TradeshiftAccount account = apiService.getAccount(companyAccountId);
        
        log.info("Account retrieved: {}", account);
        
        return account;
    }
    
    public void saveAccount(TradeshiftAccount account) {
        log.info("Saving Tradeshift Account {}", account.getId());
        accountDao.save(account);
        
        log.info("Account saved {}", account);
    }

    public void setApiService(TradeshiftApiService apiService) {
        this.apiService = apiService;
    }

    public TradeshiftApiService getApiService() {
        return apiService;
    }

    public void setAccountDao(TradeshiftAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public TradeshiftAccountDao getAccountDao() {
        return accountDao;
    }
    
}
