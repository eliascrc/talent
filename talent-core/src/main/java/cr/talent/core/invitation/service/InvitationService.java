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
     * @param emails the emails of the resources to invite
     * @param organization the organization to relate the invitations with.
     */
    void createInvitations(List<String> emails, Organization organization);

    /**
     * Validates if a token from {@link cr.talent.model.Invitation} is still active.
     * @param token the token to be validated.
     * @return true if it is valid, false if not.
     */
    boolean isTokenValid(String token);

    /**
     * Creates a technical resource that accepted an invitation from {@link cr.talent.model.Invitation}.
     * @param firstName the technical resource first name.
     * @param lastName the technical resource last name.
     * @param password the technical resource password.
     * @param token the invitation token.
     * @return the newly created technical resource.
     */
    TechnicalResource acceptInvite(String firstName, String lastName, String password, String token);

}
