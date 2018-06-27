package cr.talent.core.projectPositionHolder.dao.impl;

import cr.talent.core.projectPositionHolder.dao.ProjectPositionHolderDao;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class HibernateProjectPositionHolderDao extends HibernateCrudDao<ProjectPositionHolder, String> implements ProjectPositionHolderDao {

    @Autowired
    public HibernateProjectPositionHolderDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
