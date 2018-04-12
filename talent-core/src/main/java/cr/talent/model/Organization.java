package cr.talent.model;

import java.util.ArrayList;

/**
 * Class that represents an organization within the Talent system. It contains the organization's
 * unique identifier, name, logo and other important information for Talent. It also contains the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
public class Organization extends BasicEntity {

    /**
     * Unique Identifier for every organization in the system.
     */
    private String uniqueIdentifier;

    /**
     * The name of the organization.
     */
    private String name;

    /**
     * Flag that indicates if the organization wants login with two step verification.
     */
    private Boolean twoStepVerification;

    /**
     * The number of total technical resources involved in the organization.
     */
    private int totalUsers;

    /**
     * The state of the organization. It might be disabled, enabled or in some stage of the creation wizard.
     */
    private OrganizationState state;

    /**
     * The user authentication method that the organization's administrators select.
     */
    private UserAuthenticationMethod userAuthenticationMethod;

    /**
     * An image with the logo of the organization.
     */
    private Image logo;

    /**
     * A list with the resources that have joined the organization.
     */
    private ArrayList<TechnicalResource> resources;

    /**
     * A list with the organization's capabilities that the administrators have selected.
     */
    private ArrayList<OrganizationCapability> capabilities;

    /**
     * A list with the performance processes that the organization has created through time.
     */
    private ArrayList<PerformanceProcess> performanceProcesses;

    /**
     * The time gap in months between performance reviews for the organization.
     */
    private int performanceReviewTimeGap;

    /**
     * A list with the payment methods that the organization registers in the system.
     */
    private ArrayList<PaymentMethod> paymentMethods;

    /**
     * The current payment plan that the organization has.
     */
    private PaymentPlan currentPaymentPlan;

    /**
     * A list with the payment plans that the organization has had through time.
     */
    private ArrayList<PaymentPlan> paymentPlans;

    /**
     * A list with the level assessment test types that the organization has registered.
     */
    private ArrayList<LATestType> LATestTypes;

    /**
     * The time gap in months between level assessments for the organization.
     */
    private int levelAssessmentTimeGap;

    /**
     * A list with the categories of skills that the organization has registered.
     */
    private ArrayList<OrganizationCategorySkill> skillCategories;

    /**
     * A list with the level assessments that have been performed in the organization.
     */
    private ArrayList<LevelAssessment> levelAssessments;

    /**
     * A list with the projects that have been created in the organization.
     */
  	private ArrayList<Project> projects;

    /**
     * A list with the Human Resource Managers of the organization.
     */
    private ArrayList<HumanResourceManager> humanResourceManagers;

    /**
     * A list with the Technical Managers of the organization.
     */
    private ArrayList<TechnicalManager> technicalManagers;

    public Organization(){}

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getTwoStepVerification() {
        return twoStepVerification;
    }

    public void setTwoStepVerification(Boolean twoStepVerification) {
        this.twoStepVerification = twoStepVerification;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public void setTotalUsers(int totalUsers) {
        this.totalUsers = totalUsers;
    }

    public OrganizationState getState() {
        return state;
    }

    public void setState(OrganizationState state) {
        this.state = state;
    }

    public UserAuthenticationMethod getUserAuthenticationMethod() {
        return userAuthenticationMethod;
    }

    public void setUserAuthenticationMethod(UserAuthenticationMethod userAuthenticationMethod) {
        this.userAuthenticationMethod = userAuthenticationMethod;
    }

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public ArrayList<TechnicalResource> getResources() {
        return resources;
    }

    public void setResources(ArrayList<TechnicalResource> resources) {
        this.resources = resources;
    }

    public ArrayList<OrganizationCapability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(ArrayList<OrganizationCapability> capabilities) {
        this.capabilities = capabilities;
    }

    public ArrayList<PerformanceProcess> getPerformanceProcesses() {
        return performanceProcesses;
    }

    public void setPerformanceProcesses(ArrayList<PerformanceProcess> performanceProcesses) {
        this.performanceProcesses = performanceProcesses;
    }

    public int getPerformanceReviewTimeGap() {
        return performanceReviewTimeGap;
    }

    public void setPerformanceReviewTimeGap(int performanceReviewTimeGap) {
        this.performanceReviewTimeGap = performanceReviewTimeGap;
    }

    public ArrayList<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(ArrayList<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }

    public PaymentPlan getCurrentPaymentPlan() {
        return currentPaymentPlan;
    }

    public void setCurrentPaymentPlan(PaymentPlan currentPaymentPlan) {
        this.currentPaymentPlan = currentPaymentPlan;
    }

    public ArrayList<LATestType> getLATestTypes() {
        return LATestTypes;
    }

    public void setLATestTypes(ArrayList<LATestType> LATestTypes) {
        this.LATestTypes = LATestTypes;
    }

    public int getLevelAssessmentTimeGap() {
        return levelAssessmentTimeGap;
    }

    public void setLevelAssessmentTimeGap(int levelAssessmentTimeGap) {
        this.levelAssessmentTimeGap = levelAssessmentTimeGap;
    }

    public ArrayList<OrganizationCategorySkill> getSkillCategories() {
        return skillCategories;
    }

    public void setSkillCategories(ArrayList<OrganizationCategorySkill> skillCategories) {
        this.skillCategories = skillCategories;
    }

    public ArrayList<LevelAssessment> getLevelAssessments() {
        return levelAssessments;
    }

    public void setLevelAssessments(ArrayList<LevelAssessment> levelAssessments) {
        this.levelAssessments = levelAssessments;
    }

    public ArrayList<Project> getProjects() {
        return projects;
    }

    public void setProjects(ArrayList<Project> projects) {
        this.projects = projects;
    }

    public ArrayList<HumanResourceManager> getHumanResourceManagers() {
        return humanResourceManagers;
    }

    public void setHumanResourceManagers(ArrayList<HumanResourceManager> humanResourceManagers) {
        this.humanResourceManagers = humanResourceManagers;
    }

    @Override
    protected boolean onEquals(Object o) {
        boolean result = false;
        if ( o instanceof Organization){
            Organization organization = (Organization) o;
            result = (this.uniqueIdentifier == null ? organization.getUniqueIdentifier() == null :
                    this.uniqueIdentifier.equals(organization.getUniqueIdentifier()));
        }
        return result;
    }

    @Override
    protected int onHashCode(int result) {
        final int prime = 23;
        result = prime * result + (this.uniqueIdentifier == null ? 0 : this.uniqueIdentifier.hashCode());
        return result;
    }

    public ArrayList<PaymentPlan> getPaymentPlans() {
        return paymentPlans;
    }

    public void setPaymentPlans(ArrayList<PaymentPlan> paymentPlans) {
        this.paymentPlans = paymentPlans;
    }
}
