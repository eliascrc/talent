package cr.talent.core.language.dao.impl;

import cr.talent.core.language.dao.LanguageDao;
import cr.talent.model.Language;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.language.dao.LanguageDao}.
 *
 * @author Elías Calderón
 */
@Repository("languageDao")
public class HibernateLanguageDao extends HibernateCrudDao<Language, String> implements LanguageDao {

    @Autowired
    public HibernateLanguageDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
