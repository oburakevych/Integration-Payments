package org.integration.connectors.dropbox;

import org.springframework.social.dropbox.api.DropboxApi;
import org.springframework.social.dropbox.connect.DropboxAdapter;
import org.springframework.social.dropbox.connect.DropboxServiceProvider;

public class DropboxConnectorService {
    private DropboxAdapter adapter = new DropboxAdapter();
    private DropboxApi api;
    
    public void testApi() {
        DropboxServiceProvider p = new DropboxServiceProvider("", "");
        
        //p.getApi(accessToken, secret);
    }
}
