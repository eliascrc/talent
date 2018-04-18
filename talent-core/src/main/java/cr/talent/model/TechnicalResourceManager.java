package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents a Technical Resource Manager within the Talent system.
 * It contains the information inherited from {@link cr.talent.model.TechnicalResource} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "technical_resource_manager")
public class TechnicalResourceManager extends TechnicalResource {

    public TechnicalResourceManager(){}

}
