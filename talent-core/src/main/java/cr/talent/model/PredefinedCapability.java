package cr.talent.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents a predefined capability within the Talent system.
 * It contains the information inherited from
 * {@link cr.talent.model.Capability} class.
 *
 * @author María José Cubero
 */
@Entity
@DiscriminatorValue(value = "PREDEFINED_CAPABILITY")
public class PredefinedCapability extends Capability {

    public PredefinedCapability(){}

}
