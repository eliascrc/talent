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

    @Override
    public Capability getOrganizationCapabilityByName(String organizationId, String name) {
        String sql = "SELECT * FROM capability WHERE name = :name AND organization_id = :organizationId";
        Query query = super.getSessionFactory().getCurrentSession().createNativeQuery(sql).addEntity(Capability.class);
        query.setParameter("name", name);
        query.setParameter("organizationId", organizationId);
        List<Capability> capabilityResult = (List<Capability>)query.list();

        if (capabilityResult.size() > 0)
            return DataAccessUtils.singleResult(capabilityResult);

        return null;
    }

    @Override
    public Capability getPredefinedCapabilityByName(String name) {
        String sql = "SELECT * FROM capability WHERE name = :name AND organization_id IS NULL";
        Query query = super.getSessionFactory().getCurrentSession().createNativeQuery(sql).addEntity(Capability.class);
        query.setParameter("name", name);
        List<Capability> capabilityResult = (List<Capability>)query.list();

        if (capabilityResult.size() > 0)
            return DataAccessUtils.singleResult(capabilityResult);

        return null;
    }
}
