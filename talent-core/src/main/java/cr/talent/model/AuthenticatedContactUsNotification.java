package cr.talent.model;


import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "authenticated_contact_us_notification")
public class AuthenticatedContactUsNotification extends ContactUsNotification {

    @OneToOne
    User user;

    public AuthenticatedContactUsNotification(){}

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
