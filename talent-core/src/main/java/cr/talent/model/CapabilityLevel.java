package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Class that represents a Capability Level within the Talent system. It contains the capability level name,
 * capability, the level hierarchy position and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "capablity_level")
public abstract class CapabilityLevel extends BasicEntity {

    /**
     * The name of the capability level
     */
    @Column (name="name")
    @Id
    private String name;

    /**
     * The respective capability that the level belongs to.
     */
    @Column (name="capability")
    @Id
    private Capability capability;

    /**
     * The position in the level hierarchy for the capability, that the level possesses.
     */
    @Column(name ="hierarchy_position")
    private int hierarchyPosition;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Capability getCapability() {
        return capability;
    }

    public void setCapability(Capability capability) {
        this.capability = capability;
    }

    public int getHierarchyPosition() {
        return hierarchyPosition;
    }

    public void setHierarchyPosition(int hierarchyPosition) {
        this.hierarchyPosition = hierarchyPosition;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof CapabilityLevel){
            CapabilityLevel capabilityLevel = (CapabilityLevel) o;
            result = (this.name == null ? capabilityLevel.getName() == null : this.name.equals(capabilityLevel.getName())
                    && this.capability == null ? capabilityLevel.getCapability() == null : this.capability.equals(capabilityLevel.getCapability()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        result = prime * result + (this.capability == null ? 0 : this.capability.hashCode());
        return result;
    }
}