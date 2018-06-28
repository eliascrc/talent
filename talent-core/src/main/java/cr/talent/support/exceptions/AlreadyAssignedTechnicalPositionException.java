package cr.talent.support.exceptions;

/**
 * Default implementation of the {@link cr.talent.core.organizationSkill.service.OrganizationSkillService}.
 *
 * @author Fabián Roberto Leandro
 */
public class AlreadyAssignedTechnicalPositionException extends RuntimeException {
    
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public AlreadyAssignedTechnicalPositionException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public AlreadyAssignedTechnicalPositionException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public AlreadyAssignedTechnicalPositionException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public AlreadyAssignedTechnicalPositionException(String message, Throwable cause) {
        super(message, cause);
    }
}
