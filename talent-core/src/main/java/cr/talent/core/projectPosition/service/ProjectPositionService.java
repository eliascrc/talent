package cr.talent.core.projectPosition.service;

import cr.talent.model.CapabilityLevel;
import cr.talent.model.Project;
import cr.talent.model.ProjectPosition;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.ProjectPosition} entities.
 *
 * @author Elías Calderón, Otto Mena, Josue Cubero
 */
public interface ProjectPositionService extends CrudService<ProjectPosition, String> {

    /**
     *Used to create a new project position
     *
     * @param assigner The resource assigning the project position, checked to see if they are the lead of the project.
     * @param project The project to which we will add a new project position.
     * @param capabilityLevel The capability level of the new project position.
     * @param totalHours The hours that the project position will have assigned.
     */
    void createProjectPosition(TechnicalResource assigner, Project project, CapabilityLevel capabilityLevel, int totalHours);

    /**
     * Queries a list of {@link cr.talent.model.Project} for a technical resource..
     * @param technicalResource the technical resource.
     */
    String getTechnicalResourceActiveProjects(TechnicalResource technicalResource);
}
