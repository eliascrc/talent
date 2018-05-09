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
     * Retrieves from the database an organization with a given unique identifier
     * @param uniqueIdentifier the unique identifier of the desired organization
     * @return the organization if found, null otherwise
     */
    Organization getOrganizationWithUniqueId(String uniqueIdentifier);

}
