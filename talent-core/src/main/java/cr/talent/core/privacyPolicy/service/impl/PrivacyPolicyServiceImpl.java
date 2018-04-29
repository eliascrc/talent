package cr.talent.core.privacyPolicy.service.impl;

import cr.talent.core.privacyPolicy.dao.PrivacyPolicyDao;
import cr.talent.core.privacyPolicy.service.PrivacyPolicyService;
import cr.talent.model.PrivacyPolicy;
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
    public PrivacyPolicy getActivePrivacyPolicy() {
        return privacyPolicyDao.getActivePrivacyPolicy();
    }

    @Override
    public String getActivePrivacyPolicyContent() {
        return privacyPolicyDao.getActivePrivacyPolicyContent();
    }
}
