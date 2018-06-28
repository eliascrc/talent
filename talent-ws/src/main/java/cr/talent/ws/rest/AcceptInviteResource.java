package cr.talent.ws.rest;

import cr.talent.core.invitation.service.InvitationService;
import cr.talent.model.TechnicalResource;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
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
     * @return 200 if the token is still valid.
     *         400 if the token is invalid or not found.
     */
    @GET
    @Path("/validate/")
    public Response validateRequestToken (@QueryParam("token") String token){
        if (StringUtils.isEmpty(token) || !this.invitationService.isTokenValid(token))
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok().build();
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
    @Path("/accept/")
    public Response performFirstStep(@FormParam("nickname") String nickname,
                                     @FormParam("password") String password,
                                     @QueryParam("token") String token) {

        if (StringUtils.isEmpty(nickname) || StringUtils.isEmpty(password) || StringUtils.isEmpty(token))
            return Response.status(Response.Status.BAD_REQUEST).build();

        if (!this.invitationService.isTokenValid(token))
            return Response.status(Response.Status.BAD_REQUEST).build();

        TechnicalResource technicalResource = this.invitationService.acceptInvite(nickname, password, token);

        String serializedTechnicalResource = JSONSerializerBuilder.getTechnicalResourceAuthenticationSerializer()
                .serialize(technicalResource);

        return Response.ok().entity(serializedTechnicalResource).build();

    }
}
