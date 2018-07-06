package cr.talent.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Class that represents a Technical Resource within the Talent system. It contains the technical resource
 * organization, profile picture, skills, education records and other important information for Talent.
 * It also contains the information inherited from {@link cr.talent.model.User} class.
 *
 * @author María José Cubero
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Table(name = "technical_resource",
        uniqueConstraints=@UniqueConstraint(columnNames={"username", "organization_id"}))
public class TechnicalResource extends User{

    /**
     * The profile picture of the user.
     */
    @ManyToOne
    @JoinColumn(name = "profile_picture_id")
    private ProfilePicture profilePicture;

    /**
     * The organization that the resource belongs to.
     */
    @ManyToOne
    @JoinColumn (name = "organization_id")
    protected Organization organization;

    /**
     * The list of the resource's skills.
     */
    @ManyToMany(mappedBy = "resources")
    private Set<Skill> skills;

    /**
     * The list of the resource's education records.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "resource")
    private Set<EducationRecord> educationRecords;

    /**
     * The project positions for the resource
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "resource")
    private Set<ProjectPositionHolder> projectPositions;

    /**
     * The resource's job position.
     */
    @OneToOne (mappedBy = "technicalResource")
    private JobPosition jobPosition;

    /**
     * The resource's technical position.
     */
    @OneToOne (mappedBy = "technicalResource")
    private TechnicalPosition technicalPosition;

    /**
     * The resource's career path.
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "career_path_id", unique = true)
    private CareerPath careerPath;

    /**
     * The resource's technical manager.
     */
    @ManyToOne
    @JoinColumn (name = "technical_manager_id")
    private TechnicalManager technicalManager;

    /**
     * The resource's kudos and warnings.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "observee")
    private Set<Feedback> feedbackMade;

    /**
     * The resource's timezone setting.
     */
    @Column(name = "time_zone")
    private String timeZone;

    /**
     * The resouce's language setting.
     */
    @ManyToOne
    @JoinColumn (name = "language_id")
    private Language language;

    /**
     * The resouce's emergency contacts list.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "technicalResource")
    private Set<EmergencyContact> emergencyContacts;

    /**
     * The resource's level assessment time gap.
     */
    @Column(name = "level_assessment_time_gap")
    private int levelAssessmentTimeGap;

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
     * Defines if the user is an administrator of the organization.
     */
    @Column(name = "is_administrator", nullable = false)
    private boolean isAdministrator;

    /**
     * List of feedback that that resource has given to others.
     */
    @OneToMany (cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "observer")
    private Set<Feedback> feedbackGiven;

    /**
     * Technical Resouce's two step verification
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "two_step_verification_id", unique = true)
    private TwoStepVerification twoStepVerification;

    /**
     * A list with the project management positions that the Lead has occupied in the organization.
     */
    @OneToMany (cascade = CascadeType.ALL, mappedBy = "lead")
    private Set<LeadPosition> leadPositions;

    /**
     * A small description of the resource
     */
    @Column(name = "description")
    private String description;

    /**
     * The previous jobs of the resource. It's used for constructing the CV.
     */
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "technicalResource")
    private Set<PreviousJob> previousJobs;

    public TechnicalResource(){}

    /**
     * Method that returns the User's authorities, in this case it assigns the TECHNICAL_RESOURCE
     * role along with the ones inherited from its super class .
     * @return the collection of authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> authorities = new HashSet<>(super.getAuthorities());
        if (this.enabled)
            authorities.add(new SimpleGrantedAuthority("ROLE_TECHNICAL_RESOURCE"));
        return authorities;
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

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Set<EducationRecord> getEducationRecords() {
        return educationRecords;
    }

    public void setEducationRecords(Set<EducationRecord> educationRecords) {
        this.educationRecords = educationRecords;
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

    public Set<Feedback> getFeedbackMade() {
        return feedbackMade;
    }

    public void setFeedbackMade(Set<Feedback> feedbackMade) {
        this.feedbackMade = feedbackMade;
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

    public Set<Feedback> getFeedbackGiven() {
        return feedbackGiven;
    }

    public void setFeedbackGiven(Set<Feedback> madeKudo) {
        this.feedbackGiven = madeKudo;
    }

    public Set<Skill> getSkills() {
        return skills;
    }

    public void setSkills(Set<Skill> skills) {
        this.skills = skills;
    }

    public TwoStepVerification getTwoStepVerification() {
        return twoStepVerification;
    }

    public void setTwoStepVerification(TwoStepVerification twoStepVerification) {
        this.twoStepVerification = twoStepVerification;
    }

    public Set<ProjectPositionHolder> getProjectPositions() {
        return projectPositions;
    }

    public void setProjectPositions(Set<ProjectPositionHolder> projectPositions) {
        this.projectPositions = projectPositions;
    }

    public Set<LeadPosition> getLeadPositions() {
        return leadPositions;
    }

    public void setLeadPositions(Set<LeadPosition> leadPositions) {
        this.leadPositions = leadPositions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PreviousJob> getPreviousJobs() {
        return previousJobs;
    }

    public void setPreviousJobs(Set<PreviousJob> previousJobs) {
        this.previousJobs = previousJobs;
    }
}
