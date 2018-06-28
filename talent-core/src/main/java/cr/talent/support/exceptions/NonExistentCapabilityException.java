package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when a capability level is attempted to be created within a cabaility that
 * does not exist in the relevant organization.
 *
 * @author Fabián Roberto Leandro
 */
public class NonExistentCapabilityException extends RuntimeException {
    
        private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NonExistentCapabilityException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NonExistentCapabilityException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NonExistentCapabilityException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NonExistentCapabilityException(String message, Throwable cause) {
        super(message, cause);
    }
}
