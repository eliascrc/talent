package cr.talent.model;

import javax.persistence.*;
import java.util.Set;
import java.util.Date;

/**
 * Class that represents a project within the Talent system.
 * It contains the project name, description, dates, links, capabilities, lead history, status, timeline,
 * positions and capabilities and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "project")
public class Project extends BasicEntity {

    /**
     * The name of the project.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The description of the project.
     */
    @Column
    private String description;

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
     * The link to the JIRA page of the project.
     */
    @Column(name = "jira_link")
    private String jiraLink;

    /**
     * The link to the Confluence page of the project.
     */
    @Column(name = "confluence_link")
    private String confluenceLink;

    /**
     * The link to the Version Control page of the project.
     */
    @Column(name = "version_control_link")
    private String versionControlLink;

    /**
     * The event timeline of the project
     */
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "project")
    @OrderBy ("startDate")
    private Set<ProjectEvent> timeline;

    /**
     * The state that the project currently has. It's represented by the latest event.
     */
    @OneToOne
    private ProjectEvent state;

    /**
     * A set with the history of project manager's throughout the life time of the project.
     */
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "project")
    @OrderBy ("entityCreationTimestamp")
    private Set<LeadPosition> leadHistory;

    /**
     * A set with the capabilities of the project.
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "project_capabilities",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "capability_level_id") }
    )
    private Set<CapabilityLevel> projectCapabilities;

    /**
     * A set with the positions of the project
     */
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "project")
    private Set<ProjectPosition> projectPositions;

    /**
     * The organization that the project belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    /**
     * List of observations made in the project.
     */
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "relatedProject")
    private Set<Observation> observations;

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

    public Set<CapabilityLevel> getProjectCapabilities() {
        return projectCapabilities;
    }

    public void setProjectCapabilities(Set<CapabilityLevel> projectCapabilities) {
        this.projectCapabilities = projectCapabilities;
    }

    public ProjectEvent getState() {
        return state;
    }

    public void setState(ProjectEvent state) {
        this.state = state;
    }

    public Set<LeadPosition> getLeadHistory() {
        return leadHistory;
    }

    public void setLeadHistory(Set<LeadPosition> leadHistory) {
        this.leadHistory = leadHistory;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<Observation> getObservations() {
        return observations;
    }

    public void setObservations(Set<Observation> observations) {
        this.observations = observations;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJiraLink() {
        return jiraLink;
    }

    public void setJiraLink(String jiraLink) {
        this.jiraLink = jiraLink;
    }

    public String getConfluenceLink() {
        return confluenceLink;
    }

    public void setConfluenceLink(String confluenceLink) {
        this.confluenceLink = confluenceLink;
    }

    public String getVersionControlLink() {
        return versionControlLink;
    }

    public void setVersionControlLink(String versionControlLink) {
        this.versionControlLink = versionControlLink;
    }

    public Set<ProjectEvent> getTimeline() {
        return timeline;
    }

    public void setTimeline(Set<ProjectEvent> timeline) {
        this.timeline = timeline;
    }

    public Set<ProjectPosition> getProjectPositions() {
        return projectPositions;
    }

    public void setProjectPositions(Set<ProjectPosition> projectPositions) {
        this.projectPositions = projectPositions;
    }
}