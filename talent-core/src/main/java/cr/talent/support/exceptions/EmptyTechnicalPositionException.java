package cr.talent.support.exceptions;

public class EmptyTechnicalPositionException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public EmptyTechnicalPositionException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public EmptyTechnicalPositionException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public EmptyTechnicalPositionException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public EmptyTechnicalPositionException(String message, Throwable cause) {
        super(message, cause);
    }
}
