package cr.talent.build;

import cr.talent.model.*;
import nu.xom.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;

/**
 * This class extends the XmlParser class, it parses the xml file while creating the objects that corr and setting their attributes.
 *
 * @author Daniel Montes de Oca & Otto Mena Kikut
 */
class DataParser extends XmlParser {

    private List<Organization> organizations;
    private List<TechnicalResource> technicalResources;
    private List<PrivacyPolicy> privacyPolicyVersions;
    private List<TermsOfService> termsOfServiceVersions;
    private List<Language> languages;
    private List<EducationRecord> educationRecords;
    private List<Project> projects;
    private List<OrganizationSkill> organizationSkills;
    private List<OrganizationSkillCategory> organizationSkillCategories;
    private List<Capability> capabilities;
    private List<CapabilityLevel> capabilityLevels;
    private List<ProjectPosition> projectPositions;
    private List<TechnicalPosition> technicalPositions;
    //private List<Feedback> feedbacks;

    /**
     * The class constructor. It receives the filepath to the xml file.
     * It creates the ArrayLists that will store each object corresponding to a row of the database.
     *
     *
     * @param filepath
     */
    DataParser(String filepath) {
        super(filepath);
        this.organizations = new ArrayList<Organization>();
        this.technicalResources = new ArrayList<TechnicalResource>();
        this.privacyPolicyVersions = new ArrayList<PrivacyPolicy>();
        this.termsOfServiceVersions = new ArrayList<>();
        this.languages = new ArrayList<Language>();
        this.educationRecords = new ArrayList<EducationRecord>();
        this.projects = new ArrayList<Project>();
        this.organizationSkills = new ArrayList<OrganizationSkill>();
        this.organizationSkillCategories = new ArrayList<OrganizationSkillCategory>();
        this.capabilities = new ArrayList<Capability>();
        this.capabilityLevels = new ArrayList<CapabilityLevel>();
        this.projectPositions = new ArrayList<ProjectPosition>();
        this.technicalPositions = new ArrayList<TechnicalPosition>();
    }

    void parseData() {
        this.fillOrganizations();
        this.fillLanguages();
        this.fillTechnicalResources();
        this.fillOrganizationSkillCategories();
        this.fillOrganizationSkills();
        this.fillCapabilities();
        this.fillCapabilityLevels();
        this.fillProjects();
        this.fillProjectPositions();
        this.fillPrivacyPolicyVersions();
        this.fillTermsOfServiceVersions();

    }


    /**
     * Fill the organizations List.
     */
    private void fillOrganizations() {
        Elements organizationElements = getElementOfType("organizations").get(0).getChildElements();
        for (int i = 0; i < organizationElements.size(); i++){
            Organization organization = getOrganization(organizationElements.get(i));
            this.organizations.add(organization);
        }
    }

    /**
     * This method creates an Organization object and sets its attributes according to the text in the xml
     *
     * @param organizationElement the node of the xml file corresponding to the specific organization.
     * @return
     */
    private Organization getOrganization(Element organizationElement) {
        Organization organization = new Organization();
        organization.setUniqueIdentifier(super.getAttributeValue(organizationElement, "uniqueIdentifier"));
        organization.setName(super.getAttributeValue(organizationElement, "name"));
        organization.setTwoStepVerification(super.getBooleanValue(organizationElement, "twoStepVerification"));
        organization.setState(OrganizationState.valueOf(super.getAttributeValue(organizationElement, "state")));
        organization.setUserAuthenticationMethod(UserAuthenticationMethod.valueOf(super.getAttributeValue(organizationElement, "userAuthenticationMethod")));
        organization.setDomain(super.getAttributeValue(organizationElement, "domain"));

        return organization;
    }

    /**
     * Fill the technicalResources List.
     */
    private void fillTechnicalResources () {
        Elements technicalResourceElements = getElementOfType("technicalResources").get(0).getChildElements();
        for (int i = 0; i < technicalResourceElements.size(); i++) {
            TechnicalResource technicalResource = getTechnicalResource(technicalResourceElements.get(i));
            this.technicalResources.add(technicalResource);
        }
    }

    /**This method creates a TechnicalResource and sets its attributes according to the text in the xml file.
     *
     * @param technicalResourceElement the node of the xml file corresponding to the specific technical resource.
     * @return
     */
    private TechnicalResource getTechnicalResource(Element technicalResourceElement) {
        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setUsername(super.getAttributeValue(technicalResourceElement, "username"));
        technicalResource.setFirstName(super.getAttributeValue(technicalResourceElement, "firstName"));
        technicalResource.setLastName(super.getAttributeValue(technicalResourceElement, "lastName"));
        technicalResource.setPassword(super.getAttributeValue(technicalResourceElement, "password"));
        technicalResource.setEnabled(super.getBooleanValue(technicalResourceElement, "enabled"));
        technicalResource.setPasswordNeedsChange(super.getBooleanValue(technicalResourceElement, "passwordNeedsChange"));
        technicalResource.setStatus(User.Status.valueOf(super.getAttributeValue(technicalResourceElement, "status")));
        technicalResource.setAdministrator(super.getBooleanValue(technicalResourceElement, "isAdministrator"));
        technicalResource.setLastLevelAssessment(super.getDateValue(technicalResourceElement, "lastLevelAssessment"));
        technicalResource.setLastPerformanceReview(super.getDateValue(technicalResourceElement, "lastPerformanceReview"));
        this.linkTechnicalResourceToOrganization(technicalResource, super.getAttributeValue(technicalResourceElement, "organization"));
        technicalResource.setTimeZone(super.getAttributeValue(technicalResourceElement, "timeZone"));
        technicalResource.setLevelAssessmentTimeGap(super.getIntValue(technicalResourceElement, "levelAssessmentTimeGap"));
        technicalResource.setNickname(super.getAttributeValue(technicalResourceElement,"nickname"));

        return technicalResource;
    }

    /**
     * Fill the organizationSkillCategories List.
     */
    private void fillOrganizationSkillCategories() {
        Elements organizationSkillCategoryElements = getElementOfType("organizationSkillCategories").get(0).getChildElements();
        for (int i = 0; i < organizationSkillCategoryElements.size(); i++){
            OrganizationSkillCategory organizationSkillCategory = getOrganizationSkillCategory(organizationSkillCategoryElements.get(i));
            this.organizationSkillCategories.add(organizationSkillCategory);
        }
    }

    /**
     * This method creates an OrganizationSkillCategory object and sets its attributes according to the text in the xml
     *
     * @param organizationSkillCategoryElement the node of the xml file corresponding to the specific organizationSkillCategory.
     * @return an organizationSkillCategory object with all of its attributes already set.
     */
    private OrganizationSkillCategory getOrganizationSkillCategory(Element organizationSkillCategoryElement){
        OrganizationSkillCategory organizationSkillCategory = new OrganizationSkillCategory();
        organizationSkillCategory.setName(super.getAttributeValue(organizationSkillCategoryElement, "name"));

        this.linkOrganizationSkillCategoryToOrganization(organizationSkillCategory, super.getAttributeValue(organizationSkillCategoryElement, "organization"));

        return organizationSkillCategory;
    }

    /**
     * Fill the organizationSkills List.
     */
    private void fillOrganizationSkills() {
        Elements organizationSkillElements = getElementOfType("organizationSkills").get(0).getChildElements();
        for (int i = 0; i < organizationSkillElements.size(); i++){
            OrganizationSkill organizationSkill = getOrganizationSkill(organizationSkillElements.get(i));
            this.organizationSkills.add(organizationSkill);
        }
    }

    /**
     * This method creates an OrganizationSkill object and sets its attributes according to the text in the xml
     *
     * @param organizationSkillElement the node of the xml file corresponding to the specific organizationSkill.
     * @return an organizationSkill object with all of its attributes already set.
     */
    private OrganizationSkill getOrganizationSkill(Element organizationSkillElement){
        OrganizationSkill organizationSkill = new OrganizationSkill();
        organizationSkill.setName(super.getAttributeValue(organizationSkillElement, "name"));

        organizationSkill.setResources(new HashSet<>());

        this.linkOrganizationSkillToTechnicalResources(organizationSkill, super.getAttributeValue(organizationSkillElement,"resources"));
        this.linkOrganizationSkillToOrganizationSkillCategory(organizationSkill, super.getAttributeValue(organizationSkillElement, "category"), super.getAttributeValue(organizationSkillElement, "organization"));

        return organizationSkill;
    }

    /**
     * Fill the capabilities List.
     */
    private void fillCapabilities() {
        Elements capabilityElements = getElementOfType("capabilities").get(0).getChildElements();
        for (int i = 0; i < capabilityElements.size(); i++){
            Capability capability = getCapability(capabilityElements.get(i));
            this.capabilities.add(capability);
        }
    }


    /**
     * This method creates a Capability object and sets its attributes according to the text in the xml
     *
     * @param capabilityElement the node of the xml file corresponding to the specific capability.
     * @return a capability object with all its attributes already set.
     */
    private Capability getCapability(Element capabilityElement){
        Capability capability = new Capability();
        capability.setName(super.getAttributeValue(capabilityElement, "name"));

        this.linkCapabilityToOrganization(capability, super.getAttributeValue(capabilityElement, "organization"));

        return capability;
    }

    /**
     * Fill the capabilityLevels List.
     */
    private void fillCapabilityLevels(){
        Elements capabilityLevelElements = getElementOfType("capabilityLevels").get(0).getChildElements();
        for (int i = 0; i < capabilityLevelElements.size(); i++){
            CapabilityLevel capabilityLevel = getCapabilityLevel(capabilityLevelElements.get(i));
            this.capabilityLevels.add(capabilityLevel);
        }
    }

    /**
     * This method creates a capabilityLevel object and sets its attributes according to the text in the xml
     *
     * @param capabilityLevelElement the node of the xml file corresponding to the specific capabilityLevel.
     * @return an capabilityLevel object with all of its attributes already set.
     */
    private CapabilityLevel getCapabilityLevel(Element capabilityLevelElement){
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setName(getAttributeValue(capabilityLevelElement, "name"));
        capabilityLevel.setHierarchyPosition(getIntValue(capabilityLevelElement, "hierarchyPosition"));
        capabilityLevel.setRequiredSkills(new HashSet<>());

        this.linkCapabilityLevelToOrganization(capabilityLevel, getAttributeValue(capabilityLevelElement, "organization"));
        this.linkCapabilityLevelToCapability(capabilityLevel, getAttributeValue(capabilityLevelElement, "capability"), capabilityLevel.getOrganization());
        this.linkCapabilityLevelToRequiredSkills(capabilityLevel, getAttributeValue(capabilityLevelElement, "requiredSkills"), getAttributeValue(capabilityLevelElement, "organization"));

        return capabilityLevel;
    }

    /**
     * Fill the projects list
     */
    private void fillProjects() {
        Elements projectElements = getElementOfType("projects").get(0).getChildElements();
        for (int i = 0; i <projectElements.size(); i++){
            Project project = getProject(projectElements.get(i));
            this.projects.add(project);
        }
    }

    /**
     * This method creates a Project object and sets its attributes according to the text in the xml
     *
     * @param projectElement the node of the xml file corresponding to the specific project.
     * @return a project with all of its attributes already set.
     */
    private Project getProject(Element projectElement){
        Project project = new Project();
        project.setName(super.getAttributeValue(projectElement, "name"));
        project.setDescription(super.getAttributeValue(projectElement, "description"));
        project.setStartDate(super.getDateValue(projectElement, "startDate"));
        project.setEndDate(super.getDateValue(projectElement, "endDate"));
        project.setJiraLink(super.getAttributeValue(projectElement, "jiraLink"));
        project.setConfluenceLink(super.getAttributeValue(projectElement, "confluenceLink"));
        project.setVersionControlLink(super.getAttributeValue(projectElement, "versionControlLink"));

        project.setProjectCapabilities(new HashSet<>());

        this.linkProjectToCapabilityLevels(project, super.getAttributeValue(projectElement, "projectCapabilityLevelNames"),super.getAttributeValue(projectElement, "organization"), super.getAttributeValue(projectElement, "projectCapabilityLevelCapabilities"));
        this.linkProjectToOrganization(project, super.getAttributeValue(projectElement, "organization"));

        return project;
    }

    /**
     * Fill the projectPositions list
     */
    private void fillProjectPositions() {
        Elements projectPositionElements = getElementOfType("projectPositions").get(0).getChildElements();
        for (int i = 0; i <projectPositionElements.size(); i++){
            ProjectPosition projectPosition = getProjectPosition(projectPositionElements.get(i));
            this.projectPositions.add(projectPosition);
        }
    }

    /**
     * This method creates a Project object and sets its attributes according to the text in the xml
     *
     * @param projectPositionElement the node of the xml file corresponding to the specific project.
     * @return a projectPosition with all of its attributes already set.
     */
    private ProjectPosition getProjectPosition(Element projectPositionElement){
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setProjectPositionStatus(ProjectPositionStatus.valueOf(getAttributeValue(projectPositionElement, "status")));
        projectPosition.setTotalHours(getIntValue(projectPositionElement, "totalHours"));

        this.linkProjectPositionToProject(projectPosition, getAttributeValue(projectPositionElement, "project"), getAttributeValue(projectPositionElement, "capabilityLevelOrganization"));
        this.linkProjectPositionToCapabilityLevel(projectPosition, getAttributeValue(projectPositionElement, "capabilityLevelName"), getAttributeValue(projectPositionElement, "capabilityLevelOrganization"), getAttributeValue(projectPositionElement, "capabilityLevelCapability"));

        return projectPosition;
    }

    /**
     * Fill the languages list
     */
    private void fillLanguages(){
        Elements languagesElements = getElementOfType("languages").get(0).getChildElements();
        for(int i = 0; i < languagesElements.size(); i++){
            Language language = getLanguage(languagesElements.get(i));
            this.languages.add(language);
        }
    }

    /**This method creates a Project and sets its attributes according to the text in the xml file.
     *
     * @param languageElement the node of the xml file corresponding to the specific language.
     * @return a language object with all of its attributes already set.
     */
    private Language getLanguage(Element languageElement){
        Language language = new Language();
        language.setLanguageName(super.getAttributeValue(languageElement, "languageName"));


        return language;
    }




    /**
     * Fills the privacy policy versions list.
     */
    private void fillPrivacyPolicyVersions() {
        Elements privacyPolicyVersionsElements = getElementOfType("privacyPolicyVersions").get(0).getChildElements();
        for (int i = 0; i < privacyPolicyVersionsElements.size(); i++) {
            PrivacyPolicy privacyPolicy = getPrivacyPolicy(privacyPolicyVersionsElements.get(i));
            this.privacyPolicyVersions.add(privacyPolicy);
        }
    }

     /*
     * Fills the terms of service versions list.
     */
    private void fillTermsOfServiceVersions() {
        Elements termsOfServiceVersionsElements = getElementOfType("termsOfServiceVersions").get(0).getChildElements();
        for (int i = 0; i < termsOfServiceVersionsElements.size(); i++){
            TermsOfService termsOfService = this.getTermsOfService(termsOfServiceVersionsElements.get(i));
            this.termsOfServiceVersions.add(termsOfService);
        }
    }

    /**
     * Creates a PrivacyPolicy object and sets its attributes according to the data specified in the DummyData.xml.
     * To set the terms of service HTML content, it obtains the file which stores the content from the xml and then
     * reads it.
     *
     * @param privacyPolicyElement the node of the xml file corresponding to the specific terms of service version
     * @return the PrivacyPolicy created object
     */
    private PrivacyPolicy getPrivacyPolicy(Element privacyPolicyElement) {
        PrivacyPolicy privacyPolicy = new PrivacyPolicy();
        if (privacyPolicyElement.getChildElements("startDate").size() > 0) {
            privacyPolicy.setStartDate(super.getDateValue(privacyPolicyElement, "startDate"));
        }
        if (privacyPolicyElement.getChildElements("endDate").size() > 0) {
            privacyPolicy.setEndDate(super.getDateValue(privacyPolicyElement, "endDate"));
        }
        privacyPolicy.setActive(super.getBooleanValue(privacyPolicyElement, "isActive"));
        String privacyPolicyContent = "";
        try {
            privacyPolicyContent = FileUtils.readFileToString(new File(super.getAttributeValue(privacyPolicyElement, "contentFile")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        privacyPolicy.setContent(privacyPolicyContent);
        privacyPolicy.setPlatform(Platform.valueOf(super.getAttributeValue(privacyPolicyElement, "platform")));

        return privacyPolicy;
    }

    /*
     * @param termsOfServiceElement the node of the xml file corresponding to the specific terms of service version
     * @return the TermsOfService created object
     */
    private TermsOfService getTermsOfService(Element termsOfServiceElement) {
        TermsOfService termsOfService = new TermsOfService();
        if(super.getDateValue(termsOfServiceElement, "startDate") != null)
        	termsOfService.setStartDate(super.getDateValue(termsOfServiceElement, "startDate"));
        if(super.getDateValue(termsOfServiceElement, "endDate") != null)
        	termsOfService.setEndDate(super.getDateValue(termsOfServiceElement, "endDate"));
        termsOfService.setActive(super.getBooleanValue(termsOfServiceElement, "isActive"));
        String termsOfServiceContent = "";
        try {
            termsOfServiceContent = FileUtils.readFileToString(new File(super.getAttributeValue(termsOfServiceElement, "contentFile")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        termsOfService.setToSContent(termsOfServiceContent);
        termsOfService.setPlatform(Platform.valueOf(super.getAttributeValue(termsOfServiceElement, "platform")));

        return termsOfService;
    }

    /**
     * Fill the educationRecords List.
     */
    private void fillEducationRecords () {
        Elements educationRecordElements = getElementOfType("educationRecords").get(0).getChildElements();
        for (int i = 0; i < educationRecordElements.size(); i++) {
            EducationRecord educationRecord = getEducationRecord(educationRecordElements.get(i));
            this.educationRecords.add(educationRecord);
        }
    }

    /**This method creates a EducationRecord and sets its attributes according to the text in the xml file.
     *
     * @param educationRecordElement the node of the xml file corresponding to the specific technical resource.
     * @return an educationRecord object with all of its attributes already set.
     */
    private EducationRecord getEducationRecord(Element educationRecordElement) {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setInstitution(super.getAttributeValue(educationRecordElement, "institution"));
        educationRecord.setDate(super.getDateValue(educationRecordElement, "date"));
        educationRecord.setTitle(super.getAttributeValue(educationRecordElement, "title"));
        educationRecord.setDescription(super.getAttributeValue(educationRecordElement, "description"));

        this.linkEducationRecordToTechnicalResource(educationRecord, super.getAttributeValue(educationRecordElement, "resource"));

        return educationRecord;
    }


    /**
     * Fill the technicalResources List.
     */
    private void fillTechnicalPosition () {
        Elements technicalPositionElements = getElementOfType("technicalPositions").get(0).getChildElements();
        for (int i = 0; i < technicalPositionElements.size(); i++) {
            TechnicalPosition technicalPosition = getTechnicalPosition(technicalPositionElements.get(i));
            this.technicalPositions.add(technicalPosition);
        }
    }

    /**This method creates a TechnicalPosition and sets its attributes according to the text in the xml file.
     *
     * @param technicalPositionElement the node of the xml file corresponding to the specific technical resource.
     * @return a technicalPosition object with all of its attributes already set.
     */
    private TechnicalPosition getTechnicalPosition(Element technicalPositionElement) {
        TechnicalPosition technicalPosition = new TechnicalPosition();
        technicalPosition.setStartDate(super.getDateValue(technicalPositionElement, "startDate"));
        technicalPosition.setEndDate(super.getDateValue(technicalPositionElement, "endDate"));
        technicalPosition.setCareerPath(new CareerPath());

        this.linkTechnicalPositionToTechnicalResource(technicalPosition, super.getAttributeValue(technicalPositionElement,"technicalResource"));
        this.linkTechnicalPositionToCapabilityLevel(technicalPosition, super.getAttributeValue(technicalPositionElement, "capabilityLevelName"), super.getAttributeValue(technicalPositionElement, "capabilityLevelOrganization"), super.getAttributeValue(technicalPositionElement, "capabilityLevelCapability"));

        return technicalPosition;
    }

    /**This method sets the organization attribute to the technical resource object.
     *
     * @param technicalResource the technical resource object which must be linked to respective organization
     * @param organizationUniqueIdentifier the unique identifier of the organization to which the technical resource will be linked.
     */
    private void linkTechnicalResourceToOrganization(TechnicalResource technicalResource, String organizationUniqueIdentifier) {
        Organization organization = null;

        for (Organization organizationIterator : this.organizations) {
            if (organizationIterator.getUniqueIdentifier().equals(organizationUniqueIdentifier)) {
                organization = organizationIterator;
                break;
            }
        }
        assert organization != null;

        technicalResource.setOrganization(organization);
        organization.setTotalUsers(organization.getTotalUsers() + 1);
    }

    /**This method sets the organization attribute to the organizationSkillCategory object.
     *
     * @param organizationSkillCategory the organizationSkillCategory which must be linked to the respective organization.
     * @param organizationUniqueIdentifier the unique identifier of the organization to which the organizationSkillCategory will be linked.
     */
    private void linkOrganizationSkillCategoryToOrganization (OrganizationSkillCategory organizationSkillCategory, String organizationUniqueIdentifier){
        Organization organization = null;

        for (Organization organizationIterator : this.organizations) {
            if (organizationIterator.getUniqueIdentifier().equals(organizationUniqueIdentifier)) {
                organization = organizationIterator;
                break;
            }
        }
        assert organization != null;

        organizationSkillCategory.setOrganization(organization);
    }

    private void linkOrganizationSkillToTechnicalResources (OrganizationSkill organizationSkill, String technicalResourcesNames){
        String[] technicalResourceName = technicalResourcesNames.split(",");

        for(TechnicalResource technicalResourceIterator : this.technicalResources){
            for(int i = 0; i < technicalResourceName.length; i++){
                if(technicalResourceIterator.getUsername().equals(technicalResourceName[i])){

                    organizationSkill.getResources().add(technicalResourceIterator);
                }
            }
        }
    }

    /**This method sets the organizationSkillCategory attribute to the organizationSkill object.
     *
     * @param organizationSkill the organizationSkill which must be linked to the respective organizationSkillCategory.
     * @param organizationSkillCategoryName the name of the organizationSkillCategory to which the organizationSkill will be linked.
     * @param organizationUniqueIdentifier the unique identifier of the organization to which the organizationSkill will be linked.
     */
    private void linkOrganizationSkillToOrganizationSkillCategory (OrganizationSkill organizationSkill,String organizationSkillCategoryName, String organizationUniqueIdentifier){
        OrganizationSkillCategory organizationSkillCategory = null;

        for (OrganizationSkillCategory organizationSkillCategoryIterator : this.organizationSkillCategories) {
            if (organizationSkillCategoryIterator.getName().equals(organizationSkillCategoryName)
                    && organizationSkillCategoryIterator.getOrganization().getUniqueIdentifier().equals(organizationUniqueIdentifier)){
                organizationSkillCategory = organizationSkillCategoryIterator;
                break;
            }
        }
        assert organizationSkillCategory != null;

        organizationSkill.setCategory(organizationSkillCategory);
    }

    /**This method sets the organization attribute to the capability object.
     *
     * @param capability the capability object which must be linked to respective organization
     * @param organizationUniqueIdentifier the unique identifier of the organization to which the capability will be linked.
     */
    private void linkCapabilityToOrganization(Capability capability, String organizationUniqueIdentifier) {
        Organization organization = null;

        for (Organization organizationIterator : this.organizations) {
            if (organizationIterator.getUniqueIdentifier().equals(organizationUniqueIdentifier)) {
                organization = organizationIterator;
                break;
            }
        }
        assert organization != null;

        capability.setOrganization(organization);
    }

    /**This method sets the organization to the capabilityLevel object.
     *
     * @param capabilityLevel the capabilityLevel object which must be linked to the respective organization.
     * @param organizationUniqueIdentifier the unique identifier of the organization to which the capability will be linked.
     */
    private void linkCapabilityLevelToOrganization(CapabilityLevel capabilityLevel, String organizationUniqueIdentifier){
        Organization organization = null;

        for (Organization organizationIterator : this.organizations) {
            if (organizationIterator.getUniqueIdentifier().equals(organizationUniqueIdentifier)) {
                organization = organizationIterator;
                break;
            }
        }
        assert organization != null;

        capabilityLevel.setOrganization(organization);
    }

    /**This method sets the capability to the capabilityLevel object.
     *
     * @param capabilityLevel the capabilityLevel object which must be linked to the respective capability.
     * @param capabilityName the name of the capability to which the capabilityLevel will be linked.
     * @param organization the unique identifier of the organization of the capability to which the capabilityLevel will be linked.
     */
    private void linkCapabilityLevelToCapability (CapabilityLevel capabilityLevel, String capabilityName, Organization organization){
        Capability capability = null;

        for (Capability capabilityIterator : this.capabilities) {
            if (capabilityIterator.getName().equals(capabilityName) && capabilityIterator.getOrganization().equals(organization)){
                capability = capabilityIterator;
                break;
            }
        }
        assert capability != null;

        capabilityLevel.setCapability(capability);
    }


    /**This method sets the language attribute to the technical resource object.
     *
     * @param technicalResource the technical resource object which must be linked to respective language.
     * @param languageName the unique identifier of the language to which the technical resource will be linked.
     */
    private void linkTechnicalResourceToLanguage(TechnicalResource technicalResource, String languageName){
        Language language = null;

        for (Language languageIterator : this.languages){
            if (languageIterator.getLanguageName().equals(languageName)){
                language = languageIterator;
                break;
            }
        }
        assert language != null;

        technicalResource.setLanguage(language);
    }

    /**This method sets the TechnicalResource attribute to the educationRecord object.
     *
     * @param educationRecord the educationRecord object which must be linked to the respective technicalResource.
     * @param technicalResourceUsername the username of the technicalResource to which the educationRecord will be linked.
     */
    private void linkEducationRecordToTechnicalResource(EducationRecord educationRecord, String technicalResourceUsername){
        TechnicalResource technicalResource = null;

        for (TechnicalResource technicalResourceIterator : this.technicalResources) {
            if (technicalResourceIterator.getUsername().equals(technicalResourceUsername)){
                technicalResource = technicalResourceIterator;
                break;
            }
        }
        assert technicalResource != null;

        educationRecord.setResource(technicalResource);
    }

    /**This method sets the TechnicalResource attribute to the technicalPosition object
     *
     * @param technicalPosition the technicalPosition which must be linked to the respective technicalResource.
     * @param technicalResourceUsername the username of the technicalResource to which the educationRecord will be linked.
     */
    private void linkTechnicalPositionToTechnicalResource(TechnicalPosition technicalPosition, String technicalResourceUsername){
        TechnicalResource technicalResource = null;

        for (TechnicalResource technicalResourceIterator : this.technicalResources) {
            if (technicalResourceIterator.getUsername().equals(technicalResourceUsername)){
                technicalResource = technicalResourceIterator;
                break;
            }
        }
        assert technicalResource != null;

        technicalPosition.setTechnicalResource(technicalResource);
    }

    /**This method sets the capabilityLevel attribute to the technicalPosition object.
     *
     * @param technicalPosition the technicalPosition which must be linked to the respective capabilityLevel.
     * @param capabilityLevelName the capabilityLevel name to which the technicalPosition will be linked.
     * @param capabilityLevelOrganization the organization unique identifier of the capabilityLevel to which the technicalPosition will be linked.
     * @param capabilityLevelCapability the capability of the capabilityLevel to which the technicalPosition will be linked.
     */
    private void linkTechnicalPositionToCapabilityLevel(TechnicalPosition technicalPosition, String capabilityLevelName, String capabilityLevelOrganization, String capabilityLevelCapability){
        CapabilityLevel capabilityLevel = null;

        for (CapabilityLevel capabilityLevelIterator : this.capabilityLevels){
            if (capabilityLevelIterator.getName().equals(capabilityLevelName)
                    && capabilityLevelIterator.getOrganization().getUniqueIdentifier().equals(capabilityLevelOrganization)
                    && capabilityLevelIterator.getCapability().getName().equals(capabilityLevelCapability)){

                capabilityLevel = capabilityLevelIterator;
                break;
            }
        }
        assert  capabilityLevel != null;

        technicalPosition.setCapabilityLevel(capabilityLevel);
    }

    /**This method sets the organization attribute to the project object.
     *
     * @param project the project object which must be linked to the respective organization.
     * @param organizationUniqueIdentifier the unique identifier of the organization to which the project will be linked.
     */
    private void linkProjectToOrganization(Project project, String organizationUniqueIdentifier){
        Organization organization = null;

        for (Organization organizationIterator : this.organizations){
            if(organizationIterator.getUniqueIdentifier().equals(organizationUniqueIdentifier)){
                organization = organizationIterator;
                break;
            }
        }
        assert organization != null;

        project.setOrganization(organization);
    }

    /**This method adds multiple required skills attributes to a capabilityLevelObject
     *
     * @param capabilityLevel the capabilityLevel object to which we will add multiple required skills.
     * @param requiredSkillsNames the names of the multiple required skills separated by a coma.
     * @param organizationUniqueIdentifier the unique identifier of the organization of each required skill.
     */
    private void linkCapabilityLevelToRequiredSkills(CapabilityLevel capabilityLevel, String requiredSkillsNames, String organizationUniqueIdentifier){

        String[] skills = requiredSkillsNames.split(",");

        for (OrganizationSkill organizationSkillIterator : this.organizationSkills){
            for (int i = 0; i < skills.length; i++){
                if(organizationSkillIterator.getName().equals(skills[i])
                        && organizationSkillIterator.getCategory().getOrganization().getUniqueIdentifier().equals(organizationUniqueIdentifier)){

                    capabilityLevel.getRequiredSkills().add(organizationSkillIterator);
                }
            }
        }
    }

    /**This method will the project attribute to the projectPosition object.
     *
     * @param projectPosition the projectPosition which must be linked with the respective project.
     * @param projectName the name of the project to which the projectPosition will be linked.
     * @param organizationUniqueIdentifier the unique identifier of the organization of the project to which the projectPosition will be linked.
     */
    private void linkProjectPositionToProject(ProjectPosition projectPosition, String projectName, String organizationUniqueIdentifier){
        Project project = null;

        for (Project projectIterator : this.projects){
            if(projectIterator.getName().equals(projectName)
                    && projectIterator.getOrganization().getUniqueIdentifier().equals(organizationUniqueIdentifier)) {

                project = projectIterator;
                break;
            }
        }
        assert project != null;

        projectPosition.setProject(project);
    }

    /**This method will set the capabilityLevel attribute to the projectPosition object.
     *
     * @param projectPosition the projectPosition which must be linked with the respective capabilityLevel.
     * @param capabilityLevelName the capabilityLevel name to which the projectPosition will be linked.
     * @param capabilityLevelOrganizationUniqueIdentifier the organization unique identifier of the capabilityLevel to which the projectPosition will be linked.
     * @param capabilityLevelCapability the capability of the capabilityLevel to which the projectPosition will be linked.
     */
    private void linkProjectPositionToCapabilityLevel(ProjectPosition projectPosition, String capabilityLevelName, String capabilityLevelOrganizationUniqueIdentifier, String capabilityLevelCapability){
        CapabilityLevel capabilityLevel = null;

        for (CapabilityLevel capabilityLevelIterator : this.capabilityLevels){
            if (capabilityLevelIterator.getName().equals(capabilityLevelName)
                    && capabilityLevelIterator.getOrganization().getUniqueIdentifier().equals(capabilityLevelOrganizationUniqueIdentifier)
                    && capabilityLevelIterator.getCapability().getName().equals(capabilityLevelCapability)){

                capabilityLevel = capabilityLevelIterator;
                break;
            }
        }
        assert  capabilityLevel != null;

        projectPosition.setCapabilityLevel(capabilityLevel);
    }

    /**This method will set the projectCapabilities attribute to the project object.
     *
     * @param project the project which must be linked wit the respective capabilityLevel.
     * @param capabilityLevelNames the capabilityLevel name to which the project will be linked.
     * @param capabilityLevelOrganizationUniqueIdentifier the organization unique identifier of the capabilityLevel to which the project will be linked.
     * @param capabilityLevelCapabilities the capability of the capabilityLevel to which the project will be linked.
     */
    private void linkProjectToCapabilityLevels(Project project, String capabilityLevelNames, String capabilityLevelOrganizationUniqueIdentifier, String capabilityLevelCapabilities){
        String[] capabilityLevelName = capabilityLevelNames.split(",");
        String[] capabilityLevelCapability= capabilityLevelCapabilities.split(",");

        for (CapabilityLevel capabilityLevelIterator : this.capabilityLevels){
            for(int i = 0; i < capabilityLevelName.length; i++) {
                if (capabilityLevelIterator.getName().equals(capabilityLevelName[i])
                        && capabilityLevelIterator.getOrganization().getUniqueIdentifier().equals(capabilityLevelOrganizationUniqueIdentifier)
                        && capabilityLevelIterator.getCapability().getName().equals(capabilityLevelCapability[i])) {

                    project.getProjectCapabilities().add(capabilityLevelIterator);
                }
            }
        }
    }

    List<Organization> getOrganizations() {
        return this.organizations;
    }

    List<TechnicalResource> getTechnicalResources() {
        return this.technicalResources;
    }

    List<PrivacyPolicy> getPrivacyPolicyVersions() { return this.privacyPolicyVersions; }

    public List<TermsOfService> getTermsOfServiceVersions() {
        return termsOfServiceVersions;
    }

    List<Language> getLanguages(){
        return this.languages;
    }

    List<Project> getProjects(){
        return this.projects;
    }

    List<OrganizationSkillCategory> getOrganizationSkillCategories() {
        return this.organizationSkillCategories;
    }

    List<OrganizationSkill> getOrganizationSkills(){
        return this.organizationSkills;
    }

    List<Capability> getCapabilities(){
        return this.capabilities;
    }

    List<CapabilityLevel> getCapabilityLevels() {
        return this.capabilityLevels;
    }

    List<EducationRecord> getEducationRecords() {
        return this.educationRecords;
    }

    List<ProjectPosition> getProjectPositions() {
        return this.projectPositions;
    }

    List<TechnicalPosition> getTechnicalPositions() {
        return this.technicalPositions;
    }
}
