package cr.talent.ws.rest;

import cr.talent.core.invitation.service.InvitationService;
import cr.talent.model.Invitation;
import cr.talent.model.TechnicalResource;
import cr.talent.support.exceptions.InvalidInvitationException;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource with one POST endpoint that handle the acceptance of an invitation
 * and one GET endpoint that validates an invitation token.
 *
 * @author Josue Cubero
 */
@Component
@Scope("request")
@Path("/acceptInvitation")
public class AcceptInviteResource {

    @Autowired
    private InvitationService invitationService;

    /**
     * Receives a request for validating an invite link token
     *
     * @param token the invite link token.
     * @return 200 if the token is still valid and Invitation JSON.
     *         400 if the token is invalid or not found.
     */
    @GET
    @Path("/validate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateRequestToken (@QueryParam("token") String token){
        if (StringUtils.isEmpty(token))
            return Response.status(Response.Status.BAD_REQUEST).build();

        Invitation invitation = this.invitationService.isTokenValid(token);
        if(invitation == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        String serializedInvitation =
                JSONSerializerBuilder.getInvitationSerializer().serialize(invitation);

        return Response.ok().entity(serializedInvitation).build();
    }

    /**
     * Creates a technical resource based on an invitation that was sent and validated.
     *
     * @param nickname the nickname of the resource performing the invitation acceptance.
     * @param password the password of the resource performing the invitation acceptance.
     * @param token the invitation token of the resource performing the invitation acceptance.
     * @return 200 if the supplied information is valid, 400 if any of the parameters is null or empty or if the
     *  			password is not valid.
     */
    @POST
    @Path("/accept")
    public Response performFirstStep(@FormParam("nickname") String nickname,
                                     @FormParam("password") String password,
                                     @QueryParam("token") String token) {

        if (StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password) || StringUtils.isEmpty(token))
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {

            TechnicalResource technicalResource = this.invitationService.acceptInvite(nickname, password, token);

            String serializedTechnicalResource =
                    JSONSerializerBuilder.getTechnicalResourceSerializer().serialize(technicalResource);

            return Response.ok().entity(serializedTechnicalResource).build();

        } catch (InvalidInvitationException e) {

            return Response.status(Response.Status.BAD_REQUEST).build();

        }

    }

}