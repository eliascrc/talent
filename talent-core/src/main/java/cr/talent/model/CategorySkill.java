package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that represents a category for a set of skills within the Talent system.
 * It contains the name of the category and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "category_skill")
public abstract class CategorySkill extends BasicEntity {

    /**
     * The name of the skill category.
     */
    @Column(name = "name")
    private String name;

    public CategorySkill(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof CategorySkill){
            CategorySkill categorySkill = (CategorySkill) o;
            result = (this.name == null ? categorySkill.getName() == null : this.name.equals(categorySkill.getName()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        return result;
    }

}
