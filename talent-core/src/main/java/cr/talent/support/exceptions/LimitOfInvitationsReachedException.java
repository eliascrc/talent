package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.invitation.service.InvitationService}
 * tries to create an invitation when the limit of them has been reached
 *
 * @author Elías Calderón
 */
public class LimitOfInvitationsReachedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public LimitOfInvitationsReachedException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public LimitOfInvitationsReachedException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public LimitOfInvitationsReachedException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public LimitOfInvitationsReachedException(String message, Throwable cause) {
        super(message, cause);
    }

}
