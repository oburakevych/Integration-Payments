package org.pollbox.poll.accounts;

import org.pollbox.poll.owners.Owner;
import org.pollbox.poll.managers.DaoManager;
import org.pollbox.poll.statuses.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccountsServiceImpl implements AccountsService {
    @Autowired(required = true)
    DaoManager daoManager;

    @Transactional(readOnly = true)
    public Account getAccountById(Long id) {
        return this.daoManager.getAccountDao().getAccountById(id);
    }

    @Transactional(readOnly = true)
    public Account getAccount(Owner owner) {
        Account account = this.daoManager.getAccountDao().getAccount(owner);
        
        return account;
    }
    
    @Transactional(readOnly = true)
    public Account getAccount(String username) {
        Account account = this.daoManager.getAccountDao().getAccount(username);
        
        return account;
    }

    @Transactional
    public void save(Account account) {
        this.daoManager.getAccountDao().save(account);
    }

    @Transactional
    public void delete(Account account) {
        account.setStatus(null);
        
        save(account);
    }

}
