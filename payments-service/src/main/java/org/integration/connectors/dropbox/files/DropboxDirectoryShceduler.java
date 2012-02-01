package org.integration.connectors.dropbox.files;


import java.util.Date;

import org.integration.connectors.process.Scheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DropboxDirectoryShceduler implements Scheduler {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    
    private DropboxDirectoryExecutor executor;
    
    @Override
    public void process() {
        log.debug("Process started at: " + new Date());
        executor.run();
    }

    public DropboxDirectoryExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(DropboxDirectoryExecutor executor) {
        this.executor = executor;
    }



}
