package cr.talent.model;

import java.util.Date;
import java.util.List;

/**
 * Class that represents a Technical Resource within the Talent system. It contains the technical resource
 * organization, profile picture, skills, education records and other important information for Talent.
 * It also contains the information inherited from {@link cr.talent.model.User} class.
 *
 * @author María José Cubero
 */
public class TechnicalResource extends User{

    /**
     * Defines if the user is an administrator of the organization.
     */
    private boolean isAdministrator;

    /**
     * Specifies the date of the last level assessment result.
     */
    private Date lastLevelAssessment;

    /**
     * Specifies the date of the last performance review result.
     */
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
    private List<Skill> skills;

    /**
     * The list of the resource's education records.
     */
    private List<EducationRecord> educationRecords;

    /**
     * The list of the resource's project positions.
     */
    private List<ProjectPosition> projectPositions;

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
    private List<Observation> observations;

    /**
     * The resource's curriculum vitae.
     */
    private CurriculumVitae curriculumVitae;

    /**
     * The resource's performance reviews list.
     */
    private List<PerformanceReview> performanceReviews;

    /**
     * The resouce's emergency contacts list.
     */
    private List<EmergencyContact> emergencyContacts;

    /**
     * The resouce's language setting.
     */
    private Language language;

    /**
     * The resource's timezone setting.
     */
    private String timeZone;

    /**
     * The resource's level assessment time gap.
     */
    private int levelAssessmentTimeGap;


    public TechnicalResource(){}

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

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public List<EducationRecord> getEducationRecords() {
        return educationRecords;
    }

    public void setEducationRecords(List<EducationRecord> educationRecords) {
        this.educationRecords = educationRecords;
    }

    public List<ProjectPosition> getProjectPositions() {
        return projectPositions;
    }

    public void setProjectPositions(List<ProjectPosition> projectPositions) {
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

    public List<Observation> getObservations() {
        return observations;
    }

    public void setObservations(List<Observation> observations) {
        this.observations = observations;
    }

    public CurriculumVitae getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(CurriculumVitae curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public List<PerformanceReview> getPerformanceReviews() {
        return performanceReviews;
    }

    public void setPerformanceReviews(List<PerformanceReview> performanceReviews) {
        this.performanceReviews = performanceReviews;
    }

    public List<EmergencyContact> getEmergencyContacts() {
        return emergencyContacts;
    }

    public void setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
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
}
