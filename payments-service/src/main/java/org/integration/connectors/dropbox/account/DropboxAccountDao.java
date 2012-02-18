package org.integration.connectors.dropbox.account;

import java.util.List;

import org.integration.account.Account;

public interface DropboxAccountDao {
    void save(Account account);
    DropboxAccount getAccount(String id);
    List<DropboxAccount> getAccounts(int limit);
}
