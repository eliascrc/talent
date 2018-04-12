package cr.talent.model;

import java.util.Date;

/**
 * Class that represents a Project position within the Talent system.
 * It contains the dates of the position, the related project, capability, a performance review flag
 * and the information inherited from {@link cr.talent.model.Position} class.
 *
 * @author María José Cubero
 */
public class ProjectPosition extends Position {

    /**
     * The date that the resource started in the project position.
     */
    private Date startDate;

    /**
     * The date that the resource finished in the project position.
     */
    private Date endDate;

    /**
     * The respective project for the position that the resource occupies.
     */
    private Project project;

    /**
     * A flag that indicates if the position has already been reviewed in past performance reviews.
     */
    private boolean reviewed;

    /**
     * A reference to the specific project capability that the position occupies or occupied in the past.
     */
    private ProjectCapability projectCapability;

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

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public ProjectCapability getProjectCapability() {
        return projectCapability;
    }

    public void setProjectCapability(ProjectCapability projectCapability) {
        this.projectCapability = projectCapability;
    }
}
