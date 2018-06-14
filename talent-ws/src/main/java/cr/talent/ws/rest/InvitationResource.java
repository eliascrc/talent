package cr.talent.ws.rest;

import cr.talent.core.invitation.service.InvitationService;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.LimitOfInvitationsReachedException;
import cr.talent.support.exceptions.NotNullInviteLinkInOrganizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with two POST endpoints that handle the creation of invitations and invitation links.
 *
 * @author Elias Calderon
 */
@Component
@Scope("request")
@Path("/invitation")
public class InvitationResource {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private InvitationService invitationService;

    @POST
    @Path("/send")
    public Response stepFourSendInvite(@FormParam("email") String email) {
        if(StringUtils.isEmpty(email))
            return Response.status(Response.Status.BAD_REQUEST).build();

        TechnicalResource technicalResource = SecurityUtils.getLoggedInTechnicalResource();
        Organization organization = technicalResource.getOrganization();

        if (!technicalResource.isAdministrator())
            return Response.status(Response.Status.UNAUTHORIZED).build();

        try {
            this.invitationService.createInvitation(email, organization);
        } catch (LimitOfInvitationsReachedException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.ok().build();
    }

    @POST
    @Path("/createLink")
    public Response stepFourCreateInviteLink() {
        TechnicalResource technicalResource = SecurityUtils.getLoggedInTechnicalResource();
        Organization organization = technicalResource.getOrganization();

        if (!technicalResource.isAdministrator())
            return Response.status(Response.Status.UNAUTHORIZED).build();

        try {
            this.organizationService.createInviteLink(organization);
        } catch (NotNullInviteLinkInOrganizationException e) {
            // If there was already an invite link for the organization then it should return an OK with the link
            // so the catch does nothing.
        }

        return Response.ok().entity(organization.getInvitationLink()).build();
    }

}
