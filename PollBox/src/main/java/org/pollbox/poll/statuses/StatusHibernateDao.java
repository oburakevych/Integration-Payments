package org.pollbox.poll.statuses;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class StatusHibernateDao implements StatusDao {
    @Autowired(required = true)
    private SessionFactory sessionFactory;

    @Override
    public Status getById(Long id) {
        return (Status) this.sessionFactory.getCurrentSession().createCriteria(Status.class).add(Restrictions.idEq(id))
                .uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Status> getStatuses() {
        return this.sessionFactory.getCurrentSession().createCriteria(Status.class).list();
    }

    @Override
    public Status save(Status status) {
        return (Status) this.sessionFactory.getCurrentSession().merge(status);
    }

    @Override
    public void delete(Status status) {
        this.sessionFactory.getCurrentSession().delete(status);
    }

    @Override
    public Status getStatus(String name, String type) {
        return (Status) this.sessionFactory.getCurrentSession().createCriteria(Status.class).add(Restrictions.eq("NAME", name))
            .add(Restrictions.eq("TYPE", type)).uniqueResult();
    }

}
