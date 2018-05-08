package cr.talent.core.skill.service;

import cr.talent.model.PredefinedSkill;
import cr.talent.model.Skill;
import cr.talent.support.exceptions.AlreadyCreatedPredefinedSkillException;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.Skill} entities.
 *
 * @author Elías Calderón
 */
public interface SkillService extends CrudService<Skill, String> {


    String createPredefinedSkill(PredefinedSkill predefinedSkill) throws AlreadyCreatedPredefinedSkillException;
}
