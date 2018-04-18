package cr.talent.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class that represents an organization within the Talent system. It contains the organization's
 * unique identifier, name, logo and other important information for Talent. It also contains the information inherited from
 * {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
@Entity
@Table(name = "organization")
public class Organization extends BasicEntity {

    /**
     * Unique Identifier for every organization in the system.
     */
    @Column(name = "unique_identifier", nullable = false)
    private String uniqueIdentifier;

    /**
     * The name of the organization.
     */
    @Column (name = "name", nullable = false)
    private String name;

    /**
     * Flag that indicates if the organization wants login with two step verification.
     */
    @Column (name = "two_step_verification")
    private Boolean twoStepVerification;

    /**
     * The number of total technical resources involved in the organization.
     */
    @Column (name = "total_users")
    private int totalUsers;

    /**
     * The state of the organization. It might be disabled, enabled or in some stage of the creation wizard.
     */
    @Column (name = "state")
    @Enumerated(value = EnumType.STRING)
    private OrganizationState state;

    /**
     * The user authentication method that the organization's administrators select.
     */
    @Column (name = "user_authentication_method")
    @Enumerated(value = EnumType.STRING)
    private UserAuthenticationMethod userAuthenticationMethod;

    /**
     * The organizatio's domain
     */
    @Column (name = "domain")
    private String domain;

    /**
     * The invitatio's list for user to join an organization
     */
    private Set invitationsList;

    /**
     * An image with the logo of the organization.
     */
    private Image logo;

    /**
     * A list with the resources that have joined the organization.
     */
    private Set<TechnicalResource> resources;

    /**
     * A list with the organization's capabilities that the administrators have selected.
     */
    private Set<OrganizationCapability> capabilities;

    /**
     * A list with the categories of skills that the organization has registered.
     */
    private Set<OrganizationSkillCategory> skillCategories;

    /**
     * A list with the projects that have been created in the organization.
     */
  	private Set<Project> projects;

    /**
     * A list with the Human Resource Managers of the organization.
     */
    private Set<HumanResourceManager> humanResourceManagers;

    /**
     * A list with the Technical Managers of the organization.
     */
    private Set<TechnicalManager> technicalManagers;

    public Organization(){}

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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Set getInvitationsList() {
        return invitationsList;
    }

    public void setInvitationsList(Set invitationsList) {
        this.invitationsList = invitationsList;
    }

    public Set<TechnicalManager> getTechnicalManagers() {
        return technicalManagers;
    }

    public void setTechnicalManagers(Set<TechnicalManager> technicalManagers) {
        this.technicalManagers = technicalManagers;
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

    public Set<TechnicalResource> getResources() {
        return resources;
    }

    public void setResources(Set<TechnicalResource> resources) {
        this.resources = resources;
    }

    public Set<OrganizationCapability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(Set<OrganizationCapability> capabilities) {
        this.capabilities = capabilities;
    }

    public Set<OrganizationSkillCategory> getSkillCategories() {
        return skillCategories;
    }

    public void setSkillCategories(Set<OrganizationSkillCategory> skillCategories) {
        this.skillCategories = skillCategories;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<HumanResourceManager> getHumanResourceManagers() {
        return humanResourceManagers;
    }

    public void setHumanResourceManagers(Set<HumanResourceManager> humanResourceManagers) {
        this.humanResourceManagers = humanResourceManagers;
    }
}
