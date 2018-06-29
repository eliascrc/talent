package cr.talent.ws.rest;

import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Set;

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
     * @param technicalResourceEmail the email (username) of the technical resource whose projeect position will be returned
     * @param projectName the name of the project from which the position will be returned
     * @return 400 either string is null or empty
     *         404 with NonExistentTechnicalResource if specified technical resource does not exist within the
     *          logged user's organization
     *         404 with TechnicalResourceHasNoPositionInProject with the specified technical resource does not have a
     *          position in the specified project
     *         200 if the project position is returned successfully
     */
    @GET
    @Path("/get")
    public Response getProjectPosition(@QueryParam("technicalResource") String technicalResourceEmail,
                                       @QueryParam("project") String projectName) {
        if (StringUtils.isEmpty(technicalResourceEmail) || StringUtils.isEmpty(projectName))
            return Response.status(Response.Status.BAD_REQUEST).build();

        // Get the desired user using the received email and the logged in user's organization
        TechnicalResource technicalResource = technicalResourceService
                .getTechnicalResourceByUsernameAndOrganizationIdentifier(technicalResourceEmail,
                        SecurityUtils.getLoggedInTechnicalResource().getOrganization().getUniqueIdentifier());

        if (technicalResource == null)
            return Response.status(Response.Status.NOT_FOUND).entity("NonExistentTechnicalResource").build();

        Set<ProjectPositionHolder> projectPositionHolders = technicalResource.getProjectPositions();
        if (projectPositionHolders != null)
            for (ProjectPositionHolder projectPositionHolder : projectPositionHolders) {
                if (projectPositionHolder.getProjectPosition().getProject().getName().equals(projectName)) {
                    return Response.status(Response.Status.OK)
                            .entity(JSONSerializerBuilder.getProjectPositionHolderSerializer().serialize(projectPositionHolder))
                            .build();
                }
            }

        return Response.status(Response.Status.NOT_FOUND).entity("TechnicalResourceHasNoPositionInProject").build();
    }
}
