package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents a Career Path within the Talent system. It contains list of the positions
 * and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "career_path")
public class CareerPath extends BasicEntity {

    /**
     * Technical resource wich the Career Path belongs to.
     */
    @OneToOne (mappedBy = "careerPath")
    private TechnicalResource technicalResource;

    /**
     * List of the positions of this career path.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "careerPath")
    private Set<TechnicalPosition> positions;

    public CareerPath(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof CareerPath){
            CareerPath careerPath = (CareerPath) o;
            result = (this.technicalResource == null ?
                    careerPath.getTechnicalResource() == null : this.technicalResource.equals(careerPath.getTechnicalResource()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.technicalResource == null ? 0 : this.technicalResource.hashCode());
        return result;
    }

    public Set<TechnicalPosition> getPositions() {
        return positions;
    }

    public void setPositions(Set<TechnicalPosition> positions) {
        this.positions = positions;
    }

    public TechnicalResource getTechnicalResource() {
        return technicalResource;
    }

    public void setTechnicalResource(TechnicalResource technicalResource) {
        this.technicalResource = technicalResource;
    }
}