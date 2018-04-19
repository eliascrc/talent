package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents a skill defined by an organization within the Talent system. It contains
 * the organization, skill's category, resources that possess the skill and the information inherited from
 * {@link cr.talent.model.Skill} class.
 *
 * @author Elías Calderón
 */
@Entity
@DiscriminatorValue(value = "ORGANIZATION_SKILL")
public class OrganizationSkill extends Skill {

    /**
     * The organization skill category that the organization skill belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "org_skill_category_id", nullable = false)
    private OrganizationSkillCategory category;

    /**
     * A list with the resources that possess and have been authorized with the organization's skill.
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "org_skill_resource",
            joinColumns = { @JoinColumn(name = "org_skill_id") },
            inverseJoinColumns = { @JoinColumn(name = "resource_id") }
    )
    private Set<TechnicalResource> resources;

    /**
     * A list of the invitations that include this skill.
     */
    @ManyToMany(mappedBy = "skills")
    private Set<Invitation> invitations;

    /**
     * The organization capability levels that the organization skill belongs to.
     */
    @ManyToMany(mappedBy = "requiredSkills")
    private Set<OrganizationCapabilityLevel> organizationCapabilityLevels;

    public OrganizationSkill(){}

    public OrganizationSkillCategory getCategory() {
        return category;
    }

    public void setCategory(OrganizationSkillCategory category) {
        this.category = category;
    }

    public Set<TechnicalResource> getResources() {
        return resources;
    }

    public void setResources(Set<TechnicalResource> resources) {
        this.resources = resources;
    }

    public Set<Invitation> getInvitations() {
        return invitations;
    }

    public void setInvitations(Set<Invitation> invitations) {
        this.invitations = invitations;
    }

    public Set<OrganizationCapabilityLevel> getOrganizationCapabilityLevels() {
        return organizationCapabilityLevels;
    }

    public void setOrganizationCapabilityLevels(Set<OrganizationCapabilityLevel> organizationCapabilityLevels) {
        this.organizationCapabilityLevels = organizationCapabilityLevels;
    }
}
