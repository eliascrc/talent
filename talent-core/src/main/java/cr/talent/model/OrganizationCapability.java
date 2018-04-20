package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents an organization capability within the Talent system.
 * It contains the information inherited from
 * {@link cr.talent.model.Capability} class.
 *
 * @author María José Cubero
 */
@Entity
@DiscriminatorValue(value = "ORGANIZATION_CAPABILITY")
public class OrganizationCapability extends Capability {

    /**
     * The organization that the capability belongs to.
     */
    @ManyToOne
    @JoinColumn(name = "organization_id", nullable = false)
    private Organization organization;

    public OrganizationCapability (){}

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
