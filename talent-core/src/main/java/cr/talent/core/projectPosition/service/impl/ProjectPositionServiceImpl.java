package cr.talent.core.projectPosition.service.impl;

import cr.talent.core.projectPosition.dao.ProjectPositionDao;
import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.model.*;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.support.service.impl.CrudServiceImpl;
import cr.talent.support.exceptions.ProjectOfAnotherOrganizationException;
import cr.talent.support.exceptions.ProjectPositionAlreadyExistsException;
import cr.talent.support.exceptions.NotProjectLeadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.projectPosition.service.ProjectPositionService}.
 *
 * @author Elías Calderón && Otto Mena
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
    }
}
