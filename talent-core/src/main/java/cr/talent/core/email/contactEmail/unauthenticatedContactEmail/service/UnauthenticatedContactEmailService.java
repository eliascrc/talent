package cr.talent.core.email.contactEmail.unauthenticatedContactEmail.service;

import cr.talent.model.UnauthenticatedContactEmail;

/**
 * Provides business logic services related to {@link cr.talent.model.UnauthenticatedContactUsNotification} entities.
 *
 * @author Fabián Roberto Leandro
 */
public interface UnauthenticatedContactEmailService {
    /**
     * Sends a copy of the Contact Us message submitted by a person to the email they entered.
     * @param unauthenticatedContactEmail
     */
    void sendUnauthenticatedContactEmail(UnauthenticatedContactEmail unauthenticatedContactEmail);
}
