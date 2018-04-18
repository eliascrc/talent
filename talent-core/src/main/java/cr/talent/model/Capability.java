package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

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

    private String name;

    /**
     * The level hierarchy registered for the capability. The hierarchyPosition attribute in the {@link CapabilityLevel}
     * indicates the position in the hierarchy (capability levels for a capability).
     */
    private Set<CapabilityLevel> levelHierarchy;

    public Capability(){}

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CapabilityLevel> getLevelHierarchy() {
        return levelHierarchy;
    }

    public void setLevelHierarchy(Set<CapabilityLevel> levelHierarchy) {
        this.levelHierarchy = levelHierarchy;
    }
}