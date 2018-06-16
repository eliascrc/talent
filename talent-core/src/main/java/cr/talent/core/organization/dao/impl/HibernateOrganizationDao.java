package cr.talent.core.organization.dao.impl;

import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.model.Invitation;
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

        Query query = super.getSessionFactory().getCurrentSession()
                .createQuery("from Organization where uniqueIdentifier = :uniqueIdentifier");

        query.setParameter("uniqueIdentifier", uniqueIdentifier);
        List<Organization> organizationResult = (List<Organization>)query.list();

        return DataAccessUtils.singleResult(organizationResult);
    }

    /**
     * @see cr.talent.core.organization.dao.OrganizationDao#findValidInvitations(String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Invitation> findValidInvitations(String uniqueIdentifier) {
        String hql = "FROM Organization AS org INNER JOIN org.invitationsList AS invitation " +
                "WHERE org.uniqueIdentifier = :uniqueIdentifier AND invitation.isValid = true";
        Query query = super.getSessionFactory().getCurrentSession().createQuery(hql);

        query.setParameter("uniqueIdentifier", uniqueIdentifier);
        return (List<Invitation>)query.list();
    }

}