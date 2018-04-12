package cr.talent.model;

import java.util.List;
import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Column;

/**
 * Class that represents a Skill defined by an organization within the Talent system. It contains
 * the skill's category, resources that possess the skill and the information inherited from
 * {@link cr.talent.model.Skill} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name="organization_skill")
public class OrganizationSkill extends Skill {

    /**
     * The organization skill category that the organization skill belongs to.
     */
    @Column(name="organization_category_skill", nullable = false)
    private OrganizationCategorySkill category;

    /**
     * A list with the resources that possess and have been authorized with the organization's skill.
     */
    @Column(name="resources")
    private List<TechnicalResource> resources;

    public OrganizationCategorySkill getCategory() {
        return category;
    }

    public void setCategory(OrganizationCategorySkill category) {
        this.category = category;
    }

    public List<TechnicalResource> getResources() {
        return resources;
    }

    public void setResources(List<TechnicalResource> resources) {
        this.resources = resources;
    }

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }
}
