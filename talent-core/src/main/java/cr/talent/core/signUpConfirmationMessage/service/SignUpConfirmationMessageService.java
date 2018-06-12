package cr.talent.core.signUpConfirmationMessage.service;

import cr.talent.model.SignUpConfirmationMessage;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.SignUpConfirmationMessage} entities.
 *
 * @author Daniel Montes de Oca
 */
public interface SignUpConfirmationMessageService extends CrudService<SignUpConfirmationMessage, String> {

    /**
     * Retrieves the last confirmation message that the user has not yet confirmed if it does not exist
     * @param username The username of the user performing the first step of the sign up
     * @return the confirmation message if it exists for that username
     */
    SignUpConfirmationMessage getActiveConfirmationMessage(String username);

    /**
     * Tries to match the provided code to the last one that was sent to the provided email. It deletes the confirmation
     * message and activates the user account if it matches the confirmation.
     * @param code the code provided by the user
     * @param username the user's email
     * @return true if it matches the confirmation, false if not
     */
    boolean confirmEmail(String code, String username);
}
