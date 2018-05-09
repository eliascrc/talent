package cr.talent.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Class that represents a Project position within the Talent system.
 * It contains the status, the total hours for the position, the capability, holder history, related project and
 * and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "project_position")
public class ProjectPosition extends BasicEntity {

    /**
     * The status of the project position.
     */
    @Column(name = "project_position_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectPositionStatus projectPositionStatus;

    /**
     * The total hours assigned to that position. The sum of assigned hours of the holders must
     * be the total hours for the position.
     */
    @Column(name = "total_hours", nullable = false)
    private int totalHours;

    /**
     * The related capability of that project position
     */
    @ManyToOne
    @JoinColumn(name = "capability_id", nullable = false)
    private CapabilityLevel capability;

    /**
     * The history of position holders that the position has had.
     */
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "projectPosition")
    @OrderBy("startDate")
    private Set<ProjectPositionHolder> holderHistory;

    /**
     * The project of the position
     */
    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    public ProjectPosition(){}

    public ProjectPositionStatus getProjectPositionStatus() {
        return projectPositionStatus;
    }

    public void setProjectPositionStatus(ProjectPositionStatus projectPositionStatus) {
        this.projectPositionStatus = projectPositionStatus;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof ProjectPosition ){
            ProjectPosition projectPosition = (ProjectPosition) o;
            result = ((this.project == null ? projectPosition.getProject() == null : this.project.equals(projectPosition.getProject()))
                    && (this.capability == null ? projectPosition.getCapability() == null : this.capability.equals(projectPosition.getCapability())));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.project == null ? 0 : this.project.hashCode());
        result = prime * result + (this.capability == null ? 0 : this.capability.hashCode());
        return result;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }

    public CapabilityLevel getCapability() {
        return capability;
    }

    public void setCapability(CapabilityLevel capability) {
        this.capability = capability;
    }

    public Set<ProjectPositionHolder> getHolderHistory() {
        return holderHistory;
    }

    public void setHolderHistory(Set<ProjectPositionHolder> holderHistory) {
        this.holderHistory = holderHistory;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
