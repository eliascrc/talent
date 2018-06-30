package cr.talent.core.leadPosition.dao.impl;

import cr.talent.core.leadPosition.dao.LeadPositionDao;
import cr.talent.model.LeadPosition;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link LeadPositionDao}.
 *
 * @author Josue Cubero
 */
@Repository("leadPositionDao")
public class HibernateLeadPositionDao extends HibernateCrudDao<LeadPosition, String> implements cr.talent.core.leadPosition.dao.LeadPositionDao {

    @Autowired
    public HibernateLeadPositionDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
