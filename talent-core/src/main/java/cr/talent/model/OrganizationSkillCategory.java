package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents an organization capability within the Talent system.
 * It contains organization's skills and the information inherited from
 * {@link SkillCategory} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "organization_skill_category")
public class OrganizationSkillCategory extends SkillCategory {

    /**
     * The organization that the Skill category belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    /**
     * List of skills of the organization
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "category")
    private Set<OrganizationSkill> organizationSkills;

    public OrganizationSkillCategory(){}

    public Set<OrganizationSkill> getOrganizationSkills() {
        return organizationSkills;
    }

    public void setOrganizationSkills(Set<OrganizationSkill> organizationSkills) {
        this.organizationSkills = organizationSkills;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
