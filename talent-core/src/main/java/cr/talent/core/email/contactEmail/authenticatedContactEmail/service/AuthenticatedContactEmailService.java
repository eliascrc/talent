package cr.talent.core.email.contactEmail.authenticatedContactEmail.service;

import cr.talent.model.AuthenticatedContactEmail;

/**
 * Provides business logic services related to {@link cr.talent.model.AuthenticatedContactUsNotification} entities.
 *
 * @author Fabián Roberto Leandro
 */
public interface AuthenticatedContactEmailService {
    /**
     * Sends a copy of the Contact Us message submitted by a user to their email.
     * @param authenticatedContactEmail
     */
    void sendAuthenticatedContactEmail(AuthenticatedContactEmail authenticatedContactEmail);
}
