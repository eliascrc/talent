package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents a category for a set of skills within the Talent system.
 * It contains the name of the category and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
public abstract class SkillCategory extends BasicEntity {

    /**
     * The name of the skill category.
     */
    private String name;

    public SkillCategory(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof SkillCategory){
            SkillCategory skillCategory = (SkillCategory) o;
            result = (this.name == null ? skillCategory.getName() == null : this.name.equals(skillCategory.getName()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
