package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents a human resource within the Talent system.
 * It contains the list of educational records pending to approve and the information inherited from
 * {@link cr.talent.model.TechnicalResource} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "human_resource_manager")
public class HumanResourceManager extends TechnicalResource {

    /**
     * List of the educations records pending to check.
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "humanResourceManager")
    private Set<EducationRecord> uncheckedEducationRecords;

    public HumanResourceManager(){}

    public Set<EducationRecord> getUncheckedEducationRecords() {
        return uncheckedEducationRecords;
    }

    public void setUncheckedEducationRecords(Set<EducationRecord> uncheckedEducationRecords) {
        this.uncheckedEducationRecords = uncheckedEducationRecords;
    }
}
