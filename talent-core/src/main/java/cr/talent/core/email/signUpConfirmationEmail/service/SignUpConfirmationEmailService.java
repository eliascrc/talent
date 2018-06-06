package cr.talent.core.email.signUpConfirmationEmail.service;

import cr.talent.model.SignUpConfirmationMessage;
import cr.talent.model.TechnicalResource;

/**
 * Provides business logic services related to {@link cr.talent.model.Email},
 * for the sign up confirmation request.
 *
 * @author Daniel Montes de Oca
 */
public interface SignUpConfirmationEmailService {

    /**
     * Sends the sign up confirmation email to a user that requests that action.
     * @param destinationEmail
     * @param signUpConfirmationMessage
     */
    void sendSignUpConfirmationEmail(String destinationEmail, SignUpConfirmationMessage signUpConfirmationMessage, TechnicalResource technicalResource);

}
