package cr.talent.core.invitation.service;

import cr.talent.model.Invitation;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.CrudService;

import java.util.List;

/**
 * Provides business logic services related to {@link cr.talent.model.Invitation} entities.
 *
 * @author Elias Calderon, Josue Cubero
 */
public interface InvitationService extends CrudService<Invitation, String> {

    /**
     * Persists a list of {@link cr.talent.model.Invitation}.
     * @param technicalResource the technical resource making the invitation.
     * @param invitations the invitations JSON of the resources to invite
     * @param organization the organization to relate the invitations with.
     */
    void createInvitations(TechnicalResource technicalResource, String invitations, Organization organization);

    /**
     * Validates if a token from {@link cr.talent.model.Invitation} is still active.
     * @param token the token to be validated.
     * @return the invitation instance.
     */
    Invitation isTokenValid(String token);

    /**
     * Creates a technical resource that accepted an invitation from {@link cr.talent.model.Invitation}.
     * @param nickname the technical resource nickname.
     * @param password the technical resource password.
     * @param token the invitation token.
     * @return the newly created technical resource.
     */
    TechnicalResource acceptInvite(String nickname, String password, String token);

}
