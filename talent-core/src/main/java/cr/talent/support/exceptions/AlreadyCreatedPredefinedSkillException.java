package cr.talent.support.exceptions;

/**
 * A runtime exception that is thrown when the {@link cr.talent.core.skill.service.SkillService}
 * tries to persist a Predefined Skill with a name that is already registered within the system.
 *
 * @author Elías Calderón
 */
public class AlreadyCreatedPredefinedSkillException extends RuntimeException {
}
