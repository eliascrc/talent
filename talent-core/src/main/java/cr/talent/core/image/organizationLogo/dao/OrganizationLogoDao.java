package cr.talent.core.image.organizationLogo.dao;

import cr.talent.model.OrganizationLogo;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link cr.talent.model.Image} in related operations.
 *
 * @author María José Cubero
 */
public interface OrganizationLogoDao extends CrudDao<OrganizationLogo, String> {

    /**
     * Finds an organization logo with a given link
     * @param link the link of the organization logo
     * @return the organization logo with the given link
     */
    OrganizationLogo findLogoByLink(String link);

}
