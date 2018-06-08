package cr.talent.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Class that represents a Contact Us notification submitted by an unauthenticated user.
 * It contains the capability name, level hierarchy and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Fabián Roberto Leandro
 */
@Entity
@Table(name = "unauthenticated_contact_us_notification")
public class UnauthenticatedContactUsNotification extends ContactUsNotification {

    /**
     * The first name of the person who submitted the Contact Us request.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the person who submitted the Contact Us request.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The last name of the person who submitted the Contact Us request.
     */
    @Column(name = "email")
    private String email;

    public UnauthenticatedContactUsNotification(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof UnauthenticatedContactUsNotification){
            UnauthenticatedContactUsNotification contactUsNotification = (UnauthenticatedContactUsNotification) o;
            result = (this.issue == null ? contactUsNotification.getIssue() == null :
                    this.issue.equals(contactUsNotification.getIssue())
                    && this.issueType == null ? contactUsNotification.getIssueType() == null :
                    this.issueType.equals(contactUsNotification.getIssueType())
                    && this.email == null ? contactUsNotification.getEmail() == null :
                    this.email.equals(contactUsNotification.getEmail())
                    && this.firstName == null ? contactUsNotification.getFirstName() == null :
                    this.firstName.equals(contactUsNotification.getFirstName())
                    && this.lastName == null ? contactUsNotification.getLastName() == null :
                    this.lastName.equals(contactUsNotification.getLastName()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.issue == null ? 0 : this.issue.hashCode());
        result = prime * result + (this.issueType == null ? 0 : this.issueType.hashCode());
        result = prime * result + (this.email == null ? 0 : this.email.hashCode());
        result = prime * result + (this.firstName == null ? 0 : this.firstName.hashCode());
        result = prime * result + (this.lastName == null ? 0 : this.lastName.hashCode());
        return result;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
