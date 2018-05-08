package cr.talent.core.capability.dao;

import cr.talent.model.Capability;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link cr.talent.model.Capability} related operations.
 *
 * @author Elías Calderón
 */
public interface CapabilityDao extends CrudDao<Capability, String> {

    /**
     * Gets an organization capability with a given name.
     * @param organizationId the capability's organization Id.
     * @param name the name of the capability
     * @return The organization capability if found, null if not found
     */
    Capability getOrganizationCapabilityByName(String organizationId, String name);

    /**
     * Gets a predefined capability with a given name.
     * @param name the name of the capability
     * @return The predefined capability if found, null if not found
     */
    Capability getPredefinedCapabilityByName(String name);
}
