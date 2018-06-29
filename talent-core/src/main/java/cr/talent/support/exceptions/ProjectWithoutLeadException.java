package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.projectPositionHolder.service.impl.ProjectPositionHolderServiceImpl}
 * finds a project without an active lead
 *
 * @author Daniel Montes de Oca
 */
public class ProjectWithoutLeadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public ProjectWithoutLeadException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public ProjectWithoutLeadException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public ProjectWithoutLeadException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public ProjectWithoutLeadException(String message, Throwable cause) {
        super(message, cause);
    }


}
