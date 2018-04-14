package cr.talent.model;

/**
 * Class that represents an observation(kudo or warning) within the Talent system.
 * It contains the description, observee, the related project and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
public class Observation extends BasicEntity {

    /**
     * Observation content.
     */
    private String description;

    /**
     * Technical resource wich is receiving the observation.
     */
    private TechnicalResource observee;

    /**
     * The poject that is related to the observation.
     */
    private Project relatedProject;

    public Observation(){}

    @Override
    protected boolean onEquals(Object o) {
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TechnicalResource getObservee() {
        return observee;
    }

    public void setObservee(TechnicalResource observee) {
        this.observee = observee;
    }

    public Project getRelatedProject() {
        return relatedProject;
    }

    public void setRelatedProject(Project relatedProject) {
        this.relatedProject = relatedProject;
    }
}