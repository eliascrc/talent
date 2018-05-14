package cr.talent.core.organization.dao;

import cr.talent.model.Organization;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link cr.talent.model.Organization} related operations.
 *
 * @author Elías Calderón
 */
public interface OrganizationDao extends CrudDao<Organization, String> {

    /**
     * Gets an organization with a given id.
     * @param uniqueIdentifier the organization's unique identifier.
     * @return The organization if found, null if not found.
     */
    Organization getOrganizationByUniqueIdentifier(String uniqueIdentifier);

}