package cr.talent.core.skill.dao.impl;

import cr.talent.core.skill.dao.SkillDao;
import cr.talent.model.PredefinedSkill;
import cr.talent.model.Skill;
import cr.talent.support.dao.impl.HibernateCrudDao;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    public PredefinedSkill getPredefinedSkillByName(String predefinedSkillName) {

        String sql = "SELECT * FROM skill WHERE DTYPE = 'PREDEFINED_SKILL' AND name = :predefinedSkillName";
        Query query = super.getSessionFactory().getCurrentSession().createNativeQuery(sql).addEntity(PredefinedSkill.class);
        query.setParameter("predefinedSkillName", predefinedSkillName);
        List<PredefinedSkill> predefinedSkillResult = (List<PredefinedSkill>)query.list();

        if (predefinedSkillResult.size() > 0)
            return DataAccessUtils.singleResult(predefinedSkillResult);

        return null;
    }
}
