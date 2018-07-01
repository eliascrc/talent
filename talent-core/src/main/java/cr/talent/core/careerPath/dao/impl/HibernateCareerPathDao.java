package cr.talent.core.careerPath.dao.impl;

import cr.talent.core.careerPath.dao.CareerPathDao;
import cr.talent.model.CareerPath;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.careerPath.dao.CareerPathDao}.
 *
 * @author Fabián Roberto Leandro
 */
@Repository("careerPathDao")
public class HibernateCareerPathDao extends HibernateCrudDao<CareerPath, String> implements CareerPathDao {
    
    @Autowired
    public HibernateCareerPathDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
    
}
