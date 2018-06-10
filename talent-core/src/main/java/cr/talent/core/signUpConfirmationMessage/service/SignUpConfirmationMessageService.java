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
     * Creates a technical resource with the supplied information if it is valid and sends a confirmation email. If the
     * password is not valid it throws an exception with a code that reflects the problem.
     * @param firstName the first name of the resource performing the first step of the sign up
     * @param lastName the last name of the resource performing the first step of the sign up
     * @param username the email of the resource performing the first step of the sign up
     * @param password the password of the resource performing the first step of the sign up
     */
    void sendMessage(String firstName, String lastName, String username, String password);
}
