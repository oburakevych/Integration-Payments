package org.integration.payments.server.ws.dropbox;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.module.SimpleModule;
import org.integration.connectors.dropbox.account.DropboxUserProfile;
import org.integration.connectors.dropbox.account.DropboxUserProfileDeserializer;
import org.integration.connectors.dropbox.files.DropboxFileEntryDeserializer;
import org.integration.connectors.dropbox.files.Entry;


public class DropboxModule extends SimpleModule {
    public DropboxModule() {
        super("DropboxModule", new Version(1, 0, 0, null));
    }

    @Override
    public void setupModule(SetupContext context) {
        context.setMixInAnnotations(DropboxUserProfile.class, DropboxUserProfileDeserializer.class);
        context.setMixInAnnotations(Entry.class, DropboxFileEntryDeserializer.class);
    }
}
