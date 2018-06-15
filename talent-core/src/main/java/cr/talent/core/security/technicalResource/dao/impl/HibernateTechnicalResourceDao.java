package cr.talent.core.security.technicalResource.dao.impl;


import cr.talent.core.security.technicalResource.dao.TechnicalResourceDao;
import cr.talent.support.dao.impl.HibernateCrudDao;
import cr.talent.model.TechnicalResource;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

/**
 * Hibernate data access object implementation of {@link TechnicalResourceDao} .
 *
 * @author Josué León Sarkis
 */
@Repository("technicalResourceDao")
public class HibernateTechnicalResourceDao extends HibernateCrudDao<TechnicalResource, String> implements TechnicalResourceDao {

    /**
     * Constructor of the HibernateTechnicalResource data access object which sets the HibernateCrudDao's
     * session factory to obtain session instances from.
     *
     * @param sessionFactory The SessionFactory instance which is autowired by Spring.
     */
    @Autowired
    public HibernateTechnicalResourceDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    /**
     * @see cr.talent.core.security.technicalResource.dao.TechnicalResourceDao#findTechnicalResourceByUsernameAndOrganizationIdentifier(String,String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public TechnicalResource findTechnicalResourceByUsernameAndOrganizationIdentifier(String username,
                                                                                      String organizationIdentifier) {
        String hql = "FROM TechnicalResource WHERE username = ?1 AND  organization.uniqueIdentifier = ?2";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(1, username);
        query.setParameter(2, organizationIdentifier);

        return (TechnicalResource) DataAccessUtils.singleResult(query.list());
    }

    /**
     * @see cr.talent.core.security.technicalResource.dao.TechnicalResourceDao#findByAuthenticationToken(String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public TechnicalResource findByAuthenticationToken(String token) {
        String hql = "FROM TechnicalResource WHERE token = ?1";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(1, token);
        return (TechnicalResource) DataAccessUtils.singleResult(query.list());
    }

}
