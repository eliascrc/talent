package cr.talent.model;

import java.util.Set;

/**
 * Class that represents a predefined capability level within the Talent system.
 * It contains the information inherited from
 * {@link cr.talent.model.CategorySkill} class.
 *
 * @author María José Cubero
 */
public class PredefinedCategorySkill extends CategorySkill{

    /**
     * The list of the predefined Skills of this category;
     */
    private Set<PredefinedSkill> predefinedSkills;

    public PredefinedCategorySkill () {}

    public Set<PredefinedSkill> getPredefinedSkills() {
        return predefinedSkills;
    }

    public void setPredefinedSkills(Set<PredefinedSkill> predefinedSkills) {
        this.predefinedSkills = predefinedSkills;
    }
}
