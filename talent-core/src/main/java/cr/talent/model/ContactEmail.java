package cr.talent.model;

/**
 *Class that represents a contact email to contact the administrators of the Talent system.
 *
 * @author Elías Calderón
 */
public class ContactEmail {

    /**
     * The email that the mail will be sent to.
     */
    private String email;

    /**
     * The subject of the email.
     */
    private String subject;

    /**
     * The email content.
     */
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