package org.integration.connectors.dropbox.account.impl;

import java.util.List;

import org.integration.account.Account;
import org.integration.connectors.dropbox.account.DropboxAccount;
import org.integration.connectors.dropbox.account.DropboxAccountDao;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class DropboxAccountIbatisDao extends SqlMapClientDaoSupport implements DropboxAccountDao {
    private static final String NAMESPACE_FLAG = "DropboxAccount";
    
    // prefix `ST` means statement.
    private static final String ST_CREATE = NAMESPACE_FLAG + ".create";
    private static final String ST_GET_ACCOUNT = NAMESPACE_FLAG + ".getAccount";
    private static final String ST_GET_ACCOUNTS = NAMESPACE_FLAG + ".getAccounts";
    
    @Override
    public void save(Account account) {
        getSqlMapClientTemplate().insert(ST_CREATE, (DropboxAccount) account);
    }

    @Override
    public List<DropboxAccount> getAccounts(int limit) {
        return getSqlMapClientTemplate().queryForList(ST_GET_ACCOUNTS, 0, limit);
    }

    @Override
    public DropboxAccount getAccount(String id) {
        return (DropboxAccount) getSqlMapClientTemplate().queryForObject(ST_GET_ACCOUNT, id);
    }

}
