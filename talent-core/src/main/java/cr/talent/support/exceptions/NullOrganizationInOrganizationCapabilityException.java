package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.capability.service.CapabilityService}
 * tries to persist an Organization Capability with a null organization.
 *
 * @author Elías Calderón
 */
public class NullOrganizationInOrganizationCapabilityException extends RuntimeException {
}
