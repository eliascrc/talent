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

    public Organization getOrganizationByUniqueIdentifier(String uniqueIdentifier);
    public String createOrganization(Organization organization) throws AlreadyCreatedOrganizationException;

}
