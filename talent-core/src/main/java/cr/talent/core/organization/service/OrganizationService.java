package cr.talent.core.organization.service;

import cr.talent.model.Organization;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.Organization} entities.
 *
 * @author Elías Calderón
 */
public interface OrganizationService extends CrudService<Organization, String> {

    /**
     * Retrieves an organization with a given unique identifier
     * @param uniqueIdentifier the unique identifier of the organization
     * @return the organization if found, null otherwise
     */
    Organization getOrganizationWithUniqueId(String uniqueIdentifier);

}
