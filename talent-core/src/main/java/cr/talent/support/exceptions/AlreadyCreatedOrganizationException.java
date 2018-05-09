package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.organization.service.OrganizationService}
 * tries to persist an Organization with a unique identifier that is already registered within the system.
 *
 * @author Elías Calderón
 */
public class AlreadyCreatedOrganizationException extends RuntimeException {
}