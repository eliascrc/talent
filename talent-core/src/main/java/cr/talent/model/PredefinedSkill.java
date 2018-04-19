package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class that represents a predefined Skill by a system administrator within the Talent system.
 * It contains the category of the skill and the information inherited from
 * {@link cr.talent.model.Skill} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "predefined_skill")
public class PredefinedSkill extends Skill {

    /**
     * The predefined skill category that the predefined skill belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "pre_skill_category_id", nullable = false)
    private PredefinedSkillCategory category;

    public PredefinedSkill() {}

    public PredefinedSkillCategory getCategory() {
        return category;
    }

    public void setCategory(PredefinedSkillCategory category) {
        this.category = category;
    }
}
