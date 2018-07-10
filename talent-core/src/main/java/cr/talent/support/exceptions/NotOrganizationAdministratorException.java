package cr.talent.support.exceptions;

import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;

import java.io.InputStream;

/**
 * A runtime exception thrown when {@link cr.talent.core.organization.service.impl.OrganizationServiceImpl#editBasicInformation(Organization, TechnicalResource, String, String, InputStream)}
 * receives a technical resource that is not an administrator of their organization.
 * 
 * @author Fabián Roberto Leandrp
 */
public class NotOrganizationAdministratorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public NotOrganizationAdministratorException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public NotOrganizationAdministratorException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public NotOrganizationAdministratorException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public NotOrganizationAdministratorException(String message, Throwable cause) {
        super(message, cause);
    }
}
