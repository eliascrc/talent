package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.email.invitationEmail.service.InvitationEmailService}
 * tries to create an invitation for an empty email.
 *
 * @author Elías Calderón
 */
public class EmptyDestinationEmailException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public EmptyDestinationEmailException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public EmptyDestinationEmailException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public EmptyDestinationEmailException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public EmptyDestinationEmailException(String message, Throwable cause) {
        super(message, cause);
    }

}
