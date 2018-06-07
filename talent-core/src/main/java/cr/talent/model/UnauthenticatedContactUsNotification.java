package cr.talent.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "unauthenticated_contact_us_notification")
public class UnauthenticatedContactUsNotification extends ContactUsNotification {

    /**
     * The first name of the person who sent the Contact Us request.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the person who sent the Contact Us request.
     */
    @Column(name = "last_name")
    private String lastName;

    public UnauthenticatedContactUsNotification(){}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
