package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.capability.service.CapabilityService}
 * tries to persist a Predefined Capability with a name that is already registered within the system.
 *
 * @author Elías Calderón
 */
public class AlreadyCreatedPredefinedCapabilityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public AlreadyCreatedPredefinedCapabilityException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public AlreadyCreatedPredefinedCapabilityException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public AlreadyCreatedPredefinedCapabilityException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public AlreadyCreatedPredefinedCapabilityException(String message, Throwable cause) {
        super(message, cause);
    }


}
