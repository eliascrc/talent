package cr.talent.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * Class that represents an invitation to join the Talent system.
 * It contains the link of the image and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "invitation")
public class Invitation extends BasicEntity {

    /**
     * The name of the person receiving the invitation.
     */
    private String name;

    /**
     * The email of the person that is going to receive the invitation.
     */
    private String email;

    /**
     * The job position of the person that is going to join the organization.
     */
    private JobPosition jobPosition;

    /**
     * The technical position of the person that is going to join the organization.
     */
    private TechnicalPosition technicalPosition;

    /**
     * The list of skills of the person that is going to join the organization.
     */
    private Set<OrganizationSkill> skills;

    public Invitation (){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Invitation){
            Invitation invitation = (Invitation) o;
            result = (this.name == null ? invitation.getName() == null : this.name.equals(invitation.getName()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.name == null ? 0 : this.name.hashCode());
        return result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public TechnicalPosition getTechnicalPosition() {
        return technicalPosition;
    }

    public void setTechnicalPosition(TechnicalPosition technicalPosition) {
        this.technicalPosition = technicalPosition;
    }

    public Set<OrganizationSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<OrganizationSkill> skills) {
        this.skills = skills;
    }
}