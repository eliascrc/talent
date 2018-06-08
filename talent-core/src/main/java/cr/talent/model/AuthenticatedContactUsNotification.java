package cr.talent.model;


import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Class that represents a Contact Us notification submitted by an authenticated user.
 * It contains the capability name, level hierarchy and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Fabián Roberto Leandro
 */
@Entity
@Table(name = "authenticated_contact_us_notification")
public class AuthenticatedContactUsNotification extends ContactUsNotification {

    /**
     * The user that submitted the Contact Us request.
     */
    @OneToOne
    private TechnicalResource technicalResource;

    public AuthenticatedContactUsNotification(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof AuthenticatedContactUsNotification){
            AuthenticatedContactUsNotification contactUsNotification = (AuthenticatedContactUsNotification) o;
            result = (this.issue == null ? contactUsNotification.getIssue() == null :
                    this.issue.equals(contactUsNotification.getIssue())
                    && this.issueType == null ? contactUsNotification.getIssueType() == null :
                    this.issueType.equals(contactUsNotification.getIssueType())
                    && this.technicalResource == null ? contactUsNotification.getTechnicalResource() == null :
                    this.technicalResource.equals(contactUsNotification.getTechnicalResource()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.issue == null ? 0 : this.issue.hashCode());
        result = prime * result + (this.issueType == null ? 0 : this.issueType.hashCode());
        result = prime * result + (this.technicalResource == null ? 0 : this.technicalResource.hashCode());
        return result;
    }

    public TechnicalResource getTechnicalResource() {
        return technicalResource;
    }

    public void setTechnicalResource(TechnicalResource technicalResource) {
        this.technicalResource = technicalResource;
    }
}
