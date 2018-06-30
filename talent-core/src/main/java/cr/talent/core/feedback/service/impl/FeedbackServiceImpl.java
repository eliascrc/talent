package cr.talent.core.feedback.service.impl;

import cr.talent.core.feedback.dao.FeedbackDao;
import cr.talent.core.feedback.service.FeedbackService;
import cr.talent.model.Feedback;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.feedback.service.FeedbackService}.
 *
 * @author Otto Mena
 */
@Service("feedbackService")
@Transactional
public class FeedbackServiceImpl extends CrudServiceImpl<Feedback, String> implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    public void init() {
        setCrudDao(this.feedbackDao);
    }

}
