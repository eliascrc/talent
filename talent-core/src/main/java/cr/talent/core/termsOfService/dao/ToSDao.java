package cr.talent.core.termsOfService.dao;

import cr.talent.model.TermsOfService;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link TermsOfService} related operations.
 *
 * @author Josué León Sarkis
 */
public interface ToSDao extends CrudDao<TermsOfService, String> {

    /**
     * Obtains the currently active TermsOfService by performing an HQL query and specifying the active flag
     * in it.
     *
     * @return The active TermsOfService.
     */
    TermsOfService getActiveTermsOfService();
}
