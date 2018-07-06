package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents an profile picture within the Talent system.
 * It contains the link of the image and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */

@Entity
@DiscriminatorValue(value = "PROFILE_PICTURE")
public class ProfilePicture extends Image {

    /**
     * Technical resource that owns the profile picture.
     */
    @OneToMany(mappedBy = "profilePicture")
    private Set<TechnicalResource> technicalResources;

    public ProfilePicture (){}

    public Set<TechnicalResource> getTechnicalResource() {
        return technicalResources;
    }

    public void setTechnicalResource(Set<TechnicalResource> technicalResources) {
        this.technicalResources = technicalResources;
    }
}
