package cr.talent.model;

import java.util.Set;

/**
 * Class that represents a skill defined by an organization within the Talent system. It contains
 * the organization, skill's category, resources that possess the skill and the information inherited from
 * {@link cr.talent.model.Skill} class.
 *
 * @author Elías Calderón
 */
public class OrganizationSkill extends Skill {

    /**
     * The organization that has the category Skill
     */
    private Organization organization;

    /**
     * The organization skill category that the organization skill belongs to.
     */
    private OrganizationSkillCategory category;

    /**
     * A list with the resources that possess and have been authorized with the organization's skill.
     */
    private Set<TechnicalResource> resources;

    public OrganizationSkill(){}

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

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
}
