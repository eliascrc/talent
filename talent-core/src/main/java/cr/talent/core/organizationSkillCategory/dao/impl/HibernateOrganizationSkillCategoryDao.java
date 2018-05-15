package cr.talent.core.organizationSkillCategory.dao.impl;

import cr.talent.core.organizationSkillCategory.dao.OrganizationSkillCategoryDao;
import cr.talent.model.OrganizationSkillCategory;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.organizationSkillCategory.dao.OrganizationSkillCategoryDao}.
 *
 * @author Elías Calderón
 */
@Repository("organizationSkillCategoryDao")
public class HibernateOrganizationSkillCategoryDao extends HibernateCrudDao<OrganizationSkillCategory, String> implements OrganizationSkillCategoryDao {

    @Autowired
    public HibernateOrganizationSkillCategoryDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
