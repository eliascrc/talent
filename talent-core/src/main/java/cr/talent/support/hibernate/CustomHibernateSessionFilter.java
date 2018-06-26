package cr.talent.support.hibernate;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate5.support.OpenSessionInViewFilter;

/**
 * Custom implementation of the {@link OpenSessionInViewFilter} pattern that binds the flush mode to
 * the transaction commit event.
 *
 * @author Rodrigo A. Bartels
 * @version 1.0
 * @since 8/5/11
 */
public class CustomHibernateSessionFilter extends OpenSessionInViewFilter {

    /**
     * Open a Session for the SessionFactory that this filter uses.
     * <p>The default implementation delegates to the
     * {@code SessionFactory.openSession} method and
     * sets the {@code Session}'s flush mode to "MANUAL".
     *
     * @param sessionFactory the SessionFactory that this filter uses
     * @return the Session to use
     * @throws DataAccessResourceFailureException if the Session could not be created
     * @see FlushMode#MANUAL
     */
    protected Session openSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
        try {
            Session session = sessionFactory.openSession();
            session.setFlushMode(FlushMode.COMMIT);
            return session;
        } catch (HibernateException ex) {
            throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
        }
    }
}