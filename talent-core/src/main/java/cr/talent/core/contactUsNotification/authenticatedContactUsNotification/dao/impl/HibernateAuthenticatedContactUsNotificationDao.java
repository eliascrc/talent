package cr.talent.core.contactUsNotification.authenticatedContactUsNotification.dao.impl;

import cr.talent.core.contactUsNotification.authenticatedContactUsNotification.dao.AuthenticatedContactUsNotificationDao;
import cr.talent.model.AuthenticatedContactUsNotification;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.contactUsNotification.authenticatedContactUsNotification.dao.AuthenticatedContactUsNotificationDao}.
 *
 * @author Fabián Roberto Leandro
 */
@Repository("authenticatedContactUsNotificationDao")
public class HibernateAuthenticatedContactUsNotificationDao extends HibernateCrudDao<AuthenticatedContactUsNotification, String> implements AuthenticatedContactUsNotificationDao {

    @Autowired
    public HibernateAuthenticatedContactUsNotificationDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
