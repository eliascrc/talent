package cr.talent.core.privacyPolicy.dao.impl;

import cr.talent.core.privacyPolicy.dao.PrivacyPolicyDao;
import cr.talent.model.Platform;
import cr.talent.model.PrivacyPolicy;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Hibernate implementation of the {@link cr.talent.core.privacyPolicy.dao.PrivacyPolicyDao}.
 *
 * @author Daniel Montes de Oca
 */
@Repository("privacyPolicyDao")
public class HibernatePrivacyPolicyDao extends HibernateCrudDao<PrivacyPolicy, String> implements PrivacyPolicyDao {

    @Autowired
    public HibernatePrivacyPolicyDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public PrivacyPolicy getActivePrivacyPolicy(Platform requestPlatform) {
        String platformName = requestPlatform.name();
        Query query = super.getSessionFactory().getCurrentSession()
                .createQuery("FROM PrivacyPolicy WHERE isActive = true and platform = '" + platformName + "'");
        List<PrivacyPolicy> privacyPolicyResult = (List<PrivacyPolicy>) query.list();

        return DataAccessUtils.singleResult(privacyPolicyResult);
    }

}
