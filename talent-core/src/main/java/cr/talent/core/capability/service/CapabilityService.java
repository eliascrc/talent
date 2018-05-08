package cr.talent.core.capability.service;

import cr.talent.model.Capability;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationCapabilityException;
import cr.talent.support.exceptions.AlreadyCreatedPredefinedCapabilityException;
import cr.talent.support.exceptions.NotNullOrganizationInPredefinedCapabilityException;
import cr.talent.support.exceptions.NullOrganizationInOrganizationCapabilityException;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.Capability} entities.
 *
 * @author Elías Calderón
 */
public interface CapabilityService extends CrudService<Capability, String> {

    /**
     * Persists an organization capability within an organization.
     * @param organizationCapability the capability.
     * @return the Id of the organization capability.
     * @throws NullOrganizationInOrganizationCapabilityException An Organization Capability should
     * never have an empty organization.
     * @throws AlreadyCreatedOrganizationCapabilityException If the Organization Capability's name is already
     * registered within the organization.
     */
    String createOrganizationCapability(Capability organizationCapability)
            throws NullOrganizationInOrganizationCapabilityException, AlreadyCreatedOrganizationCapabilityException;

    /**
     * Persists a predefined capability within the system.
     * @param predefinedCapability the capability.
     * @return the Id of the predefined capability.
     * @throws NotNullOrganizationInPredefinedCapabilityException A Predefined Capability should never
     * have an organization.
     * @throws AlreadyCreatedPredefinedCapabilityException If the Predefined Capability's name is already
     * registered within the system.
     */
    String createPredefinedCapability(Capability predefinedCapability)
            throws NotNullOrganizationInPredefinedCapabilityException, AlreadyCreatedPredefinedCapabilityException;
}
