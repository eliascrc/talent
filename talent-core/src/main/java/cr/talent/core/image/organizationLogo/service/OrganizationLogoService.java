package cr.talent.core.image.organizationLogo.service;

import cr.talent.model.OrganizationLogo;
import cr.talent.model.ProfilePicture;
import cr.talent.support.service.CrudService;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Provides business logic services related to {@link cr.talent.model.OrganizationLogo} entities.
 *
 * @author María José Cubero
 */
public interface OrganizationLogoService extends CrudService<OrganizationLogo, String> {

    /**
     * uploads or updates the picture of the organization that is making the request.
     *
     * @param file
     */
    void uploadOrganizationLogo(InputStream file);

    /**
     * deletes the picture of the organization that is making the request.
     */
    void deleteOrganizationLogo();
}