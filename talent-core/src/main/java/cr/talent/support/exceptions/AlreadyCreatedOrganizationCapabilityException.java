package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.capability.service.CapabilityService}
 * tries to persist an Organization Capability with a name that is already registered within an organization.
 *
 * @author Elías Calderón
 */
public class AlreadyCreatedOrganizationCapabilityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public AlreadyCreatedOrganizationCapabilityException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public AlreadyCreatedOrganizationCapabilityException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public AlreadyCreatedOrganizationCapabilityException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public AlreadyCreatedOrganizationCapabilityException(String message, Throwable cause) {
        super(message, cause);
    }
}
