package cr.talent.core.image.profilePicture.service;

import cr.talent.model.ProfilePicture;
import cr.talent.support.service.CrudService;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * Provides business logic services related to {@link cr.talent.model.ProfilePicture} entities.
 *
 * @author María José Cubero
 */
public interface ProfilePictureService extends CrudService<ProfilePicture, String> {

    /**
     * uploads the picture of the logged in user that is making the request.
     * @param file
     */
    void uploadProfilePicture(InputStream file);

    /**
     * deletes the picture of the logged in user that is making the request.
     */
    void deleteProfilePicture();

    /**
     * updates the picture of the logged in user that is making the request.
     * @param file
     */
    void updateProfilePicture(InputStream file);
}
