package cr.talent.core.projectPositionHolder.service;

import cr.talent.model.ProjectPosition;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

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
    void assignProjectPosition(TechnicalResource assigner, TechnicalResource assignee, ProjectPosition projectPosition);

}
