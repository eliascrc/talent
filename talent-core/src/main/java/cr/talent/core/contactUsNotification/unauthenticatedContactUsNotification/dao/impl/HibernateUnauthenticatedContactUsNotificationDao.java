package cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.dao.impl;

import cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.dao.UnauthenticatedContactUsNotificationDao;
import cr.talent.model.UnauthenticatedContactUsNotification;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.dao.UnauthenticatedContactUsNotificationDao}.
 *
 * @author Fabián Roberto Leandro
 */
@Repository("unauthenticatedContactUsNotificationDao")
public class HibernateUnauthenticatedContactUsNotificationDao extends HibernateCrudDao<UnauthenticatedContactUsNotification, String> implements UnauthenticatedContactUsNotificationDao {

    @Autowired
    public HibernateUnauthenticatedContactUsNotificationDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
