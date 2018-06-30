package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents a Skill within the Talent system. It contains the name of the skill and
 * the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "skill")
public class Skill extends BasicEntity {

    /**
     * The name of the skill
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The resources that have this skill
     */
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "skill_resource",
            joinColumns = { @JoinColumn(name = "skill_id") },
            inverseJoinColumns = { @JoinColumn(name = "resource_id") }
    )
    private Set<TechnicalResource> resources;

    /**
     * The category of the skill
     */
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private SkillCategory category;

    /**
     * The type of skill
     */
    @Column(name = "skill_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private SkillType skillType;

    /**
     * The organization to which the skill belongs.
     * If it's null, the skill is a system predefined skill.
     */
    @ManyToOne
    @JoinColumn (name = "organization_id")
    private Organization organization;

    public Skill(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Skill){
            Skill skill = (Skill) o;
            result = (this.name == null ? skill.getName() == null : this.name.equals(skill.getName()))
                    && (this.category == null ? skill.getCategory() == null : this.category.equals(skill.getCategory()))
                    && (this.organization == null ? skill.getOrganization() == null : this.organization.equals(skill.getOrganization()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        result = prime * result + (this.category == null ? 0 : this.category.hashCode());
        result = prime * result + (this.organization == null ? 0 : this.organization.hashCode());
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TechnicalResource> getResources() {
        return resources;
    }

    public void setResources(Set<TechnicalResource> resources) {
        this.resources = resources;
    }

    public SkillCategory getCategory() {
        return category;
    }

    public void setCategory(SkillCategory category) {
        this.category = category;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
