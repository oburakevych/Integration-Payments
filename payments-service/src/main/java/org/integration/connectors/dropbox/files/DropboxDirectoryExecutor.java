package org.integration.connectors.dropbox.files;

import java.util.UUID;

import org.integration.connectors.documentfiles.DocumentFile;
import org.integration.connectors.documentfiles.DocumentFileList;
import org.integration.connectors.dropbox.TradeshiftConnectorService;
import org.integration.connectors.dropbox.exception.DropboxException;
import org.integration.connectors.process.Executor;
import org.integration.payments.server.document.Dispatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;

public class DropboxDirectoryExecutor implements Executor {
    protected Logger log = LoggerFactory.getLogger(this.getClass());
    public static final String DEFAULT_DROPBOX_PATH = "";
    public static final String TS_DIR = "/outbox";
    
    private DropboxFileService fileService;
    private TradeshiftConnectorService tradeshiftService;

    @Async
    @Override
    public void run(UUID companyAccountId) {
        log.debug("Running a job against directory {}", DEFAULT_DROPBOX_PATH);
        run(companyAccountId, DEFAULT_DROPBOX_PATH);
    }
    
    @Async
    @Override
    public void run(UUID companyAccountId, String path) {
        log.debug("Running a job");
        
        Entry directoryEntry = fileService.getMetadataEntry(companyAccountId, path);
        
        processDirectory(companyAccountId, directoryEntry);
    }
    
    protected void processDirectory(UUID companyAccountId, Entry directory) {
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
            
            processFile(companyAccountId, file);
        }
    }
    
    protected void processFile(UUID companyAccountId, Entry file) {
        log.debug("Processing file {}", file.getPath());
        try {
            DropboxFile df = fileService.getFile(companyAccountId, file.getPath());
            
            if (df == null || df.getContents() == null || df.getContents().length < 1) {
                throw new DropboxException("Corrupted file " + file.getPath() + " for Account " + companyAccountId);
            }
            
            dispatch(companyAccountId, df.getFileName(), df.getMimeType(), df.getContents());
            Thread.sleep(3000);
            Dispatch dispaychResult = getDispatchResult(companyAccountId, df.getFileName());
            
            if (dispaychResult != null && dispaychResult.getDispatchState() != null) {
                switch (dispaychResult.getDispatchState()) {
                case ACCEPTED:
                    log.info("Dispatch of the file {} has been ACCEPTED for Account {}", df.getFileName(), companyAccountId);
                    fileService.move(companyAccountId, file.getPath(), "/inprocess/" + file.getName());                    
                    break;
                case FAILED:
                    log.warn("Dispatch of the file {} FAILED for Account {}", df.getFileName(), companyAccountId);
                    fileService.move(companyAccountId, file.getName(), "failed/" + file.getName());
                    break;
                case COMPLETED:
                    log.info("Dispatch of the file {} has been COMPLETED successfully for account {}", df.getFileName(), companyAccountId);
                    fileService.move(companyAccountId, file.getName(), "sent/" + file.getName());
                default:
                    break;
                }
            }
        } catch (Exception e) {
            log.error("Exception processing file " + file.getPath() + " for Account " + companyAccountId, e);
        }
            
    }
    
    protected void dispatch(UUID companyAccountId, String fileName, String mimeType, byte[] document) {
        tradeshiftService.transferDocumentFile(companyAccountId, TS_DIR, fileName, mimeType, document);
        tradeshiftService.dispatchDocumentFile(companyAccountId, TS_DIR, fileName);
    }
    
    protected Dispatch getDispatchResult(UUID companyAccountId, String filename) {
        DocumentFileList dfList = tradeshiftService.getDocumentFiles(companyAccountId, null, 1, 0, null, null, filename);
        
        if (dfList != null && dfList.getItems() != null && !dfList.getItems().isEmpty()) {
            log.debug("Document Files size is {}", dfList.getItems().size());
            for (DocumentFile df : dfList.getItems()) {
                log.debug("Requesting dispatch status of the file {}", df.getFilename());
                Dispatch dispatch = tradeshiftService.getLatestDispatch(companyAccountId, df.getDocumentId());
                
                log.debug("Dispatch status {}", dispatch.getDispatchState());
                
                return dispatch;
            }
        } else {
            log.warn("The are no files!");
        }
        
        return null;
    }
    
    protected void handleDispatchStatus(UUID companyAccountId, String fileName) {
        
    }

    public DropboxFileService getFileService() {
        return fileService;
    }

    public void setFileService(DropboxFileService fileService) {
        this.fileService = fileService;
    }

    public void setTradeshiftService(TradeshiftConnectorService tradeshiftService) {
        this.tradeshiftService = tradeshiftService;
    }

    public TradeshiftConnectorService getTradeshiftService() {
        return tradeshiftService;
    }

}
