package cr.talent.core.capability.dao.impl;

import cr.talent.core.capability.dao.CapabilityDao;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

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

}
