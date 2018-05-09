package cr.talent.core.organization.dao.impl;

import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.model.Organization;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

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

    @Override
    public Organization getOrganizationWithUniqueId(String uniqueIdentifier) {
        Organization queryResult;
        try {
            Query query = super.getSessionFactory().getCurrentSession()
                    .createQuery("FROM Organization WHERE uniqueIdentifier = ?");
            query.setParameter(0, uniqueIdentifier);
            queryResult = (Organization) query.getSingleResult();
        } catch (NoResultException nre) {
            queryResult = null;
        }

        return queryResult;
    }
}
