package org.pollbox.conf.language;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class LanguageHibernateDao implements LanguageDao {
    @Autowired(required = true)
    SessionFactory sessionFactory;

    @Override
    public Language getLanguage(Long id) {
        return (Language) this.sessionFactory.getCurrentSession().createCriteria(Language.class)
                .add(Restrictions.idEq(id)).uniqueResult();
    }

    @Override
    public Language getLanguage(String code) {
        List<Language> languages = this.sessionFactory.getCurrentSession().createCriteria(Language.class)
                .add(Restrictions.eq("code", code)).list();

        return languages.size() > 0 ? languages.get(0) : null;
    }

    @Override
    public Language getLanguage(String name, String code) {
        return (Language) this.sessionFactory.getCurrentSession().createCriteria(Language.class)
                .add(Restrictions.eq("code", code)).add(Restrictions.eq("name", name)).uniqueResult();
    }

    @Override
    public void save(Language language) {
        this.sessionFactory.getCurrentSession().merge(language);
    }

    @Override
    public void delete(Language language) {
        this.sessionFactory.getCurrentSession().delete(language);
    }

}
