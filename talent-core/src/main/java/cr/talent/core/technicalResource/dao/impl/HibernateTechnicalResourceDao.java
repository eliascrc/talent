package cr.talent.core.technicalResource.dao.impl;

import cr.talent.core.technicalResource.dao.TechnicalResourceDao;
import cr.talent.model.TechnicalResource;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.technicalResource.dao.TechnicalResourceDao}.
 *
 * @author Daniel Montes de Oca
 */
@Repository("technicalResourceDao")
public class HibernateTechnicalResourceDao extends HibernateCrudDao<TechnicalResource, String> implements TechnicalResourceDao {

    @Autowired
    public HibernateTechnicalResourceDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
