package cr.talent.core.feedback.dao.impl;

import cr.talent.core.feedback.dao.FeedbackDao;
import cr.talent.model.Feedback;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.feedback.dao.FeedbackDao}.
 *
 * @author Otto Mena
 */
@Repository("feedbackDao")
public class HibernateFeedbackDao extends HibernateCrudDao<Feedback, String> implements FeedbackDao {

    @Autowired
    public HibernateFeedbackDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
