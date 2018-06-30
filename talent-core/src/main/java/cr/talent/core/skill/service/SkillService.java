package cr.talent.core.skill.service;

import cr.talent.model.Skill;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.Skill} entities.
 *
 * @author Elías Calderón
 */
public interface SkillService extends CrudService<Skill, String> {

    /**
     * Persists Predefined Skill with the respective business logic.
     * @param predefinedSkill the skill to persist.
     * @return the predefined skill's id.
     */
    String createPredefinedSkill(Skill predefinedSkill);
}
