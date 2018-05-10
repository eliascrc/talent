package cr.talent.core.passwordResetRequest.service;

import cr.talent.model.PasswordResetRequest;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.PasswordResetRequest} entities.
 *
 * @author María José Cubero.
 */
public interface PasswordResetRequestService extends CrudService<PasswordResetRequest, String> {

    /**
     *Creates an object of {@link cr.talent.model.PasswordResetRequest}
     * @param email
     */
    void createPasswordRequestReset(String email);

    /**
     * Retrieves the flag to know if the token is still valid.
     * @return the password reset request.
     */
    boolean isTokenValid (String token);

    /**
     * Resets the user's password and sets the token to invalid.
     * @param token
     * @param newPassword
     */
    boolean resetPassword (String token, String newPassword);

}
