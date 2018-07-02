package cr.talent.core.feedback.service.impl;

import cr.talent.core.feedback.dao.FeedbackDao;
import cr.talent.core.feedback.service.FeedbackService;
import cr.talent.model.*;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Iterator;

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
    public boolean createKudo(TechnicalResource observer, TechnicalResource observee, Project project, String description) {

        boolean isObserverRelatedToProject = false;
        boolean isObserveeRelatedToProject = false;
        boolean isObserverAnAdministrator = observer.isAdministrator();

        // iterate through the positions to find out if the resources are related to the project.
        Iterator<ProjectPosition> projectPositionIterator = project.getProjectPositions().iterator();
        ProjectPosition projectPosition;
        ProjectPositionHolder projectPositionHolder;
        while(projectPositionIterator.hasNext()){
            projectPosition = projectPositionIterator.next();
            Iterator<ProjectPositionHolder> projectPositionHolderIterator = projectPosition.getHolderHistory().iterator();
            while(projectPositionHolderIterator.hasNext()){
                projectPositionHolder = projectPositionHolderIterator.next();
                if (projectPositionHolder.getResource().equals(observee)) {
                    isObserveeRelatedToProject = true;
                } else if(projectPositionHolder.getResource().equals(observer)){
                    isObserverRelatedToProject = true;
                }
            }
        }

        // iterate though lead positions
        Iterator<LeadPosition> leadPositionIterator = project.getLeadHistory().iterator();
        LeadPosition leadPosition;
        while(leadPositionIterator.hasNext()){
            leadPosition = leadPositionIterator.next();
            if (leadPosition.getLead().equals(observee)) {
                isObserveeRelatedToProject = true;
            } else if(leadPosition.getLead().equals(observer)){
                isObserverRelatedToProject = true;
            }
        }

        if(isObserveeRelatedToProject && (isObserverRelatedToProject || isObserverAnAdministrator)) {

            Feedback kudo = new Feedback();
            kudo.setFeedbackType(FeedbackType.KUDO);
            kudo.setObserver(observer);
            kudo.setObservee(observee);
            kudo.setRelatedProject(project);
            kudo.setDescription(description);
            this.create(kudo);

        }

        return isObserveeRelatedToProject && (isObserverRelatedToProject || isObserverAnAdministrator);
    }
}
