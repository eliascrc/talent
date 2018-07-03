package cr.talent.core.image.profilePicture.dao;

import cr.talent.model.Image;
import cr.talent.model.ProfilePicture;
import cr.talent.model.TechnicalResource;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link cr.talent.model.ProfilePicture} in related operations.
 *
 * @author María José Cubero
 */
public interface ProfilePictureDao extends CrudDao<ProfilePicture, String> {

    /**
     * Finds an organization logo with a given link
     * @param link the link of the organization logo
     * @return the organization logo with the given link
     */
    ProfilePicture findProfilePictureByLink(String link);

}
