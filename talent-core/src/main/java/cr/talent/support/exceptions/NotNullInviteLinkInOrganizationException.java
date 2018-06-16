package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.organization.service.OrganizationService}
 * tries to create an invite link that already exists for an organization.
 *
 * @author Elías Calderón
 */
public class NotNullInviteLinkInOrganizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NotNullInviteLinkInOrganizationException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NotNullInviteLinkInOrganizationException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NotNullInviteLinkInOrganizationException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NotNullInviteLinkInOrganizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
