package org.pollbox.poll.owners;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class OwnerHibernateDao implements OwnerDao {
	@Autowired(required = true)
	SessionFactory sessionFactory;

	public Owner getOwnerById(Long id) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Owner owner where owner.id = ?").setParameter(0, id);
		
		return (Owner) query.uniqueResult();
	}

	public Owner getOwnerByUsername(String username) {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Owner owner where owner.username = ?").setParameter(0, username);
		
		return (Owner) query.uniqueResult();	}

	public Collection<Owner> getOwners() {
		Query query = this.sessionFactory.getCurrentSession().createQuery("from Owner owner");
		
		return query.list();
	}

	public void save(Owner owner) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(owner);
	}
	
	public void delete(Owner owner) {
		this.sessionFactory.getCurrentSession().delete(owner);
	}
}
