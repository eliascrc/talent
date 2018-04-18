package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents a predefined capability level within the Talent system.
 * It contains the information inherited from
 * {@link cr.talent.model.CapabilityLevel} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "predefined_capability_level")
public class PredefinedCapabilityLevel extends CapabilityLevel{

    /**
     * The predefined capability of this capability level.
     */
    private PredefinedCapability capability;

    public PredefinedCapabilityLevel (){}

    public PredefinedCapability getCapability() {
        return capability;
    }

    public void setCapability(PredefinedCapability capability) {
        this.capability = capability;
    }
}
