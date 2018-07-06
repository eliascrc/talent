package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.skillCategory.service.SkillCategoryService}
 * notices that a user tries to delete a Skill Category of another organization.
 *
 * @author Otto Mena Kikut
 */
public class SkillCategoryOfAnotherOrganizationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public SkillCategoryOfAnotherOrganizationException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public SkillCategoryOfAnotherOrganizationException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public SkillCategoryOfAnotherOrganizationException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public SkillCategoryOfAnotherOrganizationException(String message, Throwable cause) {
        super(message, cause);
    }

}