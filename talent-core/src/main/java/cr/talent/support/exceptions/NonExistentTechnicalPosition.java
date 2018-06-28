package cr.talent.support.exceptions;

public class NonExistentTechnicalPosition extends RuntimeException {
    
        private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NonExistentTechnicalPosition() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NonExistentTechnicalPosition(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NonExistentTechnicalPosition(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NonExistentTechnicalPosition(String message, Throwable cause) {
        super(message, cause);
    }
}
