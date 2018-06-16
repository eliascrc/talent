package cr.talent.core.invitation.dao.impl;

import cr.talent.core.invitation.dao.InvitationDao;
import cr.talent.model.Invitation;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate implementation of the {@link cr.talent.core.invitation.dao.InvitationDao}.
 *
 * @author Elias Calderon
 */
@Repository("invitationDao")
public class HibernateInvitationDao extends HibernateCrudDao<Invitation, String> implements InvitationDao {

    @Autowired
    public HibernateInvitationDao(@Qualifier("sessionFactory") SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    /**
     * @see cr.talent.core.invitation.dao.InvitationDao#findInvitationByEmail(String)
     */
    @Override
    @SuppressWarnings("unchecked")
    public Invitation findInvitationByEmail(String email) {
        String hql = "FROM Invitation WHERE email = :email AND isValid = true";
        Query query = super.getSessionFactory().getCurrentSession().createQuery(hql);

        query.setParameter("email", email);
        List<Invitation> invitationResult = (List<Invitation>)query.list();

        return DataAccessUtils.singleResult(invitationResult);
    }

}
