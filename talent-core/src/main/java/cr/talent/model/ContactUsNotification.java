package cr.talent.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class that represents a basic Contact Us notification sent ot the admins of Talent!.
 *
 *  @author Fabián Roberto Leandro
 */
@MappedSuperclass
public abstract class ContactUsNotification extends BasicEntity {

    @Column (name = "issue", nullable = false)
    private String issue;

    @Column (name = "issue_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    private ContactUsIssueType issueType;

    @Column(name = "date_solved")
    private Date dateSolved;

    public ContactUsNotification() {}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof ContactUsNotification){
            ContactUsNotification contactUsNotification = (ContactUsNotification) o;
            result = (this.issue == null ? contactUsNotification.getIssue() == null :
                    this.issue.equals(contactUsNotification.getIssue())
                    && this.issueType == null ? contactUsNotification.getIssueType() == null :
                    this.issueType.equals(contactUsNotification.getIssueType())
                    && this.dateSolved == null ? contactUsNotification.getDateSolved() == null :
                    this.dateSolved.equals(contactUsNotification.getDateSolved()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.issue == null ? 0 : this.issue.hashCode());
        result = prime * result + (this.issueType == null ? 0 : this.issueType.hashCode());
        result = prime * result + (this.dateSolved == null ? 0 : this.dateSolved.hashCode());
        return result;
    }

    public Date getDateSolved() {
        return dateSolved;
    }

    public void setDateSolved(Date dateSolved) {
        this.dateSolved = dateSolved;
    }

    public String getIssue() { return issue; }

    public void setIssue(String issue) { this.issue = issue; }

    public ContactUsIssueType getIssueType() { return issueType; }

    public void setIssueType(ContactUsIssueType issueType) { this.issueType = issueType; }

}
