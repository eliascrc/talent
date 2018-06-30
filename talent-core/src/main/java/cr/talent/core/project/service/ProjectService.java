package cr.talent.core.project.service;

import cr.talent.model.Project;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

import java.sql.Date;

/**
 * Provides business logic services related to {@link cr.talent.model.Project} entities.
 *
 * @author Elías Calderón
 */
public interface ProjectService extends CrudService<Project, String> {

    /**
     * Creates a new project.
     *
     * @param name the project's name.
     * @param startDate the project's start date.
     * @param projectLead the optional project's lead.
     * @param description the project's description.
     * @param technicalResource the technical resource making the request.
     * @return the created project.
     */
    Project createProject(String name, Date startDate, String projectLead, String description, TechnicalResource technicalResource);

}
