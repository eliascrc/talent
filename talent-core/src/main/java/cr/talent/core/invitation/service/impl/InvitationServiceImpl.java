package cr.talent.core.invitation.service.impl;

import cr.talent.core.email.invitationEmail.service.InvitationEmailService;
import cr.talent.core.invitation.dao.InvitationDao;
import cr.talent.core.invitation.service.InvitationService;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Invitation;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.LimitOfInvitationsReachedException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    private static final int LIMIT_OF_INVITATIONS = 10;

    @Autowired
    private InvitationDao invitationDao;

    @Autowired
    private InvitationEmailService invitationEmailService;

    public void init() {
        setCrudDao(this.invitationDao);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void createInvitation(String email, Organization organization) {

        Set<Invitation> invitations = organization.getInvitationsList();
        if (invitations.size() >= LIMIT_OF_INVITATIONS)
            throw new LimitOfInvitationsReachedException("The limit of invitations available for " +
                    "the organization has been reached.");

        Invitation invitation = this.invitationDao.findInvitationByEmail(email);
        if (invitation != null) {
            invitation.setValid(false);
            this.update(invitation);
        }

        Invitation newInvitation = new Invitation();
        newInvitation.setOrganization(organization);
        newInvitation.setEmail(email);

        String token = UUID.randomUUID().toString();
        newInvitation.setToken(token);
        newInvitation.setValid(true);

        this.create(newInvitation);

        this.invitationEmailService.sendInvitationEmail(email, newInvitation, organization.getUniqueIdentifier());

    }

}
