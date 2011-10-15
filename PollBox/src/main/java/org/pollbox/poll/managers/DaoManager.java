package org.pollbox.poll.managers;

import org.pollbox.conf.language.LanguageDao;
import org.pollbox.poll.accounts.AccountDao;
import org.pollbox.poll.owners.OwnerDao;
import org.pollbox.poll.auth.AuthorityDao;
import org.pollbox.poll.projects.ProjectDao;
import org.pollbox.poll.statuses.StatusDao;

import org.springframework.beans.factory.annotation.Autowired;


public class DaoManager {
    @Autowired
    private ProjectDao projectDao;
    
    @Autowired
    private OwnerDao ownerDao;

    @Autowired
    private AccountDao accountDao;
    
    @Autowired
    private StatusDao statusDao;
    
    @Autowired
    private AuthorityDao authorityDao;
    
    @Autowired
    private LanguageDao languageDao;

    public StatusDao getStatusDao() {
        return statusDao;
    }

    public void setStatusDao(StatusDao statusDao) {
        this.statusDao = statusDao;
    }

    public AccountDao getAccountDao() {
        return accountDao;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public OwnerDao getOwnerDao() {
        return ownerDao;
    }

    public void setOwnerDao(OwnerDao ownerDao) {
        this.ownerDao = ownerDao;
    }

    public ProjectDao getProjectDao() {
        return projectDao;
    }

    public void setProjectDao(ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    public AuthorityDao getAuthorityDao() {
        return authorityDao;
    }

    public void setAuthorityDao(AuthorityDao authorityDao) {
        this.authorityDao = authorityDao;
    }

    public void setLanguageDao(LanguageDao languageDao) {
        this.languageDao = languageDao;
    }

    public LanguageDao getLanguageDao() {
        return languageDao;
    }

}
