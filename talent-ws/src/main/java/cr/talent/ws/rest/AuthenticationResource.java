package cr.talent.ws.rest;

import cr.talent.model.User;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.NoLoggedInUserException;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource with various endpoints to process requests regarding the authentication of users, such as login, logout,
 * the retrieval of the currently loggedin user's information and if there's a currently loggedin user.
 *
 * @author Josue Leon Sarkis
 */
@Component
@Scope("request")
@Path("")
public class AuthenticationResource {

    /**
     * Checks if there is a currently logged in user via SecurityUtils
     *
     * @return 200 with "true" if a user is currently logged in, "false" if not
     */
    @GET
    @Path("/loggedIn")
    @Produces(MediaType.TEXT_PLAIN)
    public Response isLoggedIn() {
        try{
            SecurityUtils.getLoggedInUser();
            return Response.ok().entity("true").build();

        } catch(NoLoggedInUserException e) {
            return Response.ok().entity("false").build();
        }
    }

    /**
     * Gets the currently loggedin User and serializes its information to JSON.
     *
     * @return 200 with the loggedin User information in JSON if there's a loggedin user,
     *          204 if there is no loggedin user
     */
    @GET
    @Path("/authenticated")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAuthenticatedUserInformation() {
        try{
            User user = SecurityUtils.getLoggedInUser();
            return Response.ok().entity(JSONSerializerBuilder.getUserSerializer().serialize(user)).build();

        } catch(NoLoggedInUserException e){
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }

}
