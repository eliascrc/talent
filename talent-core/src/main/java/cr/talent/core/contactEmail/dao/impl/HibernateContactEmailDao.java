package cr.talent.core.contactEmail.dao.impl;

import cr.talent.core.contactEmail.dao.ContactEmailDao;
import cr.talent.model.ContactEmail;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.contactEmail.dao.ContactEmailDao}.
 *
 * @author Fabián Roberto Leandro
 */
@Repository("contactEmailDao")
public class HibernateContactEmailDao extends HibernateCrudDao<ContactEmail, String> implements ContactEmailDao {

    @Autowired
    public HibernateContactEmailDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
