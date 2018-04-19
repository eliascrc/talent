package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Class that represents a warning within the Talent system.
 * It contains the author and the information inherited from
 * {@link cr.talent.model.Observation} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "warning")
public class Warning extends Observation {

    /**
     * The technical resource that writes the warning.
     */
    @ManyToOne
    @JoinColumn(name = "resource_id")
    private TechnicalResourceManager author;

    public Warning(){}

    public TechnicalResourceManager getAuthor() {
        return author;
    }

    public void setAuthor(TechnicalResourceManager author) {
        this.author = author;
    }
}