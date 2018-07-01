package cr.talent.core.projectPosition.service.impl;

import cr.talent.core.projectPosition.dao.ProjectPositionDao;
import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.model.*;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.model.TechnicalResource;
import cr.talent.support.exceptions.NoActiveTechnicalResourceProjectException;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import cr.talent.support.service.impl.CrudServiceImpl;
import cr.talent.support.exceptions.NotProjectLeadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Default implementation of the {@link cr.talent.core.projectPosition.service.ProjectPositionService}.
 *
 * @author Elías Calderón, Josue Cubero, Otto Mena
 */
@Service("projectPositionService")
@Transactional
public class ProjectPositionServiceImpl extends CrudServiceImpl<ProjectPosition, String> implements ProjectPositionService {

    @Autowired
    private ProjectPositionDao projectPositionDao;

    public void init() {
        setCrudDao(this.projectPositionDao);
    }

    /**
     *@see cr.talent.core.projectPosition.service.ProjectPositionService#deleteProjectPosition(TechnicalResource, ProjectPosition)-
     */
    public void deleteProjectPosition(TechnicalResource deleter, ProjectPosition projectPosition) {
        TechnicalResource projectLead = null;

        for (LeadPosition leadPosition : projectPosition.getProject().getLeadHistory()) { //finds the project lead
            if (leadPosition.getActive())
                projectLead = leadPosition.getLead();
        }

        if (projectLead == null)
            throw new ProjectWithoutLeadException();

        if (!deleter.equals(projectLead))
            throw new NotProjectLeadException();

        super.remove(projectPosition);
    }

    /**
     *
     * @see cr.talent.core.projectPosition.service.ProjectPositionService#getTechnicalResourceActiveProjects(TechnicalResource)
     */
    @Override
    public String getTechnicalResourceActiveProjects(TechnicalResource technicalResource) {
        final String noActiveTechnicalResourceProjectsMsg = technicalResource.getFirstName() + " " + technicalResource.getLastName()
                + " does not have any active project";

        if(technicalResource.getProjectPositions().isEmpty()) //ask first for efficiency reasons
            throw new NoActiveTechnicalResourceProjectException(noActiveTechnicalResourceProjectsMsg);

        Set<ProjectPositionHolder> projectPositionHolders = technicalResource.getProjectPositions();
        List<ProjectPositionHolder> activeProjectPositionHolders = new ArrayList<>();

        Iterator<ProjectPositionHolder> projectPositionHolderIterator = projectPositionHolders.iterator();
        ProjectPositionHolder projectPositionHolder;
        while(projectPositionHolderIterator.hasNext()){ //iterate projectPositionHolders for active ones
            projectPositionHolder = projectPositionHolderIterator.next();
            if(projectPositionHolder.isActive()){
                activeProjectPositionHolders.add(projectPositionHolder);
            }
        }

        if(activeProjectPositionHolders.isEmpty()) //ask again after the iteration
            throw new NoActiveTechnicalResourceProjectException(noActiveTechnicalResourceProjectsMsg);

        List<Project> projects = new ArrayList<>();
        for (ProjectPositionHolder activeProjectPositionHolder : activeProjectPositionHolders) {
            projects.add(activeProjectPositionHolder.getProjectPosition().getProject());
        }

        return JSONSerializerBuilder.getTechnicalResourceActiveProjectsSerializer().serialize(projects);

    }
}
