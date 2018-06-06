package cr.talent.core.privacyPolicy.service.impl;

import cr.talent.core.privacyPolicy.dao.PrivacyPolicyDao;
import cr.talent.core.privacyPolicy.service.PrivacyPolicyService;
import cr.talent.model.Platform;
import cr.talent.model.PrivacyPolicy;
import cr.talent.support.exceptions.NoActivePrivacyPolicyException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.privacyPolicy.service.PrivacyPolicyService}
 *
 * @author Daniel Montes de Oca
 */
@Service("privacyPolicyService")
@Transactional
public class PrivacyPolicyServiceImpl extends CrudServiceImpl<PrivacyPolicy, String> implements PrivacyPolicyService {

    @Autowired
    private PrivacyPolicyDao privacyPolicyDao;

    public void init() {
        setCrudDao(this.privacyPolicyDao);
    }

    @Override
    public PrivacyPolicy getActivePrivacyPolicy(Platform platform) {
        final String noActivePrivacyPolicyMessage = "There is no currently active terms of service content for the" +
                " requested platform";
        PrivacyPolicy activePrivacyPolicy = privacyPolicyDao.getActivePrivacyPolicy(platform);
        if (activePrivacyPolicy == null)
            throw new NoActivePrivacyPolicyException(noActivePrivacyPolicyMessage);

        return activePrivacyPolicy;
    }

}
