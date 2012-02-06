package org.integration.payments.server.ws.dropbox;

import java.util.UUID;

import org.integration.connectors.dropbox.account.DropboxUserProfile;
import org.integration.connectors.dropbox.files.DropboxFile;
import org.integration.connectors.dropbox.files.Entry;


public interface DropboxApiService {
    DropboxUserProfile getUserProfile(UUID companyAccountId);
    
    Entry getMetadataEntry(UUID companyAccountId, String root, String path);
    
    Entry mkDir(UUID companyAccountId, String root, String path);
    
    DropboxFile getFile(UUID companyAccountId, String root, String path);
    
    Entry copy(UUID companyAccountId, String root, String fromPath, String toPath);
    Entry move(UUID companyAccountId, String root, String fromPath, String toPath);
}
