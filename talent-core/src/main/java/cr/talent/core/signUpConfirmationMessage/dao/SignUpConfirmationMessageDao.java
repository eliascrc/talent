package cr.talent.core.signUpConfirmationMessage.dao;

import cr.talent.model.SignUpConfirmationMessage;
import cr.talent.support.dao.CrudDao;

/**
 * Data access object for all the {@link cr.talent.model.SignUpConfirmationMessage} related operations.
 *
 * @author Daniel Montes de Oca
 */
public interface SignUpConfirmationMessageDao extends CrudDao<SignUpConfirmationMessage, String> {

    /**
     * Retrieves an active sign up confirmation message for a specific user
     * @param username the email of the user performing the sign up
     */
    SignUpConfirmationMessage getActiveConfirmationMessage(String username);

}
