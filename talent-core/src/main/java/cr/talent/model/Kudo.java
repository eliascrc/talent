package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents a kudo observation within the Talent system.
 * It contains the technical resource that writes the kudo and the information inherited from
 * {@link cr.talent.model.Observation} class.
 *
 * @author María José Cubero
 */
@Entity
@DiscriminatorValue(value = "KUDO")
public class Kudo extends Observation {

    /**
     * The technical resource that writes the kudo.
     */
    @ManyToOne
    @JoinColumn (name = "author_id")
    private TechnicalResource author;

    public Kudo (){}

    public TechnicalResource getAuthor() {
        return author;
    }

    public void setAuthor(TechnicalResource author) {
        this.author = author;
    }
}