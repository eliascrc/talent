package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents an organization logo within the Talent system.
 * It contains the link of the image and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */

@Entity
@DiscriminatorValue(value = "ORGANIZATION_LOGO")
public class OrganizationLogo extends Image {

    /**
     * Organization that owns the logo.
     */
    @OneToOne(mappedBy = "logo")
    private Organization organization;

    public OrganizationLogo (){}

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
