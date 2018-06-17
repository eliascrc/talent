package cr.talent.core.invitation.service;

import cr.talent.model.Invitation;
import cr.talent.model.Organization;
import cr.talent.support.service.CrudService;

import java.util.List;

/**
 * Provides business logic services related to {@link cr.talent.model.Invitation} entities.
 *
 * @author Elias Calderon
 */
public interface InvitationService extends CrudService<Invitation, String> {

    /**
     * Persists a list of {@link cr.talent.model.Invitation}.
     * @param emails the emails of the resources to invite
     * @param organization the organization to relate the invitations with.
     */
    void createInvitations(List<String> emails, Organization organization);

}
