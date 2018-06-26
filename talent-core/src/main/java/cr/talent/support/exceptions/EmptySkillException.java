package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.skill.service.SkillService}
 * tries to persist an empty skill.
 *
 * @author Josue Cubero
 */
public class EmptySkillException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public EmptySkillException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public EmptySkillException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public EmptySkillException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public EmptySkillException(String message, Throwable cause) {
        super(message, cause);
    }

}
