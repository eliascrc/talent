package cr.talent.core.project.service;

import cr.talent.model.Project;
import cr.talent.model.Skill;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

import java.sql.Date;
import java.util.Set;

/**
 * Provides business logic services related to {@link cr.talent.model.Project} entities.
 *
 * @author Elías Calderón, Otto Mena, Josué Cubero
 */
public interface ProjectService extends CrudService<Project, String> {

    /**This method assigns the newStateDate to the current state of the project, then it creates a new projectSate
     * and sets it to the project as its current.
     *
     * @param project the project that will have its state changed.
     * @param status the new state of the project.
     * @param lead the lead of the project attempting to change it state.
     * @param newStateDate the endDate for the old projectState and the startDate for the new projectState
     */
    void changeProjectState(Project project, String status, TechnicalResource lead, Date newStateDate);

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

    /**
     * Gets a project skills.
     *
     * @param project the project.
     * @return the created project.
     */
    Set<Skill> getSkills(Project project);

}
