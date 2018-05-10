package cr.talent.build;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.core.termsOfService.service.ToSService;
import cr.talent.core.capabilityLevel.service.CapabilityLevelService;
import cr.talent.core.capability.service.CapabilityService;


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


        List<OrganizationSkillCategory> organizationSkillCategories = dataParser.getOrganizationSkillCategories();
        OrganizationSkillCategoryService organizationSkillCategoryService = context.getBean(OrganizationSkillCategoryService.class);

        for(OrganizationSkillCategory organizationSkillCategory : organizationSkillCategories){
            organizationSkillCategoryService.create(organizationSkillCategory);
        }

        List<OrganizationSkill> organizationSkills = dataParser.getOrganizationSkills();
        OrganizationSkillService organizationSkillService = context.getBean(OrganizationSkillService.class);

        for(OrganizationSkill organizationSkill : organizationSkills){
            organizationSkillService.create(organizationSkill);
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

        List<ProjectPosition> projectPositions = dataParser.getProjectPositions();
        ProjectPositionService projectPositionService = context.getBean(ProjectPositionService.class);

        for (ProjectPosition projectPosition : projectPositions){
            projectPositionService.create(projectPosition);
        }

    }

    public static void main(String[] args) {
        DataImporter dataImporter = new DataImporter();
        dataImporter.importData();
    }
}