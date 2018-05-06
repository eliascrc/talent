package cr.talent.ws.rest;

import cr.talent.core.passwordResetRequest.service.PasswordResetRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Web services to reset a password.
 *
 * @author María José Cubero
 */

@Component
@Scope("request")
@Path("/forgotPassword")
public class PasswordResetRequestResource {

    @Autowired
    PasswordResetRequestService passwordResetRequestService;

    @POST
    @Path ("/sendEmail")
    public Response forgotPassword (@FormParam("email") String email){
        if(StringUtils.isEmpty(email))
            return Response.status(Response.Status.BAD_REQUEST).build();

        this.passwordResetRequestService.createPasswordRequestReset(email);
        return Response.ok().build();
    }

    @GET
    //@Path("/token{token:.*}")
    @Path("/new/")
    public Response validateRequestToken (@QueryParam("token") String token){
        if (!this.passwordResetRequestService.isTokenValid(token))
            return Response.status(Response.Status.BAD_REQUEST).build();

        return Response.ok().build();
    }

    @POST
    @Path("/reset/")
    public Response resetPassword (@QueryParam("token") String token, @FormParam("newPassword") String newPassword){
        Response response = Response.ok().build();
        if (StringUtils.isEmpty(newPassword))
            response=  Response.status(Response.Status.BAD_REQUEST).build();

        boolean passwordUpdated = this.passwordResetRequestService.resetPassword(token, newPassword);
        if (!passwordUpdated){
            response=  Response.status(Response.Status.BAD_REQUEST).build();
        }
        return response;
    }
}
