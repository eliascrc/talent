package cr.talent.core.project.service;

import cr.talent.model.Project;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.Project} entities.
 *
 * @author Elías Calderón
 */
public interface ProjectService extends CrudService<Project, String> {

    void changeProjectState(Project project, String status, TechnicalResource lead);

}
