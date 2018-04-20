package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * Class that represents a skill that a technical resource requests a Technical Manager to approve.
 *
 * @author Elías Calderón
 */
@Entity
public class SkillToApprove extends BasicEntity{

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

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof SkillToApprove){
            SkillToApprove skillToApprove = (SkillToApprove) o;
            result = (this.requestedSkill == null ? skillToApprove.getRequestedSkill() == null : this.requestedSkill.equals(skillToApprove.getRequestedSkill())
                    && this.requestingTechnicalResource == null ? skillToApprove.getRequestingTechnicalResource() == null :
                    this.requestingTechnicalResource.equals(skillToApprove.getRequestingTechnicalResource()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.requestedSkill == null ? 0 : this.requestedSkill.hashCode());
        result = prime * result + (this.requestingTechnicalResource == null ? 0 : this.requestingTechnicalResource.hashCode());
        return result;
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
