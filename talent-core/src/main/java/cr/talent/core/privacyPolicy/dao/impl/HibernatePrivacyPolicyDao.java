package cr.talent.core.privacyPolicy.dao.impl;

import cr.talent.core.privacyPolicy.dao.PrivacyPolicyDao;
import cr.talent.model.PrivacyPolicy;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


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
    /**
     * Retrieves the active privacy policy for the Talent! system
     * @return the active privacy policy
     */
    public PrivacyPolicy getActivePrivacyPolicy() {
        Query query = super.getSessionFactory().getCurrentSession()
                .createQuery("FROM PrivacyPolicy WHERE Active = true");
        return (PrivacyPolicy) query.getSingleResult();
    }

    @Override
    /**
     * Retrieves the content of the active privacy policy for the Talent! system
     * @return the content in html format of the active privacy policy
     */
    public String getActivePrivacyPolicyContent() {
        Query query = super.getSessionFactory().getCurrentSession()
                .createQuery("SELECT content FROM PrivacyPolicy WHERE Active = true");
        return (String) query.getSingleResult();
    }
}
