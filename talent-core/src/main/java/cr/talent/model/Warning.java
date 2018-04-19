package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents a warning within the Talent system.
 * It contains the author and the information inherited from
 * {@link cr.talent.model.Observation} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "warning")
@DiscriminatorValue(value = "WARNING")
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