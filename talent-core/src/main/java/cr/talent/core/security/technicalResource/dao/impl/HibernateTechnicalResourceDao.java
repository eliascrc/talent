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
     * @param sessionFactory The SessionFactory instance which is autowired by Spring.
     */
    @Autowired
    public HibernateTechnicalResourceDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    /**
     * Finds a technical resource by its username. It first creates a SQL query and specifies the username
     * restriction by inserting it as a parameter to the query.
     * @param username The technical resource's username to search for.
     * @return The TechnicalResource with the respective username.
     */
    @Override
    @SuppressWarnings("unchecked")
    public TechnicalResource findTechnicalResourceByUsername(String username) {
        String sql = "SELECT * FROM technical_resources WHERE username = ?";
        Query query = super.getSessionFactory().getCurrentSession().createNativeQuery(sql).addEntity(TechnicalResource.class).setParameter(0, username);
        return (TechnicalResource) DataAccessUtils.singleResult(query.list());
    }

}
