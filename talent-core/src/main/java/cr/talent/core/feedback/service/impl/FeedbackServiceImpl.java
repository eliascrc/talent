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
     * @see cr.talent.core.feedback.service.FeedbackService#createWarning(TechnicalResource, TechnicalResource, Project, String)
     */
    @Override
    public boolean createWarning(TechnicalResource observer, TechnicalResource observee, Project project, String description) {

        boolean isObserverAProjectLead = false;
        boolean isObserveeRelatedToProject = false;
        boolean isObserverAnAdministrator = observer.isAdministrator();

        // iterate through the positions to find out if the observee is related to the project.
        Iterator<ProjectPosition> projectPositionIterator = project.getProjectPositions().iterator();
        ProjectPosition projectPosition;
        ProjectPositionHolder projectPositionHolder;
        while(projectPositionIterator.hasNext()){
            projectPosition = projectPositionIterator.next();
            Iterator<ProjectPositionHolder> projectPositionHolderIterator = projectPosition.getHolderHistory().iterator();
            while(projectPositionHolderIterator.hasNext()){
                projectPositionHolder = projectPositionHolderIterator.next();
                 if(projectPositionHolder.getResource().equals(observee)){
                    isObserveeRelatedToProject = true;
                 }
            }
        }

        // iterate though lead positions, both could be leaders.
        Iterator<LeadPosition> leadPositionIterator = project.getLeadHistory().iterator();
        LeadPosition leadPosition;
        while(leadPositionIterator.hasNext()){
            leadPosition = leadPositionIterator.next();
            if (leadPosition.getLead().equals(observee)) {
                isObserveeRelatedToProject = true;
            } else if(leadPosition.getLead().equals(observer)){
                isObserverAProjectLead = true;
            }
        }

        if(isObserveeRelatedToProject && (isObserverAProjectLead || isObserverAnAdministrator)) {

            Feedback warning = new Feedback();
            warning.setFeedbackType(FeedbackType.WARNING);
            warning.setObserver(observer);
            warning.setObservee(observee);
            warning.setRelatedProject(project);
            warning.setDescription(description);
            this.create(warning);

        }

        return isObserveeRelatedToProject && (isObserverAProjectLead || isObserverAnAdministrator);
    }
}
