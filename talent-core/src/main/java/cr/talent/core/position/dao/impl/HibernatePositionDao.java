package cr.talent.core.position.dao.impl;

import cr.talent.core.position.dao.PositionDao;
import cr.talent.model.Position;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.position.dao.PositionDao}.
 *
 * @author Elías Calderón
 */
@Repository("positionDao")
public class HibernatePositionDao extends HibernateCrudDao<Position, String> implements PositionDao {

    @Autowired
    public HibernatePositionDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
