package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService}
 * notices that someone that is not the lead of a project tries to assign project positions in that project
 *
 * @author Daniel Montes de Oca
 */
public class NotProjectLeadException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NotProjectLeadException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NotProjectLeadException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NotProjectLeadException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NotProjectLeadException(String message, Throwable cause) {
        super(message, cause);
    }

}
