package cr.talent.model;

import java.util.Date;

/**
 * Class that represents a project manager position within the Talent system.
 * It contains the project start date, end date, project and project manager.
 *
 * @author María José Cubero
 */
public class ProjectManagerPosition {

    /**
     * Start date of the project.
     */
    private Date startDate;

    /**
     * End date of the project.
     */
    private Date endDate;

    /**
     * The project of the project manager position.
     */
    private Project project;

    /**
     * The project manager of this position.
     */
    private ProjectManager projectManager;

    public ProjectManagerPosition (){}

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public ProjectManager getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }
}
