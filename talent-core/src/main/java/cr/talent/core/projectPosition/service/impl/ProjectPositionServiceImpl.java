package cr.talent.core.projectPosition.service.impl;

import cr.talent.core.projectPosition.dao.ProjectPositionDao;
import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.model.*;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.model.Project;
import cr.talent.model.ProjectPosition;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.model.TechnicalResource;
import cr.talent.support.exceptions.NoActiveProjectException;
<<<<<<< HEAD
=======
import cr.talent.support.exceptions.NoTechnicalResourceProjectException;
>>>>>>> 9b393b70132b89e049f76734c7cdb0a7ba3caa04
import cr.talent.support.flexjson.JSONSerializerBuilder;
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
 * @author Elías Calderón, Otto Mena, Josue Cubero
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
     *
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

        projectPositionDao.remove(projectPosition);
    }

    /**
     *
     * @see cr.talent.core.projectPosition.service.ProjectPositionService#createProjectPosition(TechnicalResource, Project, CapabilityLevel, int)-
     */
    @Override
    public void createProjectPosition(TechnicalResource assigner, Project project, CapabilityLevel capabilityLevel, int totalHours) {
        if (!project.getOrganization().equals(capabilityLevel.getOrganization())) {
            throw new ProjectOfAnotherOrganizationException();
        }

        TechnicalResource projectLead = null;

        for (LeadPosition leadPosition : project.getLeadHistory()) {
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
        projectPosition.setCapabilityLevel(capabilityLevel);
        projectPosition.setProjectPositionStatus(ProjectPositionStatus.AVAILABLE); //No one is assigned to it, so it can not be TAKEN, and to set its status as CLOSED, one should use the delete webservice.

        for (ProjectPosition projectPositionIterator : project.getProjectPositions()) {
            if (projectPositionIterator.equals(projectPosition)) {
                throw new ProjectPositionAlreadyExistsException();
            }
        }
        super.create(projectPosition);
    }

     /**
     * @see cr.talent.core.projectPosition.service.ProjectPositionService#getTechnicalResourceProjects(TechnicalResource)
     */
    @Override
    public String getTechnicalResourceProjects(TechnicalResource technicalResource) {
        final String noActiveTechnicalResourceProjectsMsg = technicalResource.getFirstName() + " " + technicalResource.getLastName()
                + " does not have any active project";

        if(technicalResource.getProjectPositions().isEmpty()) //ask first for efficiency reasons
            throw new NoActiveProjectException(noActiveTechnicalResourceProjectsMsg);
<<<<<<< HEAD
=======

>>>>>>> 9b393b70132b89e049f76734c7cdb0a7ba3caa04

        Set<ProjectPositionHolder> projectPositionHolders = technicalResource.getProjectPositions();

<<<<<<< HEAD
        if(activeProjectPositionHolders.isEmpty()) //ask again after the iteration
            throw new NoActiveProjectException(noActiveTechnicalResourceProjectsMsg);
=======
        if(projectPositionHolders.isEmpty()) //ask again after the iteration
            throw new NoTechnicalResourceProjectException(noActiveTechnicalResourceProjectsMsg);
>>>>>>> 9b393b70132b89e049f76734c7cdb0a7ba3caa04

        Set<Project> projects = new HashSet<>();
        for (ProjectPositionHolder activeProjectPositionHolder : projectPositionHolders) {
            projects.add(activeProjectPositionHolder.getProjectPosition().getProject());
        }

        return JSONSerializerBuilder.getTechnicalResourceProjectsSerializer().serialize(projects);
    }
}
