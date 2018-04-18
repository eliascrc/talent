package cr.talent.core.capabilityLevel.dao.impl;

import cr.talent.core.capabilityLevel.dao.CapabilityLevelDao;
import cr.talent.model.CapabilityLevel;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.capabilityLevel.dao.CapabilityLevelDao}.
 *
 * @author Elías Calderón
 */
@Repository("capabilityLevelDao")
public class HibernateCapabilityLevelDao extends HibernateCrudDao<CapabilityLevel, String> implements CapabilityLevelDao {

    @Autowired
    public HibernateCapabilityLevelDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
