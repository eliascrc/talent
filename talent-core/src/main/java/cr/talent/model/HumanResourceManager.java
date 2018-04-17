package cr.talent.model;

import java.util.Set;

/**
 * Class that represents a human resource within the Talent system.
 * It contains the list of educational records pending to approve and the information inherited from
 * {@link cr.talent.model.TechnicalResource} class.
 *
 * @author María José Cubero
 */
public class HumanResourceManager extends TechnicalResource {

    /**
     * List of the educations records pending to check.
     */
    private Set<EducationRecord> uncheckedEducationRecords;

    public HumanResourceManager(){}

    public Set<EducationRecord> getUncheckedEducationRecords() {
        return uncheckedEducationRecords;
    }

    public void setUncheckedEducationRecords(Set<EducationRecord> uncheckedEducationRecords) {
        this.uncheckedEducationRecords = uncheckedEducationRecords;
    }
}
