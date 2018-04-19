package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents a Capability Level within the Talent system.
 * It contains the capability level name, the level hierarchy position and the information inherited
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "capability_level")
public abstract class CapabilityLevel extends BasicEntity {

    /**
     * The name of the capability level
     */
    @Column (name = "name" , nullable = false)
    private String name;

    /**
     * The position in the level hierarchy for the capability, that the level possesses.
     */
    @Column (name = "hierarchy_position" , nullable = false)
    private int hierarchyPosition;

    public CapabilityLevel(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof CapabilityLevel){
            CapabilityLevel capabilityLevel = (CapabilityLevel) o;
            result = (this.name == null ? capabilityLevel.getName() == null : this.name.equals(capabilityLevel.getName()));
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

    public int getHierarchyPosition() {
        return hierarchyPosition;
    }

    public void setHierarchyPosition(int hierarchyPosition) {
        this.hierarchyPosition = hierarchyPosition;
    }
}