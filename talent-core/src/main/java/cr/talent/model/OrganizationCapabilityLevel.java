package cr.talent.model;

import java.util.ArrayList;
import java.util.Set;

/**
 * Class that represents an organization capability within the Talent system.
 * It contains the information inherited from
 * {@link cr.talent.model.CapabilityLevel} class.
 *
 * @author Elías Calderón
 */
public class OrganizationCapabilityLevel extends CapabilityLevel {

    /**
     * The capability that the level belongs to.
     */
    private OrganizationCapability capability;

    /**
     * The list of the resources that have the capability level.
     */
    private Set<TechnicalResource> resources;

    /**
     * The list of the requiered skills to have that capability level.
     */
    private Set<OrganizationSkill> requiredSkills;

    /**
     * A list with the positions that have this capability level.
     */
    private ArrayList<Position> positions;

    public OrganizationCapabilityLevel() {}

    public OrganizationCapability getCapability() {
        return capability;
    }

    public void setCapability(OrganizationCapability capability) {
        this.capability = capability;
    }

    public Set<TechnicalResource> getResources() {
        return resources;
    }

    public void setResources(Set<TechnicalResource> resources) {
        this.resources = resources;
    }

    public Set<OrganizationSkill> getRequiredSkills() {
        return requiredSkills;
    }

    public void setRequiredSkills(Set<OrganizationSkill> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public ArrayList<Position> getPositions() {
        return positions;
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }
}
