package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Class that represents a Technical Manager within the Talent system, it contains a list of the resources
 * under its charge and a reference to the skills to check ({@link cr.talent.model.UncheckedSkills}. It also
 * contains the information inherited from {@link cr.talent.model.TechnicalResourceManager} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name="technical_manager")
public class TechnicalManager extends TechnicalResourceManager {

    /**
     * A list with the technical resources that the Technical Manager has under charge.
     */
    @Column(name="managed_resources")
    private List<TechnicalResource> managedResources;

    /**
     * A reference to a map of the technical resources that currently have one or more unchecked skills.
     */
    private UncheckedSkills uncheckedSkills;

    public List<TechnicalResource> getManagedResources() {
        return managedResources;
    }

    public void setManagedResources(List<TechnicalResource> managedResources) {
        this.managedResources = managedResources;
    }

    public UncheckedSkills getUncheckedSkills() {
        return uncheckedSkills;
    }

    public void setUncheckedSkills(UncheckedSkills uncheckedSkills) {
        this.uncheckedSkills = uncheckedSkills;
    }
}
