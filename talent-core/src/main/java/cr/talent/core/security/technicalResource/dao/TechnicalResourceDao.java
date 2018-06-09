package cr.talent.core.security.technicalResource.dao;

import cr.talent.support.dao.CrudDao;
import cr.talent.model.TechnicalResource;

/**
 * Data access object for all the {@link TechnicalResource} related operations.
 *
 * @author Josué León Sarkis
 */
public interface TechnicalResourceDao extends CrudDao<TechnicalResource, String> {

    /**
     * Finds a technical resource by its username.
     *
     * @param username The technical resource's username to search for.
     * @return The TechnicalResource with the respective username.
     */
    TechnicalResource findTechnicalResourceByUsername(String username);

    /**
     * Finds a technical resource by its authentication token.
     *
     * @param token The technical resource's authentication token to search for.
     * @return The TechnicalResource with the respective authentication token.
     */
    TechnicalResource findByAuthenticationToken(String token);
}
