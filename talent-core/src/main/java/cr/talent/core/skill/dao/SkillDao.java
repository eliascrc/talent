package cr.talent.core.skill.dao;

import cr.talent.model.PredefinedSkill;
import cr.talent.model.Skill;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link cr.talent.model.Skill} related operations.
 *
 * @author Elías Calderón
 */
public interface SkillDao extends CrudDao<Skill, String> {

    PredefinedSkill getPredefinedSkillByName(String predefinedSkillName);

}
