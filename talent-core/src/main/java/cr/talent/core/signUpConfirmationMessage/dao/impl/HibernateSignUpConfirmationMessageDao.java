package cr.talent.core.signUpConfirmationMessage.dao.impl;

import cr.talent.core.signUpConfirmationMessage.dao.SignUpConfirmationMessageDao;
import cr.talent.model.SignUpConfirmationMessage;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hibernate implementation of the {@link cr.talent.core.signUpConfirmationMessage.dao.SignUpConfirmationMessageDao}.
 *
 * @author Daniel Montes de Oca
 */
@Repository("signUpConfirmationMessageDao")
public class HibernateSignUpConfirmationMessageDao extends HibernateCrudDao<SignUpConfirmationMessage, String> implements SignUpConfirmationMessageDao {

    @Autowired
    public HibernateSignUpConfirmationMessageDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }


    public SignUpConfirmationMessage getActiveConfirmationMessage(String username) {
        Query query = super.getSessionFactory().getCurrentSession()
                .createQuery("FROM SignUpConfirmationMessage WHERE technicalResource.username = :username");

        query.setParameter("username", username);
        List<SignUpConfirmationMessage> signUpConfirmationMessageResult = (List<SignUpConfirmationMessage>) query.list();

        return DataAccessUtils.singleResult(signUpConfirmationMessageResult);
    }

}
