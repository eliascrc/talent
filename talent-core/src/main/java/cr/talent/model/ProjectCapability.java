package cr.talent.model;


import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents a project's capability within the Talent system.
 * It contains the capability's status, current position within the project, the position history
 * and the related project and capability and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "project_capability")
public class ProjectCapability extends BasicEntity {

    /**
     * The status for the specific capability of a project, it can be available, taken or closed.
     */
    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
	private ProjectCapabilityStatus status;

    /**
     * The current project position, and resource associated to it, that occupies this capability.
     */
    private ProjectPosition currentProjectPosition;

    /**
     * A list with a history of the project positions, and resources associated to them, that occupied this capability in
     * the past. It also includes the current project position that occupies this capability.
     */
    private Set<ProjectPosition> projectPositionHistory;

    /**
     * The project that the capability belongs to.
     */
    private Project project;

    /**
    *  The capability level that the project position should have
    */
    private OrganizationCapabilityLevel capability;

    public ProjectCapability(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof ProjectCapability){
            ProjectCapability projectCapability = (ProjectCapability) o;
            result = ((this.project == null ? projectCapability.getProject() == null : this.project.equals(projectCapability.getProject())
                    && this.capability == null ? projectCapability.getCapability() == null : this.capability.equals(projectCapability.getCapability())));
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

    public ProjectCapabilityStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectCapabilityStatus status) {
        this.status = status;
    }

    public ProjectPosition getCurrentProjectPosition() {
        return currentProjectPosition;
    }

    public void setCurrentProjectPosition(ProjectPosition currentProjectPosition) {
        this.currentProjectPosition = currentProjectPosition;
    }

    public Set<ProjectPosition> getProjectPositionHistory() {
        return projectPositionHistory;
    }

    public void setProjectPositionHistory(Set<ProjectPosition> projectPositionHistory) {
        this.projectPositionHistory = projectPositionHistory;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public OrganizationCapabilityLevel getCapability() {
        return capability;
    }

    public void setCapability(OrganizationCapabilityLevel capability) {
        this.capability = capability;
    }
}
