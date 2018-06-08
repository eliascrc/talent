package cr.talent.core.image.organizationLogo.service.impl;

import cr.talent.core.image.dao.ImageDao;
import cr.talent.core.image.organizationLogo.dao.OrganizationLogoDao;
import cr.talent.core.image.organizationLogo.service.OrganizationLogoService;
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

    public void init() {
        setCrudDao(this.organizationLogoDao);
    }

    @Override
    public void uploadOrganizationLogo(InputStream file){
        TechnicalResource technicalResource = (TechnicalResource) SecurityUtils.getLoggedInUser();
        if (technicalResource.isAdministrator()){
            Organization organization = technicalResource.getOrganization();

            if (organization.getLogo() != null)
                this.deleteOrganizationLogo();

            OrganizationLogo organizationLogo = new OrganizationLogo();
            this.create(organizationLogo);

            organizationLogo.setLink(link + organizationLogo.getId() + FILE_EXTENSION);
            organization.setLogo(organizationLogo);

            this.imageDao.uploadImage(organizationLogo.getId() + FILE_EXTENSION, file, FOLDER);
        }
    }

    @Override
    public void deleteOrganizationLogo(){
        TechnicalResource technicalResource = (TechnicalResource) SecurityUtils.getLoggedInUser();
        if (technicalResource.isAdministrator()){
            Organization organization = technicalResource.getOrganization();

            OrganizationLogo organizationLogo = organization.getLogo();
            this.remove(organizationLogo);

            this.imageDao.deleteImage(organizationLogo.getId() + FILE_EXTENSION, FOLDER);
        }
    }

    @Override
    public void updateOrganizationLogo(InputStream file){
        this.deleteOrganizationLogo();
        this.uploadOrganizationLogo(file);

        TechnicalResource technicalResource= (TechnicalResource) SecurityUtils.getLoggedInUser();
        if (technicalResource.isAdministrator()){
            Organization organization = technicalResource.getOrganization();
            OrganizationLogo organizationLogo= organization.getLogo();

            this.update(organizationLogo);
        }
    }

}
