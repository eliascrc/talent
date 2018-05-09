package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.capability.service.CapabilityService}
 * tries to persist a Predefined Capability with a name that is already registered within the system.
 *
 * @author Elías Calderón
 */
public class AlreadyCreatedPredefinedCapabilityException extends RuntimeException {
}
