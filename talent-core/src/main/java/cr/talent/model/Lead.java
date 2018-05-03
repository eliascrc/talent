package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents a Project Manager within the Talent system. It contains the list of project
 * management positions that the Project Manager has occupied. It also contains the information inherited from
 * {@link cr.talent.model.TechnicalResourceManager} class.
 *
 * @author María José Cubero
 */
@Entity
@DiscriminatorValue(value = "LEAD")
public class Lead extends TechnicalResourceManager {

    /**
     * A list with the project management positions that the Lead has occupied in the organization.
     */
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "lead")
    private Set<LeadPosition> leadPositions;

    public Lead(){}

    public Set<LeadPosition> getLeadPositions() {
        return leadPositions;
    }

    public void setLeadPositions(Set<LeadPosition> leadPositions) {
        this.leadPositions = leadPositions;
    }
}
