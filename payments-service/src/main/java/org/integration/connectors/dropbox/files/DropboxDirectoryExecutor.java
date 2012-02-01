package org.integration.connectors.dropbox.files;

import java.util.UUID;

import org.integration.connectors.process.Excecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

public class DropboxDirectoryExecutor implements Excecutor {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    public static final String DEFAULT_PATH = "outbox";
    
    private DropboxFileService fileService;

    @Async
    @Override
    public void run() {
        log.debug("Running a job against directory {}", DEFAULT_PATH);
        run(DEFAULT_PATH);
    }    
    
    @Async
    @Override
    public void run(String path) {
        log.debug("Running a job");
        UUID companyAccountId = UUID.fromString("e38d91cd-1732-4bb8-ad83-b0d1d2e5d725");
        Entry directoryEntry = fileService.getMetadataEntry(companyAccountId, path);
        
        processDirectory(directoryEntry);
    }
    
    protected void processDirectory(Entry directory) {
        if (directory == null || directory.getContents() == null || directory.getContents().isEmpty()) {
            log.debug("Directory is empty");
            return;
        }
        
        log.debug("Directory {} hash is {}", directory.getName(), directory.getHash());
        log.debug("Found {} entries", directory.getContents().size());
        
        for(Entry file : directory.getContents()) {
            log.debug("Processing entry {}", file.getName());
            
            if (file.isDir()) {
                log.warn("Directory found {}. Should not be directories here.", file.getPath());
                continue;
            }
            
            processFile(file);
        }
    }
    
    protected void processFile(Entry file) {
        log.debug("Processing file {}", file.getPath());
    }

    public DropboxFileService getFileService() {
        return fileService;
    }

    public void setFileService(DropboxFileService fileService) {
        this.fileService = fileService;
    }

}
