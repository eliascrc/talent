package cr.talent.model;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;
import java.util.Set;

/**
 * Class that represents a skill defined by an organization within the Talent system. It contains
 * the organization, skill's category, resources that possess the skill and the information inherited from
 * {@link cr.talent.model.Skill} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name="organization_skill")
public class OrganizationSkill extends Skill {

    /**
     * The organization that has the category Skill
     */
    private Organization organization;

    /**
     * The organization skill category that the organization skill belongs to.
     */
    @Column(name="organization_category_skill", nullable = false)
    private OrganizationCategorySkill category;

    /**
     * A list with the resources that possess and have been authorized with the organization's skill.
     */
    @Column(name="resources")
    private Set<TechnicalResource> resources;

    public OrganizationSkill(){}

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public OrganizationCategorySkill getCategory() {
        return category;
    }

    public void setCategory(OrganizationCategorySkill category) {
        this.category = category;
    }

    public Set<TechnicalResource> getResources() {
        return resources;
    }

    public void setResources(Set<TechnicalResource> resources) {
        this.resources = resources;
    }
}
