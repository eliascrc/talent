package cr.talent.core.projectPosition.service;

import cr.talent.model.ProjectPosition;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.ProjectPosition} entities.
 *
 * @author Elías Calderón
 */
public interface ProjectPositionService extends CrudService<ProjectPosition, String> {


    /**
     * Queries a list of {@link cr.talent.model.Project} for a technical resource..
     * @param technicalResource the technical resource.
     */
    String getTechnicalResourceProjects(TechnicalResource technicalResource);
}
