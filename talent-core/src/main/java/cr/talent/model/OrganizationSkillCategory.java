package cr.talent.model;

import java.util.Set;

/**
 * Class that represents an organization capability within the Talent system.
 * It contains organization's skills and the information inherited from
 * {@link SkillCategory} class.
 *
 * @author María José Cubero
 */
public class OrganizationSkillCategory extends SkillCategory {

    /**
     * List of skills of the organization
     */
    private Set<OrganizationSkill> organizationSkills;

    public OrganizationSkillCategory(){}

    public Set<OrganizationSkill> getOrganizationSkills() {
        return organizationSkills;
    }

    public void setOrganizationSkills(Set<OrganizationSkill> organizationSkills) {
        this.organizationSkills = organizationSkills;
    }
}
