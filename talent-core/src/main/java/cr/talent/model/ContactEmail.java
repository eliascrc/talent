package cr.talent.model;

import javax.persistence.*;

/**
 *Class that represents a contact email to contact the administrators of the Talent system.
 *
 * @author Elías Calderón, Fabián Roberto Leandro
 */
@Entity
@Table(name = "contact_email")
public class ContactEmail extends BasicEntity {

    /**
     * The email to which Talent!'s response will be sent to.
     */
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "issueType", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ContactEmailIssueType issueType;

    /**
     * The email content.
     */
    @Column(name = "content", nullable = false)
    private String content;

    /**
     * The first name of the person contacting Talent!.
     */
    @Column(name = "firstName", nullable = false)
    private String firstName;

    /**
     * The last name of the person contacting Talent!.
     */
    @Column(name = "lastName", nullable = false)
    private String lastName;

    public ContactEmail(){}

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFirstName() { return firstName; }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public ContactEmailIssueType getIssueType() { return issueType; }

    public void setIssueType(ContactEmailIssueType issueType) { this.issueType = issueType; }

    @Override
    public boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof ContactEmail){
            ContactEmail contactEmail = (ContactEmail) o;
            result = (this.email == null ? contactEmail.getEmail() == null : this.email.equals(contactEmail.getEmail()));
        }
        return result;
    }

    @Override
    public int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.email == null ? 0 : this.email.hashCode());
        return result;
    }
}