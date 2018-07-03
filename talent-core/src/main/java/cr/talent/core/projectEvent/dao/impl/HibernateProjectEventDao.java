package cr.talent.core.projectEvent.dao.impl;

import cr.talent.core.projectEvent.dao.ProjectEventDao;
import cr.talent.model.ProjectEvent;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.projectEvent.dao.ProjectEventDao}.
 *
 * @author Otto Mena
 */
@Repository("projectEventDao")
public class HibernateProjectEventDao extends HibernateCrudDao<ProjectEvent, String> implements ProjectEventDao {

    @Autowired
    public HibernateProjectEventDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}