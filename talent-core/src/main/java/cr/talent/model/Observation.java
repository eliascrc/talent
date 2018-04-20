package cr.talent.model;

import javax.persistence.*;

/**
 * Class that represents an observation(kudo or warning) within the Talent system.
 * It contains the description, observee, the related project and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author María José Cubero
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "observation")
public abstract class Observation extends BasicEntity {

    /**
     * Observation content.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * Technical resource wich is receiving the observation.
     */
    @ManyToOne
    @JoinColumn (name = "resource_id", unique = true)
    private TechnicalResource observee;

    /**
     * The poject that is related to the observation.
     */
    @ManyToOne
    @JoinColumn (name = "project_id")
    private Project relatedProject;

    public Observation(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Observation){
            Observation observation = (Observation) o;
            result = (this.relatedProject == null ? observation.getRelatedProject() == null :
                    this.relatedProject.equals(observation.getRelatedProject()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.relatedProject == null ? 0 : this.relatedProject.hashCode());
        return result;
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