package org.pollbox.poll.auth;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AuthorityHibernateDao implements AuthorityDao {
	@Autowired(required = true)
	SessionFactory sessionFactory;

    @Override
    public Authority getAuthority(Long id) {
        return (Authority) this.sessionFactory.getCurrentSession().createCriteria(Authority.class).
            add(Restrictions.idEq(id)).uniqueResult();
    }

    @Override
    public Authority getAuthority(String username) {
        return (Authority) this.sessionFactory.getCurrentSession().createCriteria(Authority.class).
            add(Restrictions.eq("username", username)).uniqueResult();
    }

    @Override
    public Authority save(Authority authority) {
        return (Authority) this.sessionFactory.getCurrentSession().merge(authority);
    }

    @Override
    public void delete(Authority authority) {
        this.sessionFactory.getCurrentSession().delete(authority);
    }

}
