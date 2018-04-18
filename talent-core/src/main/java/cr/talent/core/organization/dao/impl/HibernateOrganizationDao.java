package cr.talent.core.organization.dao.impl;

import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.organization.dao.OrganizationDao}.
 *
 * @author Elías Calderón
 */
@Repository("organizationDao")
public class HibernateOrganizationDao extends HibernateCrudDao<Organization, String> implements OrganizationDao {

    @Autowired
    public HibernateOrganizationDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
