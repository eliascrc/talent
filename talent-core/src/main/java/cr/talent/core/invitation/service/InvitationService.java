package cr.talent.core.invitation.service;

import cr.talent.model.Invitation;
import cr.talent.model.Organization;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.Invitation} entities.
 *
 * @author Elias Calderon
 */
public interface InvitationService extends CrudService<Invitation, String> {

    /**
     * Creates an object of {@link cr.talent.model.Invitation}
     * @param email the email of the resource to invite
     * @param organization the organization to relate the invitation with.
     */
    void createInvitation(String email, Organization organization);

}
