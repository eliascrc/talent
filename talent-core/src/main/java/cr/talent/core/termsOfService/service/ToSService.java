package cr.talent.core.termsOfService.service;

import cr.talent.model.TermsOfService;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.TermsOfService} entities.
 *
 * @author Josue Leon Sarkis
 */
public interface ToSService extends CrudService<TermsOfService, String> {

    TermsOfService getActiveTermsOfService();

    String getActiveTermsOfServiceContent();
}
