package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *Class that represents a contact email to contact the administrators of the Talent system.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "contact_email")
public class ContactEmail {

    /**
     * The email that the mail will be sent to.
     */
    @Column(name = "email" , nullable = false)
    private String email;

    /**
     * The subject of the email.
     */
    @Column (name = "subject")
    private String subject;

    /**
     * The email content.
     */
    @Column (name = "content")
    private String content;

    public ContactEmail(){}

    public String getEmail() {
        return email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}