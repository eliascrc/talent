package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

/**
 * Class that represents a Capability within the Talent system. It contains the capability name,
 * level hierarchy and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "capability")
public abstract class Capability extends BasicEntity {

    /**
     * The name of the capability.
     */
    @Column(name = "name")
    private String name;

    /**
     * The level hierarchy registered for the capability. The hierarchyPosition attribute in the {@link CapabilityLevel}
     * indicates the position in the hierarchy.
     */
    private List<CapabilityLevel> levelHierarchy;

    public Capability(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CapabilityLevel> getLevelHierarchy() {
        return levelHierarchy;
    }

    public void setLevelHierarchy(List<CapabilityLevel> levelHierarchy) {
        this.levelHierarchy = levelHierarchy;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Capability){
            Capability capability = (Capability) o;
            result = (this.name == null ? capability.getName() == null : this.name.equals(capability.getName()));
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