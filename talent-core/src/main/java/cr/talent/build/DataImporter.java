package cr.talent.build;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.List;

public class DataImporter {

    private final ClassPathXmlApplicationContext context;

    private DataImporter(){
        this.context = new ClassPathXmlApplicationContext("talent-core.spring.xml", "talent-persistence.spring.xml", "talent-security.spring.xml");
    }

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
