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
    protected String issue;

    @Column (name = "issue_type", nullable = false)
    @Enumerated(value = EnumType.STRING)
    protected ContactUsIssueType issueType;

    @Column(name = "date_solved")
    protected Date dateSolved;

    public ContactUsNotification() {}

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
