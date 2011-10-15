package org.pollbox.poll.owners;

import java.util.Collection;

import org.pollbox.conf.language.Language;
import org.pollbox.conf.language.LanguageDef;
import org.pollbox.poll.auth.SecurityUtil;
import org.pollbox.poll.auth.User;
import org.pollbox.poll.managers.DaoManager;
import org.pollbox.poll.statuses.Status;
import org.pollbox.poll.statuses.StatusDef;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;


@Service
public class OwnersServiceImpl implements OwnersService {
    @Autowired(required = true)
    DaoManager daoManager;

    @Autowired
    Validator validator;
    
    @Transactional(readOnly = true)
    public Owner getOwnerById(Long id) {
        return this.daoManager.getOwnerDao().getOwnerById(id);
    }

    @Transactional(readOnly = true)
    public Owner getOwnerByUsername(String username) {
        return this.daoManager.getOwnerDao().getOwnerByUsername(username);
    }

    @Transactional(readOnly = true)
    public OwnerList getOwners() {
        Collection<Owner> owners = this.daoManager.getOwnerDao().getOwners();

        return new OwnerList(owners);
    }

    @Transactional
    public void save(Owner owner) {
        Status status = StatusDef.getStatus(owner.getAccount().getStatus().getId());
        Language language = LanguageDef.getLanguage(owner.getLanguage().getId());
        
        owner.getAccount().setStatus(status);
        owner.setLanguage(language);
        
        this.daoManager.getOwnerDao().save(owner);
        
        //Update authenticated user
        SecurityUtil.getAuthenticatedUser().setOwner(owner); 
    }

    @Transactional
    public void delete(Owner owner) {
        this.daoManager.getOwnerDao().delete(owner);

    }
}
