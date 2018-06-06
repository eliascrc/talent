package cr.talent.core.termsOfService.service;

import cr.talent.model.Platform;
import cr.talent.model.TermsOfService;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.TermsOfService} entities.
 *
 * @author Josue Leon Sarkis
 */
public interface ToSService extends CrudService<TermsOfService, String> {

    /**
     * Gets the currently active version of the system's Terms of Service via the data access object of the
     * service.
     * @return The active TermsOfService
     */
    TermsOfService getActiveTermsOfService(Platform platform);

}
