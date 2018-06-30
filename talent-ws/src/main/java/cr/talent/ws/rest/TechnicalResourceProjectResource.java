package cr.talent.ws.rest;

import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with one POST endpoint that handles the query for a technical resource active project list.
 *
 * @author Josue Cubero
 */
@Component
@Scope("request")
@Path("technicalResource/project")
public class TechnicalResourceProjectResource {

    @Autowired
    ProjectPositionService projectPositionService;

    @Autowired
    TechnicalResourceService technicalResourceService;

    /**
     * Receives a request to query the active projects of a technical resource.
     *
     * @return 204 if the technical resource does not have any active project.
     *         401 if no user is logged in
     *         200 if the active projects are correctly queried and retrieved.
     */
    @GET
    @Path("/get")
    public Response getActiveProjects() {

        TechnicalResource lazySessionTechnicalResource = SecurityUtils.getLoggedInTechnicalResource();

        TechnicalResource technicalResource =
                this.technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(
                        lazySessionTechnicalResource.getUsername(),
                        lazySessionTechnicalResource.getOrganization().getUniqueIdentifier());

        try {
            String serializedList = this.projectPositionService.getTechnicalResourceActiveProjects(technicalResource);
            return Response.ok().entity(serializedList).build();
        } catch (NoActiveTechnicalResourceProjectException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
    }
}
