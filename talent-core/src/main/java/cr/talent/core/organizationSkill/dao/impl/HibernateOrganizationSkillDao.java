package cr.talent.core.organizationSkill.dao.impl;

import cr.talent.core.organizationSkill.dao.OrganizationSkillDao;
import cr.talent.model.OrganizationSkill;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.organizationSkill.dao.OrganizationSkillDao}.
 *
 * @author Elías Calderón
 */
@Repository("organizationSkillDao")
public class HibernateOrganizationSkillDao extends HibernateCrudDao<OrganizationSkill, String> implements OrganizationSkillDao {

    @Autowired
    public HibernateOrganizationSkillDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
