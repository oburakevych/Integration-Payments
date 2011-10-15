package org.pollbox.poll.projects;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class ProjectHibernateDao implements ProjectDao {
    @Autowired(required = true)
    SessionFactory sessionFactory;

    public Project getById(Long id) {
        return (Project) this.sessionFactory.getCurrentSession().createCriteria(Project.class)
                .add(Restrictions.idEq(id)).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public Collection<Project> getProjects() {
        return this.sessionFactory.getCurrentSession().createCriteria(Project.class).list();
    }

    public Project save(Project project) {
        return (Project) this.sessionFactory.getCurrentSession().merge(project);
    }

    public void delete(Project project) {
        this.sessionFactory.getCurrentSession().delete(project);
    }
}
