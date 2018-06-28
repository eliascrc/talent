package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.invitation.service.InvitationService}
 * is queried being invalid.
 *
 * @author Josue Cubero
 */
public class InvalidInvitationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public InvalidInvitationException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public InvalidInvitationException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public InvalidInvitationException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public InvalidInvitationException(String message, Throwable cause) {
        super(message, cause);
    }

}
