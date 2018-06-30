package cr.talent.build;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.privacyPolicy.service.PrivacyPolicyService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.core.skill.service.SkillService;
import cr.talent.core.skillCategory.service.SkillCategoryService;
import cr.talent.core.termsOfService.service.ToSService;
import cr.talent.core.capabilityLevel.service.CapabilityLevelService;
import cr.talent.core.capability.service.CapabilityService;
import cr.talent.core.educationRecord.service.EducationRecordService;
import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService;
import cr.talent.core.technicalPosition.service.TechnicalPositionService;
import cr.talent.core.leadPosition.service.LeadPositionService;
import cr.talent.core.language.service.LanguageService;
import cr.talent.core.project.service.ProjectService;
import cr.talent.core.feedback.service.FeedbackService;
import cr.talent.core.projectEvent.service.ProjectEventService;
import cr.talent.core.security.humanResourceManager.service.HumanResourceManagerService;
import cr.talent.model.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;


/**
 * This class will import objects into the data base, its only attribute its the ClassPathXmlApplicationContext which will get the configuration files in resources.
 *
 * @author Elías Calderón
 */
public class DataImporter {

    /**
     * The spring context used to import data
     */
    private final ClassPathXmlApplicationContext context;

    /**
     * Constructor of the class that creates the context
     */
    private DataImporter(){
        this.context = new ClassPathXmlApplicationContext("talent-core.spring.xml", "talent-persistence.spring.xml", "talent-security.spring.xml");
    }


    /**
     * This method creates a dataParser which then is used to create the objects and store them in arrays.
     * It uses the OrganizationService and the TechnicalResourceService to create each each object will in turn
     * will add them to the database.
     */
    private void importData(){
        DataParser dataParser = new DataParser("./talent-core/src/main/resources/DummyData.xml");
        dataParser.parseData();

        List<Organization> organizations = dataParser.getOrganizations();
        OrganizationService organizationService = context.getBean(OrganizationService.class);

        for (Organization organization : organizations) {
            organizationService.create(organization);
        }


        List<Language> languages = dataParser.getLanguages();
        LanguageService languageService = context.getBean(LanguageService.class);

        for (Language language : languages){
            languageService.create(language);
        }

        List<TechnicalResource> technicalResources = dataParser.getTechnicalResources();
        TechnicalResourceService technicalResourceService = context.getBean(TechnicalResourceService.class);

        for (TechnicalResource technicalResource: technicalResources) {
            technicalResourceService.create(technicalResource);
        }

        List<HumanResourceManager> humanResourceManagers = dataParser.getHumanResourceManagers();
        HumanResourceManagerService humanResourceManagerService = context.getBean(HumanResourceManagerService.class);

        for (HumanResourceManager humanResourceManager: humanResourceManagers) {
            humanResourceManagerService.create(humanResourceManager);
        }

        List<PrivacyPolicy> privacyPolicyVersions = dataParser.getPrivacyPolicyVersions();
        PrivacyPolicyService privacyPolicyService = context.getBean(PrivacyPolicyService.class);

        for (PrivacyPolicy privacyPolicyVersion : privacyPolicyVersions) {
            privacyPolicyService.create(privacyPolicyVersion);
        }

        List<TermsOfService> termsOfServiceVersions = dataParser.getTermsOfServiceVersions();
        ToSService toSService = context.getBean(ToSService.class);

        for(TermsOfService termsOfService : termsOfServiceVersions) {
            toSService.create(termsOfService);
        }

        List<EducationRecord> educationRecords = dataParser.getEducationRecords();
        EducationRecordService educationRecordService = context.getBean(EducationRecordService.class);

        for (EducationRecord educationRecord : educationRecords){
            educationRecordService.create(educationRecord);
        }

        List<SkillCategory> skillCategories = dataParser.getSkillCategories();
        SkillCategoryService skillCategoryService = context.getBean(SkillCategoryService.class);

        for(SkillCategory skillCategory : skillCategories){
            skillCategoryService.create(skillCategory);
        }

        List<Skill> skills = dataParser.getSkills();
        SkillService skillService = context.getBean(SkillService.class);

        for(Skill skill : skills){
            skillService.create(skill);
        }

        List<Capability> capabilities = dataParser.getCapabilities();
        CapabilityService capabilityService = context.getBean(CapabilityService.class);

        for (Capability capability : capabilities){
            capabilityService.create(capability);
        }

        List<CapabilityLevel> capabilityLevels = dataParser.getCapabilityLevels();
        CapabilityLevelService capabilityLevelService = context.getBean(CapabilityLevelService.class);

        for (CapabilityLevel capabilityLevel :  capabilityLevels){
            capabilityLevelService.create(capabilityLevel);
        }

        List<TechnicalPosition> technicalPositions = dataParser.getTechnicalPositions();
        TechnicalPositionService technicalPositionService = context.getBean(TechnicalPositionService.class);

        for(TechnicalPosition technicalPosition : technicalPositions){
            technicalPositionService.create(technicalPosition);
        }


        List<Project> projects = dataParser.getProjects();
        ProjectService projectService = context.getBean(ProjectService.class);

        for (Project project : projects) {
            projectService.create(project);
        }

        List<ProjectEvent> projectEvents = dataParser.getProjectEvents();
        ProjectEventService projectEventService = context.getBean(ProjectEventService.class);

        for (ProjectEvent projectEvent : projectEvents){
            projectEventService.create(projectEvent);
            for(Project project : projects)
            {
                if (project.getName().equals(projectEvent.getProject().getName())){
                    project.setcurrentState(projectEvent);
                    projectService.update(project);
                }
            }
        }

        List<ProjectPosition> projectPositions = dataParser.getProjectPositions();
        ProjectPositionService projectPositionService = context.getBean(ProjectPositionService.class);

        for (ProjectPosition projectPosition : projectPositions){
            projectPositionService.create(projectPosition);
        }

        List<ProjectPositionHolder> projectPositionHolders = dataParser.getProjectPositionHolders();
        ProjectPositionHolderService projectPositionHolderService = context.getBean(ProjectPositionHolderService.class);

        for (ProjectPositionHolder projectPositionHolder : projectPositionHolders){
            projectPositionHolderService.create(projectPositionHolder);
        }

        List<LeadPosition> leadPositions = dataParser.getLeadPositions();
        LeadPositionService leadPositionService = context.getBean(LeadPositionService.class);

        for (LeadPosition leadPosition : leadPositions) {
            leadPositionService.create(leadPosition);
        }

        List<Feedback> feedbacks = dataParser.getFeedbacks();
        FeedbackService feedbackService = context.getBean(FeedbackService.class);

        for (Feedback feedback : feedbacks){
            feedbackService.create(feedback);
        }
    }

    public static void main(String[] args) {
        DataImporter dataImporter = new DataImporter();
        dataImporter.importData();
    }
}