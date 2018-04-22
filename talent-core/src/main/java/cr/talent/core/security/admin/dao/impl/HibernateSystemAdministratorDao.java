package cr.talent.core.security.admin.dao.impl;

import cr.talent.core.security.admin.dao.SystemAdministratorDao;
import cr.talent.support.dao.impl.HibernateCrudDao;
import cr.talent.model.SystemAdministrator;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate data access object implementation of {@link SystemAdministratorDao} .
 *
 * @author Josué León Sarkis
 */
@Repository("systemAdministratorDao")
public class HibernateSystemAdministratorDao extends HibernateCrudDao<SystemAdministrator, String> implements SystemAdministratorDao {

    /**
     * Constructor of the HibernateSystemAdministrator data access object which sets the HibernateCrudDao's
     * session factory to obtain session instances from.
     * @param sessionFactory The SessionFactory instance which is autowired by Spring.
     */
    @Autowired
    public HibernateSystemAdministratorDao(@Qualifier("sessionFactory") SessionFactory sessionFactory){
        setSessionFactory(sessionFactory);
    }

    /**
     * Finds a system administrator by its username. It first creates a SQL query and specifies the username
     * restriction by by inserting it as a parameter to the query.
     * @param username The system administrator's username to search for.
     * @return The SystemAdministrator with the respective username.
     */
    @Override
    @SuppressWarnings("unchecked")
    public SystemAdministrator findSysAdminByUsername(String username) {
        String sql = "SELECT * FROM administrator WHERE username = ?1";
        Query query = super.getSessionFactory().getCurrentSession().createNativeQuery(sql).addEntity(SystemAdministrator.class).setParameter(1, username);
        return (SystemAdministrator) DataAccessUtils.singleResult(query.list());
    }
}
