package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.privacyPolicy.service.PrivacyPolicyService} tries to
 * retrieve a Privacy Policy and there is no currently active content.
 */
public class NoActivePrivacyPolicyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NoActivePrivacyPolicyException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NoActivePrivacyPolicyException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NoActivePrivacyPolicyException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NoActivePrivacyPolicyException(String message, Throwable cause) {
        super(message, cause);
    }
}
