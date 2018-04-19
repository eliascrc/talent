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
@Table(name = "technical_manager")
public class TechnicalManager extends TechnicalResourceManager {

    /**
     * A map with the list of skills that each resource is requesting
     */
    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="technical_manager_id")
    @MapKeyJoinColumn( name="resource_id")
    private Map<TechnicalResource, Set<Skill>> skillsToApprove;

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

    public Map<TechnicalResource, Set<Skill>> getSkillsMap() {
        return skillsToApprove;
    }

    public void setSkills(Map<TechnicalResource, Set<Skill>> skills) {
        this.skillsToApprove = skills;
    }
}
