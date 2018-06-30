package cr.talent.core.skillCategory.dao.impl;

import cr.talent.core.skillCategory.dao.SkillCategoryDao;
import cr.talent.model.SkillCategory;
import cr.talent.model.SkillCategory;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;


/**
 * Hibernate implementation of the {@link cr.talent.core.skillCategory.dao.SkillCategoryDao}.
 *
 * @author Otto Mena
 */
@Repository("skillCategoryDao")
public class HibernateSkillCategoryDao extends HibernateCrudDao<SkillCategory, String> implements SkillCategoryDao {

    @Autowired
    public HibernateSkillCategoryDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }
}
