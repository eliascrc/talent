package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.capability.service.CapabilityService}
 * tries to persist an Organization Capability with a null organization.
 *
 * @author Elías Calderón
 */
public class NullOrganizationInOrganizationCapabilityException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NullOrganizationInOrganizationCapabilityException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NullOrganizationInOrganizationCapabilityException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NullOrganizationInOrganizationCapabilityException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NullOrganizationInOrganizationCapabilityException(String message, Throwable cause) {
        super(message, cause);
    }

}
