package cr.talent.model;

/**
 * Class that represents a predefined Skill by a system administrator within the Talent system.
 * It contains the category of the skill and the information inherited from
 * {@link cr.talent.model.Skill} class.
 *
 * @author María José Cubero
 */
public class PredefinedSkill extends Skill {

    /**
     * The predefined skill category that the predefined skill belongs to.
     */
    private PredefinedCategorySkill category;

    public PredefinedSkill() {}

    public PredefinedCategorySkill getCategory() {
        return category;
    }

    public void setCategory(PredefinedCategorySkill category) {
        this.category = category;
    }
}
