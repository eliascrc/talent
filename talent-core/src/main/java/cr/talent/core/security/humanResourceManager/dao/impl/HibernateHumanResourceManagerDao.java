package cr.talent.core.security.humanResourceManager.dao.impl;

import cr.talent.core.security.humanResourceManager.dao.HumanResourceManagerDao;
import cr.talent.model.HumanResourceManager;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


/**
 * Hibernate implementation of the {@link cr.talent.core.security.humanResourceManager.dao.HumanResourceManagerDao}.
 *
 * @author Otto Mena
 */
@Repository("humanResourceManagerDao")
public class HibernateHumanResourceManagerDao extends HibernateCrudDao<HumanResourceManager, String> implements HumanResourceManagerDao {

    @Autowired
    public HibernateHumanResourceManagerDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}