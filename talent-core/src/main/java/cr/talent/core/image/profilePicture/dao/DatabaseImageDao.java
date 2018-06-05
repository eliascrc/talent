package cr.talent.core.image.profilePicture.dao;

import cr.talent.model.Image;
import cr.talent.model.ProfilePicture;
import cr.talent.model.TechnicalResource;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link cr.talent.model.Image} in related operations.
 *
 * @author María José Cubero
 */
public interface DatabaseImageDao extends CrudDao<ProfilePicture, String> {

}
