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
     * Creates a new confirmation message or edits the existent if there was one before that and sends it, and it
     * does the same with the technical resource
     * @param signUpConfirmationMessage the confirmation message that will be sent
     * @param technicalResource the technical resource that will be saved and will receive the email
     * @param hadAnotherConfirmationMessage whether or not there was a confirmation message already for that email
     */
    void sendMessage(SignUpConfirmationMessage signUpConfirmationMessage, TechnicalResource technicalResource, boolean hadAnotherConfirmationMessage);
}
