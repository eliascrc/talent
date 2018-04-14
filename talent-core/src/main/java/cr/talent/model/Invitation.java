package cr.talent.model;

import java.util.Set;

/**
 * Class that represents an invitation to join the Talent system.
 * It contains the link of the image and the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
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
        return false;
    }

    @Override
    protected int onHashCode(int result) {
        return 0;
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