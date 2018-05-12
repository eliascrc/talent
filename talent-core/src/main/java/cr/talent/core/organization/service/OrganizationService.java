package cr.talent.core.organization.service;

import cr.talent.model.Organization;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationException;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.Organization} entities.
 *
 * @author Elías Calderón
 */
public interface OrganizationService extends CrudService<Organization, String> {

    /**
     * Gets an organization with a given id.
     * @param uniqueIdentifier the organization's unique identifier
     * @return The organization if found, null if not found.
     */
    Organization getOrganizationByUniqueIdentifier(String uniqueIdentifier);

    /**
     * Persists an organization with the respective business logic.
     * @param organization the organization to create.
     * @return The organization's id.
     */
    String createOrganization(Organization organization);

}
