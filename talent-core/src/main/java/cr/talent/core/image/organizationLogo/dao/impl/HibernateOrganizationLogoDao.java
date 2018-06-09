package cr.talent.core.image.organizationLogo.dao.impl;

import cr.talent.core.image.organizationLogo.dao.OrganizationLogoDao;
import cr.talent.model.OrganizationLogo;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

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

}
