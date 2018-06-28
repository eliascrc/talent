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
     * Creates a technical resource with the supplied information if it is valid and sends a confirmation email. If the
     * password is not valid it throws an exception with a code that reflects the problem.
     * @param firstName the first name of the resource performing the first step of the sign up
     * @param lastName the last name of the resource performing the first step of the sign up
     * @param nickname the nickname of the resource performing the first step of the sign up
     * @param username the email of the resource performing the first step of the sign up
     * @param password the password of the resource performing the first step of the sign up
     * @return the created or updated technical resource
     */
    TechnicalResource sendMessage(String firstName, String lastName, String nickname, String username, String password);

    /**
     * Tries to match the provided code to the last one that was sent to the provided email. It deletes the confirmation
     * message and activates the user account if it matches the confirmation.
     * @param code the code provided by the user
     * @param username the user's email
     * @return true if it matches the confirmation, false if not
     */
    boolean confirmEmail(String code, String username);

    /**
     * Gets the confirmation code for a given email.
     * @param email the provided email.
     * @return the sign up code.
     */
    String getCode(String email);
}
