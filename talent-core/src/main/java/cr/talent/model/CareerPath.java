package cr.talent.model;

import java.util.Set;

/**
 * Class that represents a Career Path within the Talent system. It contains list of the positions
 * and the information inherited from {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
public class CareerPath extends BasicEntity {

    /**
     * Technical resource wich the Career Path belongs to.
     */
    private TechnicalResource technicalResource;

    /**
     * List of the positions of this career path
     */
    private Set<TechnicalPosition> positions;

    public CareerPath(){}

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
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