package cr.talent.model;

import java.util.Date;

/**
 * Class that represents a Technical Position within the Talent system, it contains the start date
 * end date and the information inherited from {@link cr.talent.model.Position} class.
 *
 * @author María José Cubero
 */
public class TechnicalPosition extends Position {

    /**
     * The start date of the resource in the technical position.
     */
    private Date startDate;

    /**
     * The end date of the resource in the technical position.
     */
    private Date endDate;

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
}
