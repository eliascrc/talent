package cr.talent.core.skill.dao;

import cr.talent.model.Skill;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link cr.talent.model.Skill} related operations.
 *
 * @author Elías Calderón
 */
public interface SkillDao extends CrudDao<Skill, String> {

    /**
     * Gets a predefined skill with a given name.
     * @param predefinedSkillName the name of the skill.
     * @return the predefined skill if found, null if not found.
     */
    Skill getPredefinedSkillByName(String predefinedSkillName);

}
