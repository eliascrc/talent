package cr.talent.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;
import java.util.Date;

/**
 * Class that represents a project within the Talent system.
 * It contains the project name, dates, capabilities, project manager history, status
 * and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "project")
public class Project extends BasicEntity {

    /**
     * The name of the project.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The date in which the project started.
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * The date in which the project finished.
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * A list with the capabilities of the project.
     */
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "project")
    private Set<ProjectCapability> projectCapabilities;

    /**
     * A set with the history of project manager's throughout the life time of the project.
     */
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "project")
    @OrderBy ("entityCreationTimestamp")
    private Set<ProjectManagerPosition> projectManagerHistory;

    /**
     * The state that the project currently has.
     */
    @Column(name = "state", nullable = false)
    @Enumerated(EnumType.STRING)
    private ProjectState state;

    /**
     * The organization that the project belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    public Project () {}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Project){
            Project project = (Project) o;
            result = (this.name == null ? project.getName() == null : this.name.equals(project.getName()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public Set<ProjectCapability> getProjectCapabilities() {
        return projectCapabilities;
    }

    public void setProjectCapabilities(Set<ProjectCapability> projectCapabilities) {
        this.projectCapabilities = projectCapabilities;
    }

    public ProjectState getState() {
        return state;
    }

    public void setState(ProjectState state) {
        this.state = state;
    }

    public Set<ProjectManagerPosition> getProjectManagerHistory() {
        return projectManagerHistory;
    }

    public void setProjectManagerHistory(Set<ProjectManagerPosition> projectManagerHistory) {
        this.projectManagerHistory = projectManagerHistory;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}