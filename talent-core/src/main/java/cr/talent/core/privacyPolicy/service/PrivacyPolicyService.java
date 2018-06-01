package cr.talent.core.privacyPolicy.service;

import cr.talent.model.Platform;
import cr.talent.model.PrivacyPolicy;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.PrivacyPolicy} entities.
 *
 * @author Daniel Montes de Oca
 */
public interface PrivacyPolicyService extends CrudService<PrivacyPolicy, String> {

    /**
     * Retrieves the active privacy policy for the Talent! system
     * @return the active privacy policy
     */
    PrivacyPolicy getActivePrivacyPolicy(Platform platform);

}
