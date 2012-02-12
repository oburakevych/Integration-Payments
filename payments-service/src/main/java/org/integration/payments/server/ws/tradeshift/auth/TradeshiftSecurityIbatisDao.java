package org.integration.payments.server.ws.tradeshift.auth;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.integration.connectors.tradeshift.account.TradeshiftAccount;
import org.integration.connectors.tradeshift.security.TradeshiftAccessToken;
import org.integration.payments.server.ws.auth.SecurityDao;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.social.oauth1.OAuthToken;

public class TradeshiftSecurityIbatisDao extends SqlMapClientDaoSupport implements SecurityDao {
    private static final String NAMESPACE_FLAG = "TradeshiftAccessToken";
    
    // prefix `ST` means statement.
    private static final String ST_CREATE = NAMESPACE_FLAG + ".create";
    private static final String ST_EXISTS = NAMESPACE_FLAG + ".exists";
    private static final String ST_UPDATE = NAMESPACE_FLAG + ".update";
    private static final String ST_GET_BY_ACCOUNT_ID = NAMESPACE_FLAG + ".get";
    private static final String ST_GET_ALL = NAMESPACE_FLAG + ".getAll";
    private static final String ST_DELETE = NAMESPACE_FLAG + ".delete";

    @Override
    public void save(String accountId, OAuthToken credentials) {
        if (exists(accountId)) {
            update(accountId, credentials);
        } else {
            getSqlMapClientTemplate().insert(ST_CREATE, (TradeshiftAccessToken) credentials);
        }
    }
    
    @Override
    public void update(String accountId, OAuthToken credentials) {
        getSqlMapClientTemplate().update(ST_UPDATE, (TradeshiftAccessToken) credentials);
    }

    @Override
    public OAuthToken get(String accountId) {
        return (OAuthToken) getSqlMapClientTemplate().queryForObject(ST_GET_BY_ACCOUNT_ID, accountId);
    }

    @Override
    public void delete(String accountId) {
        getSqlMapClientTemplate().delete(ST_DELETE, accountId);
    }

    @Override
    public boolean exists(String accountId) {
        return get(accountId) != null;
    }

    @Override
    public List<String> getAllAccounts() {
        return getSqlMapClientTemplate().queryForList(ST_GET_ALL);
    }

    @Override
    public List<String> getAccounts(Date modifiedSince, Date lastCheckedSince, Boolean updated, int limit) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getUpdatedAccounts(int limit) {
        // TODO Auto-generated method stub
        return null;
    }

}
