package cr.talent.support.hibernate;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;

/**
 * Custom Implementation of the {@link OpenSessionInViewInterceptor} that sets the flush mode to
 * {@code FLUSH_AUTO}
 *
 * @author Rodrigo A. Bartels
 */
public class HibernateSessionInterceptor extends OpenSessionInViewInterceptor {

    /**
     * Open a Session for the SessionFactory that this interceptor uses.
     * <p>The default implementation delegates to the
     * {@code SessionFactory.openSession} method and
     * sets the {@code Session}'s flush mode to "MANUAL".
     *
     * @return the Session to use
     * @throws DataAccessResourceFailureException if the Session could not be created
     * @see FlushMode#MANUAL
     */
    protected Session openSession() throws DataAccessResourceFailureException {
        try {
            Session session = getSessionFactory().openSession();
            session.setFlushMode(FlushMode.AUTO);
            return session;
        } catch (HibernateException ex) {
            throw new DataAccessResourceFailureException("Could not open Hibernate Session", ex);
        }
    }
}
