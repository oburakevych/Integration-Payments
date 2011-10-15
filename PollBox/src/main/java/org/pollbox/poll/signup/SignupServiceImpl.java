package org.pollbox.poll.signup;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import org.pollbox.conf.language.Language;
import org.pollbox.conf.language.LanguageDef;
import org.pollbox.poll.accounts.Account;
import org.pollbox.poll.owners.Owner;
import org.pollbox.poll.auth.Authority;
import org.pollbox.poll.auth.Role;
import org.pollbox.poll.managers.DaoManager;
import org.pollbox.poll.statuses.Status;
import org.pollbox.poll.statuses.StatusDef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SignupServiceImpl implements SignupService {
    private Logger log = Logger.getLogger(getClass().getName());
    
    @Autowired(required = true)
    DaoManager daoManager;
    
    @Autowired(required = true)
    PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Signup signupUser(Signup signup) {      
        daoManager.getOwnerDao().save(signup.getOwner());
        log.info("New owner has been created: \n" + signup.getOwner());
        
        daoManager.getAccountDao().save(signup.getAccount());
        log.info("New account has been created: \n" + signup.getAccount());
        
        daoManager.getAuthorityDao().save(signup.getAuthority());
        log.info("New authority has been created: \n" + signup.getAuthority());
        
        return signup;
    }

    @Override
    public Signup bindSignupData(Owner owner, Account account, Authority authority) {
        if (authority == null) {
            authority = new Authority();
        }
        
        authority.setUsername(owner.getUsername());
        
        if (authority.getAuthority() == null) {
            authority.setAuthority(Role.DEFAULT.getCode());
        }
        
        if (owner.getLanguage().getId() != null) {
            Language language = LanguageDef.getLanguage(owner.getLanguage().getId());
            owner.setLanguage(language);
        }
        
        account.setStatus(getDefaultSignupStatus());
        account.setDateCreated(Calendar.getInstance());
        
        owner.setAccount(account);
        owner.setCreatedDate(Calendar.getInstance());
        owner.setEnabled(true);
        owner.setTypeId(1L);
        
        String password = passwordEncoder.encodePassword(owner.getPassword(), owner.getUsername());
        
        owner.setPassword(password);
        
        Set<Owner> owners = new HashSet<Owner>();
        
        owners.add(owner);
        
        account.setOwners(owners);
        
        Signup signup = new Signup(owner, account, authority);
        
        return signup;
    }
    
    public Status getDefaultSignupStatus() {
        return new Status(StatusDef.INITIAL_ACCOUNT);
    }
}
