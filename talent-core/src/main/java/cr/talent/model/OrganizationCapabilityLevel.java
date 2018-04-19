package cr.talent.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * Class that represents an organization capability within the Talent system.
 * It contains the information inherited from
 * {@link cr.talent.model.CapabilityLevel} class.
 *
 * @author Elías Calderón
 */
@Entity
@DiscriminatorValue(value = "ORGANIZATION_CAPABILITY_LEVEL")
public class OrganizationCapabilityLevel extends CapabilityLevel {

    /**
     * The capability of the capability level.
     */
    @ManyToOne
    @JoinColumn(name = "org_capability_id", nullable = false)
    private OrganizationCapability organizationCapability;

    /**
     * The list of the resources that have the capability level.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organizationCapabilityLevel")
    private Set<TechnicalResource> resources;

    /**
     * The list of the requiered skills to have that capability level.
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "org_capability_level_skill",
            joinColumns = { @JoinColumn(name = "organization_capability_level_id") },
            inverseJoinColumns = { @JoinColumn(name = "organization_skill_id") }
    )
    private Set<OrganizationSkill> requiredSkills;

    /**
     * The project capabilities that have the organization capability level.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "capability")
    private Set<ProjectCapability> projectCapability;

    public OrganizationCapabilityLevel() {}

    public OrganizationCapability getCapability() {
        return organizationCapability;
    }

    public void setCapability(OrganizationCapability capability) {
        this.organizationCapability = capability;
    }

    public Set<TechnicalResource> getResources() {
        return resources;
    }

    public void setResources(Set<TechnicalResource> resources) {
        this.resources = resources;
    }

    public Set<OrganizationSkill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<OrganizationSkill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public OrganizationCapability getOrganizationCapability() {
        return organizationCapability;
    }

    public void setOrganizationCapability(OrganizationCapability organizationCapability) {
        this.organizationCapability = organizationCapability;
    }

    public Set<ProjectCapability> getProjectCapability() {
        return projectCapability;
    }

    public void setProjectCapability(Set<ProjectCapability> projectCapability) {
        this.projectCapability = projectCapability;
    }
}
