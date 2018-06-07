package cr.talent.core.termsOfService.dao.impl;

import cr.talent.core.termsOfService.dao.ToSDao;
import cr.talent.model.Platform;
import cr.talent.model.TermsOfService;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

/**
 * Hibernate data access object implementation of {@link cr.talent.core.termsOfService.dao.ToSDao} .
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

    /**
     * @see cr.talent.core.termsOfService.dao.ToSDao#getActiveTermsOfService(Platform)
     */
    @Override
    public TermsOfService getActiveTermsOfService(Platform requestPlatform) {
        String platformName = requestPlatform.name();
        Query query = super.getSessionFactory().getCurrentSession()
                .createQuery("FROM TermsOfService WHERE isActive = true and platform = '" + platformName + "'");
        List<TermsOfService> termsOfServiceResult = (List<TermsOfService>) query.list();

        return DataAccessUtils.singleResult(termsOfServiceResult);
    }
}
