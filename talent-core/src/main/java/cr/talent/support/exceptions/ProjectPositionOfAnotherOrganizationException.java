package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService}
 * notices that a technical resource tried to assign a project position of an organization that they do not belong
 *
 * @author Daniel Montes de Oca
 */
public class ProjectPositionOfAnotherOrganizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public ProjectPositionOfAnotherOrganizationException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public ProjectPositionOfAnotherOrganizationException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public ProjectPositionOfAnotherOrganizationException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public ProjectPositionOfAnotherOrganizationException(String message, Throwable cause) {
        super(message, cause);
    }

}
