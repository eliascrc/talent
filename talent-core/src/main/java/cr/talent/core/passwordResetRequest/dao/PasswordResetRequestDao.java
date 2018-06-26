package cr.talent.core.passwordResetRequest.dao;

import cr.talent.model.PasswordResetRequest;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link cr.talent.model.PasswordResetRequest} related operations.
 *
 * @author María José Cubero.
 */

public interface PasswordResetRequestDao extends CrudDao<PasswordResetRequest, String>{
    /**
     * Retrieves the password reset request that corresponds to a token.
     * @return the password reset request.
     */
    PasswordResetRequest findByToken(String token);

    /**
     * Retrieves the password reset request that corresponds to an email.
     * @return the password reset request.
     */
    PasswordResetRequest findByEmailAndOrganizationIdentifier(String email, String organizationIdentifier);

}
