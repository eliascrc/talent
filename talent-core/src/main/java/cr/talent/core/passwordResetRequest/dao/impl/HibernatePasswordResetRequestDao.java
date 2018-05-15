package cr.talent.core.passwordResetRequest.dao.impl;

import cr.talent.core.passwordResetRequest.dao.PasswordResetRequestDao;
import cr.talent.model.PasswordResetRequest;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;


/**
 * Hibernate implementation of the {@link cr.talent.core.passwordResetRequest.dao.PasswordResetRequestDao}.
 *
 * @author María José Cubero
 */
@Repository("passwordResetRequestDao")
public class HibernatePasswordResetRequestDao extends HibernateCrudDao<PasswordResetRequest, String> implements PasswordResetRequestDao {

    @Autowired
    public HibernatePasswordResetRequestDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public PasswordResetRequest findByToken(String token) {
        String hql = "FROM PasswordResetRequest WHERE token = ?1";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(1, token);

        return (PasswordResetRequest) DataAccessUtils.singleResult(query.list());
    }

    @Override
    public PasswordResetRequest findByEmail(String email) {
        String hql = "FROM PasswordResetRequest WHERE email = ?1 AND isValid = 'true'";
        Query query = this.getSessionFactory().getCurrentSession().createQuery(hql);
        query.setParameter(1, email);

        return (PasswordResetRequest) DataAccessUtils.singleResult(query.list());
    }
}
