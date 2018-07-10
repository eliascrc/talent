package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.skillCategory.service.SkillCategoryService}
 * tries to persist a Skill Category with a name that is already registered within the system.
 *
 * @author Josue Cubero
 */
public class AlreadyCreatedSkillCategoryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public AlreadyCreatedSkillCategoryException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public AlreadyCreatedSkillCategoryException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public AlreadyCreatedSkillCategoryException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public AlreadyCreatedSkillCategoryException(String message, Throwable cause) {
        super(message, cause);
    }

}
