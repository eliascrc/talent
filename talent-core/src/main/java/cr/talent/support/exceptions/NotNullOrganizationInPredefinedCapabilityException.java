package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.capability.service.CapabilityService}
 * tries to persist a Predefined Capability with a not null organization attribute.
 *
 * @author Elías Calderón
 */
public class NotNullOrganizationInPredefinedCapabilityException extends RuntimeException {
}
