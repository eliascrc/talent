package cr.talent.core.privacyPolicy.dao;

import cr.talent.model.PrivacyPolicy;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link cr.talent.model.PrivacyPolicy} related operations.
 *
 * @author Daniel Montes de Oca
 */
public interface PrivacyPolicyDao extends CrudDao<PrivacyPolicy, String> {

    PrivacyPolicy getActivePrivacyPolicy();
    String getActivePrivacyPolicyContent();

}
