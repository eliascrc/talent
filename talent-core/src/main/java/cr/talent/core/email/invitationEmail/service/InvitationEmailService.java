package cr.talent.core.email.invitationEmail.service;

import cr.talent.model.Invitation;

/**
 * Provides business logic services related to {@link cr.talent.model.Email},
 * for the invitations.
 *
 * @author Elias Calderon
 */
public interface InvitationEmailService {
    /**
     * Sends the invitation email to a user that requests that action.
     * @param invitation the invitation to send
     */
    void sendInvitationEmail(Invitation invitation);
}
