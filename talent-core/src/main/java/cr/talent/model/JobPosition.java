package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Class that represents a job position within the Talent system.
 * It contains the start date, end date and the information inherited from
 * {@link cr.talent.model.Position} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "job_position")
public class JobPosition extends Position {

    /**
     * Start date of the job position.
     */
    private Date startDate;

    /**
     * End date of the job position.
     */
    private Date endDate;

    public JobPosition (){}

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
}
