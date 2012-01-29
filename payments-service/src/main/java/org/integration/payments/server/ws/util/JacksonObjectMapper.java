package org.integration.payments.server.ws.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.integration.payments.server.ws.dropbox.DropboxModule;

public class JacksonObjectMapper extends ObjectMapper{
    public JacksonObjectMapper() {
        super();
        registerModule(new DropboxModule());
    }

}
