package cr.talent.core.invitation.service.impl;

import cr.talent.core.email.invitationEmail.service.InvitationEmailService;
import cr.talent.core.invitation.dao.InvitationDao;
import cr.talent.core.invitation.service.InvitationService;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Invitation;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.AlreadyRegisteredUserException;
import cr.talent.support.exceptions.EmptyDestinationEmailException;
import cr.talent.support.exceptions.LimitOfInvitationsReachedException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author Elias Calderon
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
     * @see cr.talent.core.invitation.service.InvitationService#createInvitations(List, Organization)
     */
    @Override
    @SuppressWarnings("unchecked")
    public void createInvitations(List<String> emails, Organization organization) {
        final String invitationLimitReachedMsg = "The limit of invitations available for the organization has been reached.";
        final String alreadyRegisteredUserMsg = "The email for the invitation is already registered in the organization.";
        final String emptyDestinationEmailMsg = "One of the emails passed to the service is empty.";

        List<Invitation> invitationsToSend = new ArrayList<>();

        for (String email : emails) {

            if (StringUtils.isEmpty(email)) {
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

                Set<Invitation> invitations = this.organizationService.getValidInvitations(organization);
                int totalInvitationsAndResources = invitations.size() + organization.getResources().size();

                if (totalInvitationsAndResources >= LIMIT_OF_INVITATIONS)
                    throw new LimitOfInvitationsReachedException(invitationLimitReachedMsg);

            }

            if (this.technicalResourceService.getTechnicalResourceByUsername(email) != null)
                throw new AlreadyRegisteredUserException(alreadyRegisteredUserMsg);

            Invitation newInvitation = new Invitation();
            newInvitation.setOrganization(organization);
            newInvitation.setEmail(email);

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

}
