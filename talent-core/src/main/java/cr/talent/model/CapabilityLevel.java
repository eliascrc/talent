package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents a Capability Level within the Talent system.
 * It contains the capability level name, the level hierarchy position and the information inherited
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "capability_level")
public class CapabilityLevel extends BasicEntity {

    /**
     * The name of the capability level
     */
    @Column (nullable = false)
    private String name;

    /**
     * The position in the level hierarchy for the capability, that the level possesses.
     */
    @Column (name = "hierarchy_position" , nullable = false)
    private int hierarchyPosition;

    /**
     * The parent capability of the level.
     */
    @ManyToOne
    @JoinColumn (name = "capability_id", nullable = false)
    private Capability capability;

    /**
     * The required skills to achieve the capability level.
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "required_capability_skills",
            joinColumns = { @JoinColumn(name = "capability_level_id") },
            inverseJoinColumns = { @JoinColumn(name = "skill_id") }
    )
    private Set<OrganizationSkill> requiredSkills;

    /**
     * The organization of the capability level. If it points no organization, the capability level is taken as a
     * predefined capability level.
     */
    @ManyToOne
    @JoinColumn (name = "organization_id", nullable = false)
    private Organization organization;

    /**
     * The projects where the capability is in use
     */
    @ManyToMany(mappedBy = "projectCapabilities")
    private Set<Project> projects;

    public CapabilityLevel(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof CapabilityLevel){
            CapabilityLevel capabilityLevel = (CapabilityLevel) o;
            result = ((this.name == null ? capabilityLevel.getName() == null : this.name.equals(capabilityLevel.getName()))
                    && (this.organization == null ? capabilityLevel.getOrganization() == null : this.organization.equals(capabilityLevel.getOrganization()))
                    && (this.capability == null ? capabilityLevel.getCapability() == null : this.capability.equals(capabilityLevel.getCapability())));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        result = prime * result + (this.organization == null ? 0 : this.organization.hashCode());
        result = prime * result + (this.capability == null ? 0 : this.capability.hashCode());
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHierarchyPosition() {
        return hierarchyPosition;
    }

    public void setHierarchyPosition(int hierarchyPosition) {
        this.hierarchyPosition = hierarchyPosition;
    }

    public Capability getCapability() {
        return capability;
    }

    public void setCapability(Capability capability) {
        this.capability = capability;
    }

    public Set<Project> getProject() {
        return projects;
    }

    public void setProject(Set<Project> project) {
        this.projects = project;
    }

    public Set<OrganizationSkill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<OrganizationSkill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}