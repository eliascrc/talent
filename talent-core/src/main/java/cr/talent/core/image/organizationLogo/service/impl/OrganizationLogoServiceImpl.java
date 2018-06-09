package cr.talent.core.image.organizationLogo.service.impl;

import cr.talent.core.image.dao.ImageDao;
import cr.talent.core.image.organizationLogo.dao.OrganizationLogoDao;
import cr.talent.core.image.organizationLogo.service.OrganizationLogoService;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Organization;
import cr.talent.model.OrganizationLogo;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InputStream;


/**
 * Default implementation of the {@link cr.talent.core.image.organizationLogo.service.OrganizationLogoService}.
 *
 * @author María José Cubero
 */
@Service("organizationLogoService")
@Transactional
public class OrganizationLogoServiceImpl extends CrudServiceImpl<OrganizationLogo, String> implements OrganizationLogoService {

    @Value("${talent.s3.image_basic_link}")
    private String link;

    private static final String FILE_EXTENSION = ".jpg";
    private static final String FOLDER = "/organizations-logo";

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private OrganizationLogoDao organizationLogoDao;

    @Autowired
    private TechnicalResourceService technicalResourceService;

    @Autowired
    private OrganizationService organizationService;

    public void init() {
        setCrudDao(this.organizationLogoDao);
    }

    @Override
    public void uploadOrganizationLogo(InputStream file) {
        TechnicalResource technicalResource = (TechnicalResource) SecurityUtils.getLoggedInUser();
        TechnicalResource technicalResource1 = this.technicalResourceService.findById(technicalResource.getId());

        if (technicalResource1.isAdministrator()) {
            Organization organization1 = technicalResource.getOrganization();
            Organization organization = technicalResource1.getOrganization();

            if (organization.getLogo() != null)
                    this.deleteOrganizationLogo();

            OrganizationLogo organizationLogo = new OrganizationLogo();
            this.create(organizationLogo);

            organizationLogo.setLink(link + FOLDER + "/" + organizationLogo.getId() + FILE_EXTENSION);
            organization1.setLogo(organizationLogo);
            organization.setLogo(organizationLogo);
            this.organizationService.update(organization);
          //  this.technicalResourceService.update(technicalResource1);

            this.imageDao.uploadImage(organizationLogo.getId() + FILE_EXTENSION, file, FOLDER);
        }
    }

    @Override
    public void deleteOrganizationLogo() {
        TechnicalResource technicalResource = (TechnicalResource) SecurityUtils.getLoggedInUser();
        TechnicalResource technicalResource1 = this.technicalResourceService.findById(technicalResource.getId());
        Organization organization = technicalResource.getOrganization();
        Organization organization1 = technicalResource1.getOrganization();
        OrganizationLogo organizationLogo = organization1.getLogo();

        if (technicalResource1.isAdministrator() && organizationLogo != null) {
            organization1.setLogo(null);
            this.organizationService.update(organization1);
            organization.setLogo(null);

            this.remove(organizationLogo);
            this.imageDao.deleteImage(organizationLogo.getId() + FILE_EXTENSION, FOLDER);
        }
    }
}
