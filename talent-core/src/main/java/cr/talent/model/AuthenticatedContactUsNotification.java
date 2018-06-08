package cr.talent.model;


import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Class that represents a Contact Us notification submitted by an authenticated user.
 * It contains the capability name, level hierarchy and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Fabián Roberto Leandro
 */
@Entity
@Table(name = "authenticated_contact_us_notification")
public class AuthenticatedContactUsNotification extends ContactUsNotification {

    /**
     * The user that submitted the Contact Us request.
     */
    @OneToOne
    TechnicalResource technicalResource;

    public AuthenticatedContactUsNotification(){}

    public TechnicalResource getTechnicalResource() {
        return technicalResource;
    }

    public void setTechnicalResource(TechnicalResource technicalResource) {
        this.technicalResource = technicalResource;
    }
}
