package org.integration.connectors.dropbox.account;

import java.util.List;

public interface DropboxAccountDao {
    void save(DropboxAccount account);
    List<DropboxAccount> getAccounts(int limit);
}
