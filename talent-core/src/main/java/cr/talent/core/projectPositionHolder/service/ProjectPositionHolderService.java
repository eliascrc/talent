package cr.talent.core.projectPositionHolder.service;

import cr.talent.model.ProjectPosition;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

import java.util.Date;

/**
 * Provides business logic services related to {@link cr.talent.model.ProjectPositionHolder} entities.
 *
 * @author Daniel Montes de Oca
 */
public interface ProjectPositionHolderService extends CrudService<ProjectPositionHolder, String> {

    /**
     * Used to assign a project position to a technical resource
     * @param assigner The resource assigning the project position, checked to see if they are the lead of the project
     * @param assignee The resource that is assigned the project position
     * @param projectPosition the project position that is assigned to the resource
     */
    void assignProjectPosition(TechnicalResource assigner, TechnicalResource assignee, ProjectPosition projectPosition,
                               Date startDate, int assignedHours, boolean active);

    /**
     * Used to unassign a project position of a technical resource
     * @param projectPositionHolder the instance of the project position that will be modified
     * @param unassigner the technical resource unassigning the project position, they must be the project lead
     * @param endDate the date the resource finished working on that project position
     */
    void unassignProjectPosition(ProjectPositionHolder projectPositionHolder, TechnicalResource unassigner, Date endDate);

}
