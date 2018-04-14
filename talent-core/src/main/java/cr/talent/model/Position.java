package cr.talent.model;

/**
 * Class that represents a Technical Resource's position within the Talent system.
 * It contains the capability level related to the position and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
public abstract class Position extends BasicEntity {

    /**
     * The capability and level related to the position. The organization's capability can be found as an attribute
     * in {@link OrganizationCapabilityLevel}
     */
    private OrganizationCapabilityLevel organizationCapabilityLevel;

    /**
     * The technical resource that has the position
     */
    private TechnicalResource technicalResource;

    public Position(){}


    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Position){
            Position position = (Position) o;
            result = (this.organizationCapabilityLevel == null ?
                    position.getOrganizationCapabilityLevel() == null :
                    this.organizationCapabilityLevel.equals(position.getOrganizationCapabilityLevel()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.organizationCapabilityLevel == null ? 0 : this.organizationCapabilityLevel.hashCode());
        return result;
    }

    public OrganizationCapabilityLevel getOrganizationCapabilityLevel() {
        return organizationCapabilityLevel;
    }

    public void setOrganizationCapabilityLevel(OrganizationCapabilityLevel organizationCapabilityLevel) {
        this.organizationCapabilityLevel = organizationCapabilityLevel;
    }

    public TechnicalResource getTechnicalResource() {
        return technicalResource;
    }

    public void setTechnicalResource(TechnicalResource technicalResource) {
        this.technicalResource = technicalResource;
    }
}