package cr.talent.core.skill.service;

import cr.talent.model.Organization;
import cr.talent.model.Skill;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

import java.util.List;

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

    /**
     * Persists a list of {@link cr.talent.model.Skill}.
     * @param skills the skills that will try to be assigned to the technical resource.
     * @param organization the technical resource organization.
     * @param technicalResource the technical resource.
     */
    void assignSkillToTechnicalResource(List<String> skills, Organization organization, TechnicalResource technicalResource);
}
