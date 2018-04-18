package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents an organization capability within the Talent system.
 * It contains the information inherited from
 * {@link cr.talent.model.Capability} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "organization_capability")
public class OrganizationCapability extends Capability {

    /**
     * The organization that the capability belongs to.
     */
    private Organization organization;

    public OrganizationCapability (){}

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
