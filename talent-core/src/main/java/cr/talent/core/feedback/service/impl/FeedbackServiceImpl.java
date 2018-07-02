package cr.talent.core.feedback.service.impl;

import cr.talent.core.feedback.dao.FeedbackDao;
import cr.talent.core.feedback.service.FeedbackService;
import cr.talent.model.Feedback;
import cr.talent.model.FeedbackType;
import cr.talent.model.Project;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.feedback.service.FeedbackService}.
 *
 * @author Otto Mena, Josue Cubero
 */
@Service("feedbackService")
@Transactional
public class FeedbackServiceImpl extends CrudServiceImpl<Feedback, String> implements FeedbackService {

    @Autowired
    private FeedbackDao feedbackDao;

    public void init() {
        setCrudDao(this.feedbackDao);
    }

    /**
     * @see cr.talent.core.feedback.service.FeedbackService#createKudo(TechnicalResource, TechnicalResource, Project, String)
     */
    @Override
    public void createKudo(TechnicalResource observer, TechnicalResource observee, Project project, String description) {
        Feedback kudo = new Feedback();
        kudo.setFeedbackType(FeedbackType.KUDO);
        kudo.setObserver(observer);
        kudo.setObservee(observee);
        kudo.setRelatedProject(project);
        kudo.setDescription(description);
        this.create(kudo);
    }
}
