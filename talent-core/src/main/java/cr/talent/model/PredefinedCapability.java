package cr.talent.model;

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
@Table(name = "predefined_capability")
public class PredefinedCapability extends Capability {

    public PredefinedCapability(){}

}
