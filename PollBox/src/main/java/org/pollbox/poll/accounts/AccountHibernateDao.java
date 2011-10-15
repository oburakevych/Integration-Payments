package org.pollbox.poll.accounts;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.pollbox.poll.accounts.Account;
import org.pollbox.poll.owners.Owner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AccountHibernateDao implements AccountDao {
    @Autowired(required = true)
    SessionFactory sessionFactory;

    public Account getAccountById(Long id) {
        Query query = this.sessionFactory.getCurrentSession().createQuery("from Account account where account.id = ?")
                .setParameter(0, id);

        return (Account) query.uniqueResult();
    }

    //TODO: implement correct query
    /**
     * <b>Not implemented yet!</b>
     */
    public Account getAccount(Owner owner) {
        return null;
    }
    
    //TODO: implement correct query
    /**
     * <b>Not implemented yet!</b>
     */
    public Account getAccount(String username) {
        return null;
    }

    public void save(Account account) {
        this.sessionFactory.getCurrentSession().saveOrUpdate(account);
    }

    public void delete(Account account) {
        this.sessionFactory.getCurrentSession().delete(account);
    }
}
