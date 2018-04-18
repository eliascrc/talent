package cr.talent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Set;

/**
 * Class that represents a Technical Resource within the Talent system. It contains the technical resource
 * organization, profile picture, skills, education records and other important information for Talent.
 * It also contains the information inherited from {@link cr.talent.model.User} class.
 *
 * @author María José Cubero
 */
@Entity
@Table(name = "technical_resource")
public class TechnicalResource extends User{

    /**
     * Defines if the user is an administrator of the organization.
     */
    @Column(name = "is_administrator", nullable = false)
    private boolean isAdministrator;

    /**
     * Specifies the date of the last level assessment result.
     */
    @Column(name = "last_level_assessment")
    private Date lastLevelAssessment;

    /**
     * Specifies the date of the last performance review result.
     */
    @Column(name = "last_performance_review")
    private Date lastPerformanceReview;

    /**
     * The profile picture of the user.
     */
    private Image profilePicture;

    /**
     * The organization that the resource belongs to.
     */
    private Organization organization;

    /**
     * The list of the resource's skills.
     */
    private Set<Skill> skills;

    /**
     * The list of the resource's education records.
     */
    private Set<EducationRecord> educationRecords;

    /**
     * The list of the resource's project positions.
     */
    private Set<ProjectPosition> projectPositions;

    /**
     * The resource's job position.
     */
    private JobPosition jobPosition;

    /**
     * The resource's technical position.
     */
    private TechnicalPosition technicalPosition;

    /**
     * The resource's career path.
     */
    private CareerPath careerPath;

    /**
     * The resource's technical manager.
     */
    private TechnicalManager technicalManager;

    /**
     * The resource's kudos and warnings.
     */
    private Set<Observation> observations;

    /**
     * The resouce's emergency contacts list.
     */
    private Set<EmergencyContact> emergencyContacts;

    /**
     * The resouce's language setting.
     */
    private Language language;

    /**
     * The resource's timezone setting.
     */
    @Column(name = "time_zone", nullable = false)
    private String timeZone;

    /**
     * The resource's level assessment time gap.
     */
    @Column(name = "level_assessment_time_gap", nullable = false)
    private int levelAssessmentTimeGap;

    public TechnicalResource(){}

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if (o instanceof TechnicalResource){
            TechnicalResource technicalResource = (TechnicalResource) o;
            result = (this.username == null ? technicalResource.getUsername() == null : this.username.equals(technicalResource.getUsername())
                    && this.password == null ? technicalResource.getPassword() == null : this.password.equals(technicalResource.getPassword())
                    && this.organization == null ? technicalResource.getOrganization() == null : this.organization.equals(technicalResource.getOrganization()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + ((this.username == null)? 0 : this.username.hashCode());
        result = prime * result + ((this.password == null)? 0 : this.password.hashCode());
        result = prime * result + ((this.organization == null)? 0 : this.organization.hashCode());
        return result;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

    public Date getLastLevelAssessment() {
        return lastLevelAssessment;
    }

    public void setLastLevelAssessment(Date lastLevelAssessment) {
        this.lastLevelAssessment = lastLevelAssessment;
    }

    public Date getLastPerformanceReview() {
        return lastPerformanceReview;
    }

    public void setLastPerformanceReview(Date lastPerformanceReview) {
        this.lastPerformanceReview = lastPerformanceReview;
    }

    public Image getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Image profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public Set<EducationRecord> getEducationRecords() {
        return educationRecords;
    }

    public void setEducationRecords(Set<EducationRecord> educationRecords) {
        this.educationRecords = educationRecords;
    }

    public Set<ProjectPosition> getProjectPositions() {
        return projectPositions;
    }

    public void setProjectPositions(Set<ProjectPosition> projectPositions) {
        this.projectPositions = projectPositions;
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

    public CareerPath getCareerPath() {
        return careerPath;
    }

    public void setCareerPath(CareerPath careerPath) {
        this.careerPath = careerPath;
    }

    public TechnicalManager getTechnicalManager() {
        return technicalManager;
    }

    public void setTechnicalManager(TechnicalManager technicalManager) {
        this.technicalManager = technicalManager;
    }

    public Set<Observation> getObservations() {
        return observations;
    }

    public void setObservations(Set<Observation> observations) {
        this.observations = observations;
    }

    public Set<EmergencyContact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(Set<EmergencyContact> emergencyContacts) {
        this.emergencyContacts = emergencyContacts;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public int getLevelAssessmentTimeGap() {
        return levelAssessmentTimeGap;
    }

    public void setLevelAssessmentTimeGap(int levelAssessmentTimeGap) {
        this.levelAssessmentTimeGap = levelAssessmentTimeGap;
    }
}
