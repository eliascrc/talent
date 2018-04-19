package cr.talent.model;

import javax.persistence.*;
import java.util.Map;
import java.util.Set;

/**
 * Class that represents a Technical Manager within the Talent system, it contains a list of the resources
 * under its charge and a list to check the uncheckedSkills. It also
 * contains the information inherited from {@link cr.talent.model.TechnicalResourceManager} class.
 *
 * @author María José Cubero
 */
@Entity
@DiscriminatorValue(value = "TECHNICAL_MANAGER")
public class TechnicalManager extends TechnicalResourceManager {

    /**
     * A map with the list of skills that each resource is requesting
     */
    @ElementCollection
    @CollectionTable(name="skills_to_approve",
            joinColumns=@JoinColumn(name="technical_manager_id"))
    private Set<SkillToApprove> skillsToApprove;

    /**
     * A list with the technical resources that the Technical Manager has under charge.
     */
    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "technicalManager")
    private Set<TechnicalResource> managedResources;

    public TechnicalManager(){}

    public Set<TechnicalResource> getManagedResources() {
        return managedResources;
    }

    public void setManagedResources(Set<TechnicalResource> managedResources) {
        this.managedResources = managedResources;
    }

    public Set<SkillToApprove> getSkillsToApprove() {
        return skillsToApprove;
    }

    public void setSkillsToApprove(Set<SkillToApprove> skillsToApprove) {
        this.skillsToApprove = skillsToApprove;
    }
}
