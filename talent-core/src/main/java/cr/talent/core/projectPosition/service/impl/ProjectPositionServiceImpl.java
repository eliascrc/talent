package cr.talent.core.projectPosition.service.impl;

import cr.talent.core.projectPosition.dao.ProjectPositionDao;
import cr.talent.core.projectPosition.service.ProjectPositionService;
<<<<<<< HEAD
import cr.talent.model.*;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
=======
import cr.talent.model.Project;
import cr.talent.model.ProjectPosition;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.model.TechnicalResource;
import cr.talent.support.exceptions.NoActiveTechnicalResourceProjectException;
import cr.talent.support.flexjson.JSONSerializerBuilder;
>>>>>>> 2d351e987724fc6e98937556944b5a9f8862879c
import cr.talent.support.service.impl.CrudServiceImpl;
import cr.talent.support.exceptions.ProjectOfAnotherOrganizationException;
import cr.talent.support.exceptions.ProjectPositionAlreadyExistsException;
import cr.talent.support.exceptions.NotProjectLeadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Default implementation of the {@link cr.talent.core.projectPosition.service.ProjectPositionService}.
 *
<<<<<<< HEAD
 * @author Elías Calderón, Otto Mena
=======
 * @author Elías Calderón, Josue Cubero
>>>>>>> 2d351e987724fc6e98937556944b5a9f8862879c
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
<<<<<<< HEAD
     * @see cr.talent.core.projectPosition.service.ProjectPositionService#createProjectPosition(TechnicalResource, Project, CapabilityLevel, int)-
     */
    @Override
    public void createProjectPosition(TechnicalResource assigner, Project project, CapabilityLevel capabilityLevel, int totalHours){
        if(!project.getOrganization().equals(capabilityLevel.getOrganization())){
            throw new ProjectOfAnotherOrganizationException();
        }

        TechnicalResource projectLead = null;

        for (LeadPosition leadPosition : project.getLeadHistory()) { //finds the project lead
            if (leadPosition.getActive())
                projectLead = leadPosition.getLead();
        }

        if (projectLead == null)
            throw new ProjectWithoutLeadException();

        if (!assigner.equals(projectLead))
            throw new NotProjectLeadException();

        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setTotalHours(totalHours);
        projectPosition.setProject(project);
        projectPosition.setCapability(capabilityLevel);
        projectPosition.setProjectPositionStatus(ProjectPositionStatus.AVAILABLE); //No one is assigned to it, so it can not be TAKEN, and to set its status as CLOSED, one should use the delete webservice.

        for (ProjectPosition projectPositionIterator : project.getProjectPositions()){
            if (projectPositionIterator.equals(projectPosition)){
                throw new ProjectPositionAlreadyExistsException();
            }
        }
        super.create(projectPosition);
=======
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

>>>>>>> 2d351e987724fc6e98937556944b5a9f8862879c
    }
}
