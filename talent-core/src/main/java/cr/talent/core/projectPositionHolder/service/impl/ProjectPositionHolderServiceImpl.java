package cr.talent.core.projectPositionHolder.service.impl;

import cr.talent.core.projectPositionHolder.dao.ProjectPositionHolderDao;
import cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService;
import cr.talent.model.LeadPosition;
import cr.talent.model.ProjectPosition;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.model.TechnicalResource;
import cr.talent.support.exceptions.NotProjectLeadException;
import cr.talent.support.exceptions.ProjectPositionOfAnotherOrganizationException;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Hibernate implementation of the {@link cr.talent.core.projectPositionHolder.dao.ProjectPositionHolderDao}.
 *
 * @author Daniel Montes de Oca
 */
@Service("projectPositionHolderService")
@Transactional
public class ProjectPositionHolderServiceImpl extends CrudServiceImpl<ProjectPositionHolder, String> implements ProjectPositionHolderService {

    @Autowired
    private ProjectPositionHolderDao projectPositionHolderDao;

    public void init() {
        setCrudDao(this.projectPositionHolderDao);
    }

    /**
     * @see cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService#assignProjectPosition(TechnicalResource, TechnicalResource, ProjectPosition, Date, int, boolean)
     */
    @Override
    public void assignProjectPosition(TechnicalResource assigner, TechnicalResource assignee,
                                      ProjectPosition projectPosition, Date startDate, int assignedHours,
                                      boolean active){
        if (!projectPosition.getProject().getOrganization().equals(assigner.getOrganization())) {
            throw new ProjectPositionOfAnotherOrganizationException();
        }

        TechnicalResource projectLead = null;

        for (LeadPosition leadPosition : projectPosition.getProject().getLeadHistory()) { //finds the project lead
            if (leadPosition.getActive())
                projectLead = leadPosition.getLead();
        }

        if (projectLead == null)
            throw new ProjectWithoutLeadException();

        if (!assigner.equals(projectLead))
            throw new NotProjectLeadException();

        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setAssignedHours(assignedHours);
        projectPositionHolder.setReviewed(false);
        projectPositionHolder.setActive(active);
        projectPositionHolder.setResource(assignee);
        projectPositionHolder.setStartDate(startDate);
        projectPositionHolder.setProjectPosition(projectPosition);

        super.create(projectPositionHolder);
    }

}
