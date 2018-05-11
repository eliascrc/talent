package cr.talent.support.exceptions;

public class NoLoggedInUserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NoLoggedInUserException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NoLoggedInUserException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NoLoggedInUserException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NoLoggedInUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
