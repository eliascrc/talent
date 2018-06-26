package cr.talent.model;

import javax.persistence.*;

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
    @OneToOne (mappedBy = "profilePicture")
    private TechnicalResource technicalResource;

    public ProfilePicture (){}

    public TechnicalResource getTechnicalResource() {
        return technicalResource;
    }

    public void setTechnicalResource(TechnicalResource technicalResource) {
        this.technicalResource = technicalResource;
    }
}
