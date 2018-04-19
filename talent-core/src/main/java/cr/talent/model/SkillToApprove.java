package cr.talent.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Class that represents a skill that a technical resource requests a Technical Manager to approve.
 *
 * @author Elías Calderón
 */
@Embeddable
public class SkillToApprove {

    /**
     * The requesting technical resource.
     */
    @ManyToOne
    @JoinColumn(name = "technical_resource_id", nullable = false)
    private TechnicalResource requestingTechnicalResource;

    /**
     * The requested skill.
     */
    @ManyToOne
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill requestedSkill;

    public SkillToApprove() {
    }

    public TechnicalResource getRequestingTechnicalResource() {
        return requestingTechnicalResource;
    }

    public void setRequestingTechnicalResource(TechnicalResource requestingTechnicalResource) {
        this.requestingTechnicalResource = requestingTechnicalResource;
    }

    public Skill getRequestedSkill() {
        return requestedSkill;
    }

    public void setRequestedSkill(Skill requestedSkill) {
        this.requestedSkill = requestedSkill;
    }
}
