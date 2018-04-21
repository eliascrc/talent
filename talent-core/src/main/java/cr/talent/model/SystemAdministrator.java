package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents a System Administrator within the Talent system.
 * It contains the information inherited from {@link cr.talent.model.User} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "system_administrator")
public class SystemAdministrator extends User {

    public SystemAdministrator(){

    }

}
