package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that represents a Skill within the Talent system. It contains the name of the skill and
 * the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "skill")
public abstract class Skill extends BasicEntity{

    /**
     * The name of the skill
     */
    @Column(name = "name")
    private String name;

    public Skill(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Skill){
            Skill skill = (Skill) o;
            result = (this.name == null ? skill.getName() == null : this.name.equals(skill.getName()));
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
