package cr.talent.core.organization.dao.impl;

import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.model.Organization;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    /**
     * @see cr.talent.core.organization.dao.OrganizationDao#getOrganizationByUniqueIdentifier(String)
     */
    @Override
    public Organization getOrganizationByUniqueIdentifier(String uniqueIdentifier) {
        String sql = "SELECT * FROM organization WHERE unique_identifier = :uniqueIdentifier";
        Query query = super.getSessionFactory().getCurrentSession().createNativeQuery(sql).addEntity(Organization.class);
        query.setParameter("uniqueIdentifier", uniqueIdentifier);
        List<Organization> organizationResult = (List<Organization>)query.list();

        if (organizationResult.size() > 0)
            return DataAccessUtils.singleResult(organizationResult);

        return null;
    }

}
