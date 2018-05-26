package cr.talent.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class that represents a privacy policy for the Talent! system
 * It contains the contents of that privacy policy, as well as a start date, end date, and a flag that
 * tells us if it is active or not
 * @author Daniel Montes de Oca
 */
@Entity
@Table(name = "privacy_policy")
public class PrivacyPolicy extends BasicEntity {

    /**
     * HTML with the content of the privacy policy
     */
    @Lob
    @Column(name = "content")
    private String content;

    /**
     * The date that the privacy policy started being active
     */
    @Column(name = "start_date")
    private Date startDate;

    /**
     * The date that the privacy policy stopped being active
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * Whether the privacy policy is active or not
     */
    @Column(name = "is_active")
    private boolean isActive;

    /**
     * The platform in which Talent is being used
     */
    @Column (name = "platform")
    @Enumerated(value = EnumType.STRING)
    private Platform platform;

    public PrivacyPolicy() {}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof PrivacyPolicy){
            PrivacyPolicy privacyPolicy = (PrivacyPolicy) o;
            result = (this.startDate == null ? privacyPolicy.getStartDate() == null : this.startDate.equals(privacyPolicy.getStartDate()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.startDate == null ? 0 : this.startDate.hashCode());
        result = prime * result + (this.endDate == null ? 0 : this.endDate.hashCode());
        return result;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() { return this.isActive; }

    public void setActive(boolean active) { this.isActive = active; }

    public String getContent() { return this.content; }

    public void setContent(String content) { this.content = content; }

    public Platform getPlatform() { return this.platform; }

    public void setPlatform(Platform platform) { this.platform = platform; }
}
