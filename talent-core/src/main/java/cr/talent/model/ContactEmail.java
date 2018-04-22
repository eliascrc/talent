package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

    public boolean equals(Object o) {
        boolean result = false;
        if ( o instanceof Image){
            ContactEmail contactEmail = (ContactEmail) o;
            result = (this.email == null ? contactEmail.getEmail() == null : this.email.equals(contactEmail.getEmail()));
        }
        return result;
    }

    public int hashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.email == null ? 0 : this.email.hashCode());
        return result;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}