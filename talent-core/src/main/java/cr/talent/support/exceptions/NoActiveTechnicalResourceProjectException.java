package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.project.service.ProjectService} tries to
 * retrieve a Privacy Policy and there is no currently active content.
 */
public class NoActiveTechnicalResourceProjectException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NoActiveTechnicalResourceProjectException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NoActiveTechnicalResourceProjectException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NoActiveTechnicalResourceProjectException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NoActiveTechnicalResourceProjectException(String message, Throwable cause) {
        super(message, cause);
    }
}
