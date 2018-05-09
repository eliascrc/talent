package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.capability.service.CapabilityService}
 * tries to persist an Organization Capability with a name that is already registered within an organization.
 *
 * @author Elías Calderón
 */
public class AlreadyCreatedOrganizationCapabilityException extends RuntimeException {
}
