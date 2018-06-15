package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.invitation.service.InvitationService}
 * tries to create an invitation for an user that is already registered in the organization.
 *
 * @author Elías Calderón
 */
public class AlreadyRegisteredUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public AlreadyRegisteredUserException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public AlreadyRegisteredUserException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public AlreadyRegisteredUserException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public AlreadyRegisteredUserException(String message, Throwable cause) {
        super(message, cause);
    }

}
