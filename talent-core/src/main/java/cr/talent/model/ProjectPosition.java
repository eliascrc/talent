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
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
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
