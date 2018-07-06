package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

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
    @OneToMany(mappedBy = "logo")
    private Set<Organization> organizations;

    public OrganizationLogo (){}

    public Set<Organization> getOrganization() {
        return organizations;
    }

    public void setOrganization(Set<Organization> organizations) {
        this.organizations = organizations;
    }
}
