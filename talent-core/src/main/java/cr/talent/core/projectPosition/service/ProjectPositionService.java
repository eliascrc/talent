package cr.talent.core.projectPosition.service;

import cr.talent.model.CapabilityLevel;
import cr.talent.model.Project;
import cr.talent.model.ProjectPosition;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.ProjectPosition} entities.
 *
 * @author Elías Calderón
 */
public interface ProjectPositionService extends CrudService<ProjectPosition, String> {

    /**Used to delete a project position
     *
     * @param lead the user that is deleting the project position.
     * @param projectPosition the project position that will be deleted
     */
    void deleteProjectPosition(TechnicalResource lead, ProjectPosition projectPosition);
}
