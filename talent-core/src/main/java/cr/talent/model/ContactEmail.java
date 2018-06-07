package cr.talent.model;

import javax.persistence.*;

/**
 *Class that represents a contact email to contact the administrators of the Talent system.
 *
 * @author María José Cubero, Elías Calderón
 */
public class ContactEmail extends Email {

    /**
     * The type of issue selected by the user.
     */
    private ContactEmailIssueType issueType;

    public ContactEmail(){
        super();
    }

    public ContactEmailIssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(ContactEmailIssueType issueType) {
        this.issueType = issueType;
    }
}