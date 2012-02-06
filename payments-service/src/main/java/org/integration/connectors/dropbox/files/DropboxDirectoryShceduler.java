package org.integration.connectors.dropbox.files;


import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

import org.integration.connectors.dropbox.DropboxAuthorisationService;
import org.integration.connectors.process.Executor;
import org.integration.connectors.process.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropboxDirectoryShceduler implements Scheduler {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    private Executor executor;
    private DropboxAuthorisationService authorisationService;
    
    @Override
    public void process() {
        Set<UUID> companyAccounts = authorisationService.getOauthManager().getCachedCompanyAccounts();
        
        log.debug("Found {} cached company accounts", companyAccounts.size());
        log.debug("Process started at: " + new Date());
        for(UUID companyAccountId : companyAccounts) {
            Calendar begin = Calendar.getInstance();
            
            log.debug("Start processing {} at {}", companyAccountId, begin);
            executor.run(companyAccountId);
            log.debug("Finished processing {}, it took {} milliseconds", companyAccountId, Calendar.getInstance().getTimeInMillis() - begin.getTimeInMillis());
        }
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public DropboxAuthorisationService getAuthorisationService() {
        return authorisationService;
    }

    public void setAuthorisationService(DropboxAuthorisationService authorisationService) {
        this.authorisationService = authorisationService;
    }



}
