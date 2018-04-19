package cr.talent.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Class that represents a Technical Position within the Talent system, it contains the start date
 * end date and the information inherited from {@link cr.talent.model.Position} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "technical_position")
public class TechnicalPosition extends Position {

    /**
     * The start date of the resource in the technical position.
     */
    @Column(name = "start_date", nullable = false)
    private Date startDate;

    /**
     * The end date of the resource in the technical position.
     */
    @Column(name = "end_date")
    private Date endDate;

    /**
     * The career path where it belongs.
     */
    @ManyToOne
    @JoinColumn(name = "career_path_id", nullable = false)
    private CareerPath careerPath;

    /**
     * Technical position invitation.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "invitation_technical_id")
    private Invitation invitation;

    public TechnicalPosition (){}

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

    public CareerPath getCareerPath() {
        return careerPath;
    }

    public void setCareerPath(CareerPath careerPath) {
        this.careerPath = careerPath;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }
}
