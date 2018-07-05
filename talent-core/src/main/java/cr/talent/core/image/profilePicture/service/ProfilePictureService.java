package cr.talent.core.image.profilePicture.service;

import cr.talent.model.ProfilePicture;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

import java.io.InputStream;

/**
 * Provides business logic services related to {@link cr.talent.model.ProfilePicture} entities.
 *
 * @author María José Cubero
 */
public interface ProfilePictureService extends CrudService<ProfilePicture, String> {

    /**
     * uploads or updates the picture of the logged in user that is making the request.
     * @param file the file to be uploaded.
     */
    void uploadProfilePicture(InputStream file);

    /**
     * deletes the picture of the loggedin user that is making the request.
     */
    void deleteProfilePicture();

    /**
     * Changes a technical resource to use the default profile picture
     * @param technicalResource the technical resource that will have its profile picture modified
     */
    void setDefaultProfilePicture(TechnicalResource technicalResource);

}
