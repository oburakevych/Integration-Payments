package org.integration.connectors.dropbox.account.impl;

import java.util.List;

import org.integration.connectors.dropbox.account.DropboxAccount;
import org.integration.connectors.dropbox.account.DropboxAccountDao;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class DropboxAccountIbatisDao extends SqlMapClientDaoSupport implements DropboxAccountDao {
    private static final String NAMESPACE_FLAG = "DropboxAccount";
    
    // prefix `ST` means statement.
    private static final String ST_CREATE = NAMESPACE_FLAG + ".create";
    
    @Override
    public void save(DropboxAccount account) {
        getSqlMapClientTemplate().insert(ST_CREATE, account);
    }

    @Override
    public List<DropboxAccount> getAccounts(int limit) {
        return null;
    }

}
