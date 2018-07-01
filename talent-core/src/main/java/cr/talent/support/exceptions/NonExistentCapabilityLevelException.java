package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when a technical position is attempted to be created with a cabaility level that
 * does not exist within a capability within the relevant organization.
 *
 * @author Fabián Roberto Leandro
 */
public class NonExistentCapabilityLevelException extends RuntimeException {
    
        private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NonExistentCapabilityLevelException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NonExistentCapabilityLevelException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NonExistentCapabilityLevelException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NonExistentCapabilityLevelException(String message, Throwable cause) {
        super(message, cause);
    }
}
