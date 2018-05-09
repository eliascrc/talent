package cr.talent.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Contains the Terms of Service of the application, and stores its start date and end date, as well as a flag
 * in order to obtain the latest version (active) for the user to see it. It also contains the information inherited
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Josue Leon Sarkis
 */
@Entity
@Table(name = "terms_of_service")
public class TermsOfService extends BasicEntity {

    /**
     * Stores the content of the Terms of Service in HTML format.
     */
    @Lob
    @Column(name = "terms_of_service_content", nullable = false)
    private String termsOfServiceContent;

    /**
     * Date in which the respective Terms of Service were added to the application.
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * Date in which the respective Terms of Service were removed from the application and replaced by
     * another version.
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * Flag that specifies if the Terms of Service version is active or not.
     */
    @Column(name = "is_active")
    private boolean isActive;

    public TermsOfService() {
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if (o instanceof TermsOfService) {
            TermsOfService termsOfService = (TermsOfService) o;
            result = (this.startDate == null ? termsOfService.getStartDate() == null : this.startDate.equals(termsOfService.getStartDate()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.startDate == null ? 0 : this.startDate.hashCode());
        return result;
    }

    public String getToSContent() {
        return termsOfServiceContent;
    }

    public void setToSContent(String toSContent) {
        termsOfServiceContent = toSContent;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
