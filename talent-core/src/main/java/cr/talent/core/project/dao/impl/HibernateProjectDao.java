package cr.talent.core.project.dao.impl;

import cr.talent.core.project.dao.ProjectDao;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.project.dao.ProjectDao}.
 *
 * @author Elías Calderón
 */
@Repository("projectDao")
public class HibernateProjectDao extends HibernateCrudDao<Project, String> implements ProjectDao {

    @Autowired
    public HibernateProjectDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
