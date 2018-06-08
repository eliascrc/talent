package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents a contact email to contact the administrators of the Talent system.
 *
 * @author María José Cubero, Elías Calderón, Fabián Roberto Leandro
 */
public class ContactEmail extends Email {

    /**
     * The type of issue selected by the user.
     */
    private ContactUsIssueType issueType;

    public ContactEmail(){}

    public ContactUsIssueType getIssueType() {
        return issueType;
    }

    public void setIssueType(ContactUsIssueType issueType) {
        this.issueType = issueType;
    }
}