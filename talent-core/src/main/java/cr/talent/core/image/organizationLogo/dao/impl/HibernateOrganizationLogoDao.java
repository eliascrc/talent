package cr.talent.core.image.organizationLogo.dao.impl;

import cr.talent.core.image.organizationLogo.dao.OrganizationLogoDao;
import cr.talent.model.Organization;
import cr.talent.model.OrganizationLogo;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate implementation of the {@link OrganizationLogoDao}.
 *
 * @author María José Cubero
 */
@Repository("organizationLogoDao")
public class HibernateOrganizationLogoDao extends HibernateCrudDao<OrganizationLogo, String> implements OrganizationLogoDao {

    @Autowired
    public HibernateOrganizationLogoDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    /**
     * @see cr.talent.core.image.organizationLogo.dao.OrganizationLogoDao#findLogoByLink(String)
     */
    @Override
    public OrganizationLogo findLogoByLink(String link) {
        Query query = super.getSessionFactory().getCurrentSession()
                .createQuery("from OrganizationLogo where link = :link");

        query.setParameter("link", link);
        List<OrganizationLogo> logoResult = (List<OrganizationLogo>)query.list();

        return DataAccessUtils.singleResult(logoResult);
    }

}
