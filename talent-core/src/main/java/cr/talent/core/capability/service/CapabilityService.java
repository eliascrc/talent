package cr.talent.core.capability.service;

import cr.talent.model.Capability;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationCapabilityException;
import cr.talent.support.exceptions.NullOrganizationInOrganizationCapabilityException;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.Capability} entities.
 *
 * @author Elías Calderón
 */
public interface CapabilityService extends CrudService<Capability, String> {

    String createOrganizationCapability(Capability capability)
            throws NullOrganizationInOrganizationCapabilityException, AlreadyCreatedOrganizationCapabilityException;

}
