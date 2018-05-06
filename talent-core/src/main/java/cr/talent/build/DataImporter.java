package cr.talent.build;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
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
        DataParser dataParser = new DataParser("src/main/resources/DummyData.xml");
        dataParser.parseData();

        List<Organization> organizations = dataParser.getOrganizations();
        OrganizationService organizationService = context.getBean(OrganizationService.class);

        for (Organization organization : organizations) {
            organizationService.create(organization);
        }

        List<TechnicalResource> technicalResources = dataParser.getTechnicalResources();
        TechnicalResourceService technicalResourceService = context.getBean(TechnicalResourceService.class);

        for (TechnicalResource technicalResource: technicalResources) {
            technicalResourceService.create(technicalResource);
        }
    }

    public static void main(String[] args) {
        DataImporter dataImporter = new DataImporter();
        dataImporter.importData();
    }
}