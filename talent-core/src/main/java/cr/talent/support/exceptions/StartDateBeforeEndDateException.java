package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when a start date is greater than an end date
 *
 * @author Daniel Montes de Oca
 */
public class StartDateBeforeEndDateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public StartDateBeforeEndDateException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public StartDateBeforeEndDateException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public StartDateBeforeEndDateException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public StartDateBeforeEndDateException(String message, Throwable cause) {
        super(message, cause);
    }

}
