package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService}
 * tries to find a sign up confirmation message for a user that just has one active.
 *
 * @author Daniel Montes de Oca
 */
public class NonExistentConfirmationMessageException extends RuntimeException  {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NonExistentConfirmationMessageException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NonExistentConfirmationMessageException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NonExistentConfirmationMessageException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NonExistentConfirmationMessageException(String message, Throwable cause) {
        super(message, cause);
    }

}