package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents a predefined Skill by a system administrator within the Talent system.
 * It contains the category of the skill and the information inherited from
 * {@link cr.talent.model.Skill} class.
 *
 * @author María José Cubero
 */
@Entity
@DiscriminatorValue(value = "PREDEFINED_SKILL")
public class PredefinedSkill extends Skill {

    /**
     * The predefined skill category that the predefined skill belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "pre_skill_category_id")
    private PredefinedSkillCategory category;

    public PredefinedSkill() {}

    public PredefinedSkillCategory getCategory() {
        return category;
    }

    public void setCategory(PredefinedSkillCategory category) {
        this.category = category;
    }
}
