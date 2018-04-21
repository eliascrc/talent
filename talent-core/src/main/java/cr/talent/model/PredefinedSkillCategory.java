package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents a predefined capability level within the Talent system.
 * It contains the information inherited from
 * {@link SkillCategory} class.
 *
 * @author María José Cubero
 */
@Entity
@DiscriminatorValue(value = "PREDEFINED_SKILL_CATEGORY")
public class PredefinedSkillCategory extends SkillCategory {

    /**
     * The list of the predefined Skills of this category;
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "category")
    private Set<PredefinedSkill> predefinedSkills;

    public PredefinedSkillCategory() {}

    public Set<PredefinedSkill> getPredefinedSkills() {
        return predefinedSkills;
    }

    public void setPredefinedSkills(Set<PredefinedSkill> predefinedSkills) {
        this.predefinedSkills = predefinedSkills;
    }
}
