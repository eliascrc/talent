package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.organization.service.impl.OrganizationServiceImpl}
 * tries to find a technical resource with a null organization to assign him the new organization that he is creating.
 *
 * @author María José Cubero
 */
public class NonExistentUserWithNullOrganization extends RuntimeException{

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NonExistentUserWithNullOrganization() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NonExistentUserWithNullOrganization(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NonExistentUserWithNullOrganization(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NonExistentUserWithNullOrganization(String message, Throwable cause) {
        super(message, cause);
    }

}
