package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.termsOfService.service.ToSService} tries to
 * retrieve a Terms Of Service and there is no currently active content.
 */
public class NoActiveTermsOfServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NoActiveTermsOfServiceException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NoActiveTermsOfServiceException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NoActiveTermsOfServiceException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NoActiveTermsOfServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
