package org.integration.connectors.tradeshift.account.impl;

import org.integration.connectors.tradeshift.account.TradeshiftAccount;
import org.integration.connectors.tradeshift.account.TradeshiftAccountDao;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class TradeshiftAccountIbatisDao extends SqlMapClientDaoSupport implements TradeshiftAccountDao{
    private static final String NAMESPACE_FLAG = "TradeshiftAccount";
    
    // prefix `ST` means statement.
    private static final String ST_CREATE = NAMESPACE_FLAG + ".create";
    
    @Override
    public void save(TradeshiftAccount account) {
        getSqlMapClientTemplate().insert(ST_CREATE, account);
    }

}
