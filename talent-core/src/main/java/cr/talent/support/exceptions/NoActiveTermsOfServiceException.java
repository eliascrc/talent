package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.termsOfService.service.ToSService} tries to
 * retrieve a Terms Of Service and there is no currently active content.
 */
public class NoActiveTermsOfServiceException extends RuntimeException {
}
