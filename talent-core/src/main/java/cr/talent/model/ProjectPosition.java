package cr.talent.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class that represents a Project position within the Talent system.
 * It contains the dates of the position, the related project, capability, a performance review flag
 * and the information inherited from {@link cr.talent.model.Position} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "project_position")
public class ProjectPosition extends Position {

    /**
     * The date that the resource started in the project position.
     */
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    /**
     * The date that the resource finished in the project position.
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * A flag that indicates if the position has already been reviewed in past performance reviews.
     */
    @Column(name = "reviewed", nullable = false)
    private boolean reviewed;

    /**
     * A reference to the specific project capability that the position occupies or occupied in the past.
     */
    @ManyToOne
    @JoinColumn(name = "project_capability_id", nullable = false)
    private ProjectCapability projectCapability;

    public ProjectPosition(){}

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
