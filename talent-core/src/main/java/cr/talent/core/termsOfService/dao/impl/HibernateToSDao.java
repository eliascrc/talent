package cr.talent.core.termsOfService.dao.impl;

import cr.talent.core.termsOfService.dao.ToSDao;
import cr.talent.model.TermsOfService;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate data access object implementation of {@link ToSDao} .
 *
 * @author Josué León Sarkis
 */
@Repository("termsOfServiceDao")
public class HibernateToSDao extends HibernateCrudDao<TermsOfService, String> implements ToSDao {

    /**
     * Constructor of the HibernateToSDao data access object which sets the HibernateCrudDao's
     * session factory to obtain session instances from.
     *
     * @param sessionFactory The SessionFactory instance which is autowired by Spring.
     */
    @Autowired
    public HibernateToSDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public TermsOfService getActiveTermsOfService() {
        Query query = super.getSessionFactory().getCurrentSession()
                .createQuery("FROM TermsOfService WHERE isActive = true");
        return (TermsOfService) query.getSingleResult();
    }

}
