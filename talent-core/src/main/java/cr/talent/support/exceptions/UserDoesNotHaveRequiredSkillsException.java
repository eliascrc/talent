package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when a technical resource does not have the required skills for the capability
 * level in the technical position they are trying to be assigned.
 *
 * @author Fabián Roberto Leandro
 */
public class UserDoesNotHaveRequiredSkillsException extends RuntimeException {
    
        private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public UserDoesNotHaveRequiredSkillsException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public UserDoesNotHaveRequiredSkillsException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public UserDoesNotHaveRequiredSkillsException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public UserDoesNotHaveRequiredSkillsException(String message, Throwable cause) {
        super(message, cause);
    }
}
