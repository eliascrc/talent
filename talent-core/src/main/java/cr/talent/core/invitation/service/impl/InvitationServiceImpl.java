package cr.talent.core.invitation.service.impl;

import cr.talent.core.email.invitationEmail.service.InvitationEmailService;
import cr.talent.core.invitation.dao.InvitationDao;
import cr.talent.core.invitation.service.InvitationService;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Invitation;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.model.User;
import cr.talent.support.exceptions.AlreadyRegisteredUserException;
import cr.talent.support.exceptions.EmptyDestinationEmailException;
import cr.talent.support.exceptions.LimitOfInvitationsReachedException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Default implementation of the {@link cr.talent.core.invitation.service.InvitationService}
 *
 * @author Elias Calderon, Josue Cubero
 */

@Service("invitationService")
@Transactional
public class InvitationServiceImpl extends CrudServiceImpl<Invitation, String> implements InvitationService {

    /**
     * Limit of invitations, currently the free plan is assumed for all organizations.
     */
    private static final int LIMIT_OF_INVITATIONS = 10;

    @Autowired
    private InvitationDao invitationDao;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private TechnicalResourceService technicalResourceService;

    @Autowired
    private InvitationEmailService invitationEmailService;

    public void init() {
        setCrudDao(this.invitationDao);
    }

    /**
     * @see cr.talent.core.invitation.service.InvitationService#createInvitations(String, Organization)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void createInvitations(String invitations, Organization organization) {
        final String invitationLimitReachedMsg = "The limit of invitations available for the organization has been reached.";
        final String alreadyRegisteredUserMsg = "The email for the invitation is already registered in the organization.";
        final String emptyDestinationEmailMsg = "One of the emails passed to the service is empty.";

        List<Invitation> invitationsToSend = new ArrayList<>();

        JSONObject invitationsJSON = new JSONObject(invitations);
        JSONArray invitationsList = invitationsJSON.getJSONArray("invitations");
        int invitationsListSize = invitationsList.length();
        String email, firstName, lastName;

        for (int i = 0; i < invitationsListSize; i++) {

            email = invitationsList.getJSONObject(i).getString("email");
            firstName = invitationsList.getJSONObject(i).getString("firstName");
            lastName = invitationsList.getJSONObject(i).getString("lastName");

            if (StringUtils.isEmpty(email) || StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName)) {
                throw new EmptyDestinationEmailException(emptyDestinationEmailMsg);
            }

            Invitation invitation = this.invitationDao.findInvitationByEmail(email);
            if (invitation != null) {
                // If there was already an invitation, invalidate the previous one.
                invitation.setValid(false);
                this.update(invitation);
            } else {
                // If the email was not already registered for an invitation, it's a brand new invitation
                // so the system needs to check if the limit has been reached

                // Get the appropriate instantiation of organization from the service
                organization = this.organizationService.getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier());

                Set<Invitation> organizationInvitations = this.organizationService.getValidInvitations(organization);
                int totalInvitationsAndResources = organizationInvitations.size() + organization.getResources().size();

                if (totalInvitationsAndResources >= LIMIT_OF_INVITATIONS)
                    throw new LimitOfInvitationsReachedException(invitationLimitReachedMsg);

            }

            if (this.technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(
                    email, organization.getUniqueIdentifier()) != null)
                throw new AlreadyRegisteredUserException(alreadyRegisteredUserMsg);

            Invitation newInvitation = new Invitation();
            newInvitation.setOrganization(organization);
            newInvitation.setEmail(email);
            newInvitation.setFirstName(firstName);
            newInvitation.setLastName(lastName);

            String token = UUID.randomUUID().toString();
            newInvitation.setToken(token);
            newInvitation.setValid(true);

            this.create(newInvitation);

            invitationsToSend.add(newInvitation);
        }

        //Sends the invitations until all of them are validated and stored.
        for (Invitation invitationToSend : invitationsToSend) {
            this.invitationEmailService.sendInvitationEmail(invitationToSend, organization.getUniqueIdentifier());
        }
    }

    /**
     * @see cr.talent.core.invitation.service.InvitationService#isTokenValid(String)
     */
    @Override
    public boolean isTokenValid(String token) {
        Invitation invitation = this.invitationDao.findInvitationByToken(token);
        return invitation != null && invitation.isValid();
    }

    /**
     * @see cr.talent.core.invitation.service.InvitationService#acceptInvite(String, String, String)
     */
    @Override
    public TechnicalResource acceptInvite(String nickname, String password, String token) {

        Invitation invitation = this.invitationDao.findInvitationByToken(token); // get the invitation

        TechnicalResource technicalResource = new TechnicalResource(); // build the technical resource
        technicalResource.setFirstName(invitation.getFirstName());
        technicalResource.setLastName(invitation.getLastName());
        technicalResource.setUsername(invitation.getEmail());
        technicalResource.setNickname(nickname);
        technicalResource.setPassword(password);
        technicalResource.setStatus(User.Status.ACTIVE);
        technicalResource.setAdministrator(false); // because they are signing up while creating an organization
        technicalResource.setOrganization(invitation.getOrganization());

        this.technicalResourceService.create(technicalResource);

        invitation.setValid(false); // invalidate the invitation
        this.invitationDao.update(invitation);

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(technicalResource, null, technicalResource.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication); // log the new technical resource

        return technicalResource;
    }

}
