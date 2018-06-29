package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.projectPosition.service.impl.ProjectPositionServiceImpl}
 * tries to create a project that already exists.
 *
 * @author Otto Mena Kikut
 */
public class ProjectPositionAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public ProjectPositionAlreadyExistsException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public ProjectPositionAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public ProjectPositionAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public ProjectPositionAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }


}
