package cr.talent.core.projectPositionHolder.dao.impl;

import cr.talent.core.projectPositionHolder.dao.ProjectPositionHolderDao;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.projectPositionHolder.dao.ProjectPositionHolderDao}.
 *
 * @author Daniel Montes de Oca
 */
@Repository("projectPositionHolderDao")
public class HibernateProjectPositionHolderDao extends HibernateCrudDao<ProjectPositionHolder, String> implements ProjectPositionHolderDao {

    @Autowired
    public HibernateProjectPositionHolderDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
