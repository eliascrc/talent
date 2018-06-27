package cr.talent.ws.rest;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource that handles operations related to project positions
 *
 * @author Daniel Montes de Oca
 */
@Component
@Scope("request")
@Path("/projectPosition")
public class ProjectPositionResource {

    /**
     * Endpoint for assigning project positions to technical resources
     * @param username the technical resource's username
     * @param projectPositionId the identifier of the project position
     * @return  400 if a parameter was left empty or if the id was of a project position of another organization
     *          403 if the logged in user lacks the permissions to assign the project position
     *          404 if no project position with that id was found
     *          200 if the project position was left empty
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/assign")
    public Response assignProjectPosition(@QueryParam("username") String username,
                                        @QueryParam("projectPositionId") String projectPositionId) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(projectPositionId))
            return Response.status(Response.Status.BAD_REQUEST).build();



        return null;
    }

}
