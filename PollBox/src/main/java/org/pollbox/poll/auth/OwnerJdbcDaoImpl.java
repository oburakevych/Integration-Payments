package org.pollbox.poll.auth;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;

import org.pollbox.conf.language.Language;
import org.pollbox.conf.locale.LocaleUtil;
import org.pollbox.poll.managers.ServiceManager;
import org.pollbox.poll.owners.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;


public class OwnerJdbcDaoImpl extends JdbcDaoImpl {
    @Autowired
    ServiceManager serviceManager;
    
    public static final String DEF_USERS_BY_USERNAME_QUERY =
        "select username, password, enabled " +
        "from owner " +
        "where username = ? ";
    
    public static final String DEF_ACCOUNTS_BY_USERNAME_QUERY =
        "select account.id from account, owner " +
        "where account.id = owner.account_id " +
        "  and owner.username = ?";
    
    private String accountIdByUsernameQuery;
    private boolean usernameBasedPrimaryKey = true;
    private boolean enableAccountId = true;
    private boolean enableOwner = true;
    private boolean enableLocale = true;
    
    public OwnerJdbcDaoImpl() {
        super();
        
        setUsersByUsernameQuery(DEF_USERS_BY_USERNAME_QUERY);
        setAccountIdByUsernameQuery(DEF_ACCOUNTS_BY_USERNAME_QUERY);
    }
    
    /**
     * Can be overridden to customize the creation of the final UserDetailsObject which is
     * returned by the <tt>loadUserByUsername</tt> method.
     *
     * @param username the name originally passed to loadUserByUsername
     * @param userFromUserQuery the object returned from the execution of the
     * @param combinedAuthorities the combined array of authorities from all the authority loading queries.
     * @return the final UserDetails which should be used in the system.
     */
    protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
            List<GrantedAuthority> combinedAuthorities) {
        String returnUsername = userFromUserQuery.getUsername();
       
        if (!usernameBasedPrimaryKey) {
            returnUsername = username;
        }
        
        Long accountId = null;
        
        if (enableAccountId) {
            List<Long> accountIds = loadAccountIdsByUsername(username);
            
            accountId = accountIds.get(0);
        }
        
        Owner owner = null;
        Locale locale = null;
        
        if (enableOwner) {
            owner = loadOwner(returnUsername);
            
            if (owner != null && enableLocale) {
                Language lang = owner.getLanguage();
                
                if (lang != null) {
                    locale = LocaleUtil.toLocale(lang.getCode());
                }
            }
        }

        return new User(returnUsername, userFromUserQuery.getPassword(), userFromUserQuery.isEnabled(),
                true, true, true, combinedAuthorities, accountId, owner, locale);
    }

    
    /**
     * Executes the SQL <tt>ownerIdByUsernameQuery</tt> and returns a list of String objects.
     * There should normally only be one matching user.
     */
    protected List<Long> loadAccountIdsByUsername(String username) {
        return getJdbcTemplate().query(accountIdByUsernameQuery, new String[] {username}, new RowMapper<Long>() {
            public Long mapRow(ResultSet rs, int rowNum) throws SQLException {
                Long accountId = rs.getLong(1);
                
                return accountId;
            }
        });
    }
    
    protected Owner loadOwner(String username) {
        return serviceManager.getOwnersService().getOwnerByUsername(username);
    }

    public boolean isEnableAccountId() {
        return enableAccountId;
    }

    public void setEnableAccountId(boolean enableAccountId) {
        this.enableAccountId = enableAccountId;
    }

    public String getAccountIdByUsernameQuery() {
        return accountIdByUsernameQuery;
    }

    public void setAccountIdByUsernameQuery(String accountIdByUsernameQuery) {
        this.accountIdByUsernameQuery = accountIdByUsernameQuery;
    }

    public void setEnableLocale(boolean enableLocale) {
        this.enableLocale = enableLocale;
    }

    public boolean isEnableLocale() {
        return enableLocale;
    }

}
