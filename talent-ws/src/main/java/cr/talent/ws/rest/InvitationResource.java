package cr.talent.ws.rest;

import cr.talent.core.invitation.service.InvitationService;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.AlreadyRegisteredUserException;
import cr.talent.support.exceptions.EmptyDestinationEmailException;
import cr.talent.support.exceptions.LimitOfInvitationsReachedException;
import cr.talent.support.exceptions.NotNullInviteLinkInOrganizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Resource with two POST endpoints that handle the creation of invitations and invitation links, and one
 * GET endpoint that retrieves the invite link.
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

    /**
     * Receives a request to send an invitation email. If the invitation passes all the verifications in the service
     * they're sent correctly.
     *
     * @param emails the destination emails
     * @return 400 if the list is empty, or if any email is empty
     * 401 if the user is not an administrator of the organization.
     * 409 if the limit of invitations and resources has been reached or an invitation is sent to a user that is
     * already registered in the organization.
     * 200 if the email was correctly sent.
     */
    @POST
    @Path("/send")
    public Response sendInvitation(@FormParam("emails") List<String> emails) {
        if(emails.size() == 0)
            return Response.status(Response.Status.BAD_REQUEST).build();

        TechnicalResource technicalResource = SecurityUtils.getLoggedInTechnicalResource();
        Organization organization = technicalResource.getOrganization();

        if (!technicalResource.isAdministrator())
            return Response.status(Response.Status.UNAUTHORIZED).build();

        try {

            this.invitationService.createInvitations(emails, organization);

        } catch (LimitOfInvitationsReachedException e) {
            return Response.status(Response.Status.CONFLICT).
                    entity("LimitOfInvitationsReached").build();

        } catch (AlreadyRegisteredUserException e) {
            return Response.status(Response.Status.CONFLICT).
                    entity("AlreadyRegisteredUser").build();

        } catch (EmptyDestinationEmailException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }

    /**
     * Receives a request for creating an invite link for an organization. If the user is the administrator
     * the link is correctly created.
     *
     * @return 401 if the user is not the administrator of the organization.
     * 200 if the link was correctly created or if it already exists. The invite link is also returned in the response.
     */
    @POST
    @Path("/inviteLink/create")
    public Response createInviteLink() {
        TechnicalResource technicalResource = SecurityUtils.getLoggedInTechnicalResource();
        Organization organization = technicalResource.getOrganization();

        if (!technicalResource.isAdministrator())
            return Response.status(Response.Status.UNAUTHORIZED).build();

        String inviteLink;
        try {
            inviteLink = this.organizationService.createInviteLink(organization);
        } catch (NotNullInviteLinkInOrganizationException e) {
            // If there was already an invite link for the organization just get it.
            inviteLink = organization.getInviteLink();
        }

        return Response.ok().entity(inviteLink).build();
    }

    /**
     * Receives a request for getting the invite link of an organization. If the user is the administrator
     * the link is correctly returned.
     *
     * @return 401 if the user is not the administrator of the organization.
     * 204 if there is no link to return.
     * 200 if the link was correctly created or if it already exists.
     */
    @GET
    @Path("/inviteLink")
    public Response getInviteLink() {
        TechnicalResource technicalResource = SecurityUtils.getLoggedInTechnicalResource();
        Organization organization = technicalResource.getOrganization();

        if (!technicalResource.isAdministrator())
            return Response.status(Response.Status.UNAUTHORIZED).build();

        if (organization.getInviteLink() == null)
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.ok().entity(organization.getInviteLink()).build();
    }

}
