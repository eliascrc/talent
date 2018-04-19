package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents a Technical Resource Manager within the Talent system.
 * It contains the information inherited from {@link cr.talent.model.TechnicalResource} class.
 *
 * @author Elías Calderón
 */
@MappedSuperclass
public class TechnicalResourceManager extends TechnicalResource {

    /**
     * List of the observations that that resouce has made.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "autor")
    private Set<Warning> madeWarnings;

    public TechnicalResourceManager(){}

    public Set<Warning> getMadeWarnings() {
        return madeWarnings;
    }

    public void setMadeWarnings(Set<Warning> madeWarnings) {
        this.madeWarnings = madeWarnings;
    }
}
