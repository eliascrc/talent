package cr.talent.core.Email.PasswordResetEmail.service;

import cr.talent.model.PasswordResetRequest;

/**
 * Provides business logic services related to {@link cr.talent.model.Email},
 * for the password reset request.
 *
 * @author María José Cubero.
 */public interface PasswordResetEmailService {

    /**
     * Sends the forgot password email to a user that requests that action.
     * @param destinationEmail
     * @param passwordResetRequest
     */
    void sendPasswordResetMail(String destinationEmail, PasswordResetRequest passwordResetRequest);
}
