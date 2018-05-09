package cr.talent.core.capability.dao.impl;

import cr.talent.core.capability.dao.CapabilityDao;
import cr.talent.model.Capability;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate implementation of the {@link cr.talent.core.capability.dao.CapabilityDao}.
 *
 * @author Elías Calderón
 */
@Repository("capabilityDao")
public class HibernateCapabilityDao extends HibernateCrudDao<Capability, String> implements CapabilityDao {

    @Autowired
    public HibernateCapabilityDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    /**
     * @see cr.talent.core.capability.dao.CapabilityDao#getOrganizationCapabilityByName(String, String)
     */
    @Override
    public Capability getOrganizationCapabilityByName(String organizationId, String name) {

        Query query = super.getSessionFactory().getCurrentSession()
                .createQuery("from Capability capability join fetch capability.organization organization " +
                        "where capability.name = :name and organization.id = :organizationId");

        query.setParameter("name", name);
        query.setParameter("organizationId", organizationId);
        List<Capability> capabilityResult = (List<Capability>)query.list();

        return DataAccessUtils.singleResult(capabilityResult);

    }

    /**
     * @see cr.talent.core.capability.dao.CapabilityDao#getPredefinedCapabilityByName(String)
     */
    @Override
    public Capability getPredefinedCapabilityByName(String name) {

        Query query = super.getSessionFactory().getCurrentSession()
                .createQuery("from Capability where name = :name and organization is null");

        query.setParameter("name", name);
        List<Capability> capabilityResult = (List<Capability>)query.list();

        return DataAccessUtils.singleResult(capabilityResult);

    }
}
