package cr.talent.core.skill.dao.impl;

import cr.talent.core.skill.dao.SkillDao;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * Hibernate implementation of the {@link cr.talent.core.skill.dao.SkillDao}.
 *
 * @author Elías Calderón
 */
@Repository("skillDao")
public class HibernateSkillDao extends HibernateCrudDao<Skill, String> implements SkillDao {

    @Autowired
    public HibernateSkillDao(@Qualifier("sessionFactory")SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

}
