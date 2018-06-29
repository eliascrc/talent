package cr.talent.core.projectPosition.dao.impl;

import cr.talent.core.projectPosition.dao.ProjectPositionDao;
import cr.talent.model.ProjectPosition;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.projectPosition.dao.ProjectPositionDao}.
 *
 * @author Elías Calderón
 */
@Repository("projectPositionDao")
public class HibernateProjectPositionDao extends HibernateCrudDao<ProjectPosition, String> implements ProjectPositionDao {

    @Autowired
    public HibernateProjectPositionDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
}