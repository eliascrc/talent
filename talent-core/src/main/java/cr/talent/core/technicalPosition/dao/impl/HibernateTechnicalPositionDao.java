package cr.talent.core.technicalPosition.dao.impl;

import cr.talent.core.technicalPosition.dao.TechnicalPositionDao;
import cr.talent.model.TechnicalPosition;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.technicalPosition.dao.TechnicalPositionDao}.
 *
 * @author Elías Calderón
 */
@Repository("technicalPositionDao")
public class HibernateTechnicalPositionDao extends HibernateCrudDao<TechnicalPosition, String> implements TechnicalPositionDao {

    @Autowired
    public HibernateTechnicalPositionDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
