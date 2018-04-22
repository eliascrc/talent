package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents a predefined capability level within the Talent system.
 * It contains the information inherited from
 * {@link cr.talent.model.CapabilityLevel} class.
 *
 * @author María José Cubero
 */
@Entity
@DiscriminatorValue(value = "PREDEFINED_CAPABILITY_LEVEL")
public class PredefinedCapabilityLevel extends CapabilityLevel {

    /**
     * The capability of the capability level.
     */
    @ManyToOne
    @JoinColumn(name = "pre_capability_id", nullable = false)
    private PredefinedCapability capability;

    public PredefinedCapabilityLevel (){}

    public PredefinedCapability getCapability() {
        return capability;
    }

    public void setCapability(PredefinedCapability capability) {
        this.capability = capability;
    }
}
