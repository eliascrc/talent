package cr.talent.core.email.basicEmail.service;

import cr.talent.model.Email;

import java.util.Map;

/**
 * Provides business logic services related to {@link cr.talent.model.Email}.
 *
 * @author María José Cubero.
 */
public interface EmailSenderService {

    /**
     * Sends the forgot password email to a user that requests that action.
     * @param email
     * @param model
     */
    void sendEmail(Email email, Map< String, Object> model);
}
