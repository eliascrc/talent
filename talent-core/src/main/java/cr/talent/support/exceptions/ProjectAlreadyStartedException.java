package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService}
 * notices that someone is trying to unassign a project position like if it hadn't started even though it already had
 *
 * @author Daniel Montes de Oca
 */
public class ProjectAlreadyStartedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public ProjectAlreadyStartedException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public ProjectAlreadyStartedException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public ProjectAlreadyStartedException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public ProjectAlreadyStartedException(String message, Throwable cause) {
        super(message, cause);
    }

}
