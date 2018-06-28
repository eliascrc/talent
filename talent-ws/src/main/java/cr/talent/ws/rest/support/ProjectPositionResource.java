package cr.talent.ws.rest.support;

import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

/**
 * Resource that handles operations related to project positions
 *
 * @author Fabián Roberto Leandro
 */
@Component
@Scope("request")
@Path("/projectPosition")
public class ProjectPositionResource {

    @Autowired
    TechnicalResourceService technicalResourceService;

    /**
     * Endpoint to obtain a technical resource's project position from their username and a project
     */
    @POST
    @Path("/get")
    public Response getProjectPosition(@QueryParam("technicalResource") String technicalResourceEmail,
                                       @QueryParam("projectName") String project) {

    }
}
