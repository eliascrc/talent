package cr.talent.ws.rest;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.project.service.ProjectService;
import cr.talent.model.Organization;
import cr.talent.model.Project;
import cr.talent.model.ProjectPosition;
import cr.talent.support.SecurityUtils;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Resource with two POST endpoints that create a new project and retrieve a project information.
 *
 * @author Elías Calderón, Josue Cubero
 */
@Component
@Scope("request")
@Path("/organization/project")
public class ProjectResource {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private OrganizationService organizationService;

    /**
     * Receives the request for creating a new project within an organization.
     * If the unique identifier corresponds to an existing organization, it creates the project successfully.
     *
     * @param organizationUniqueIdentifier the organization's unique identifier.
     * @param name                         the name of the new project.
     * @return 200 if the project is correctly created,
     * 400 if any of the parameters are null or empty strings,
     * 404 if the unique identifier does not belong to any organization.
     */
    @POST
    @Path("/create")
    public Response createProject(
            @FormParam("organizationUniqueIdentifier") String organizationUniqueIdentifier,
            @FormParam("name") String name) {

        if (StringUtils.isEmpty(organizationUniqueIdentifier) || StringUtils.isEmpty(name))
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        Organization organization = this.organizationService.getOrganizationByUniqueIdentifier(organizationUniqueIdentifier);
        if (organization == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        Project project = new Project();
        project.setOrganization(organization);
        project.setName(name);
        this.projectService.create(project);

        return Response.ok().build();
    }

    /**
     * Receives the request for retrieving a project's information.
     *
     * @param projectId the project's id.
     * @return 200 if the project's information is correctly retrieved in JSON format,
     * 400 if the parameter is null or empty,
     * 404 if the id does not belong to any project,
     * 401 if no user is logged in.
     */
    @POST
    @Path("/get")
    public Response getProject(
            @FormParam("projectId") String projectId) {

        if (StringUtils.isEmpty(projectId))
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty


        Project project = this.projectService.findById(projectId);
        if (project == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().entity(JSONSerializerBuilder.getProjectInformationSerializer().serialize(project)).build();
    }

    /**
     * Returns every position in the project, along with each position's capability and holder history.
     *
     * @param projectId the unique identifier of the Project entity, inherited from BasicEntity
     * @return 400 if the string is either null or empty
     * 404 if the project does not exist
     * 403 if the project exists but is not in the logged user's organization
     * 204 if the project has no positions
     * 200 of the information is returned successfully
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getHistory")
    public Response getHistory(@QueryParam("projectId") String projectId) {
        if (org.apache.commons.lang.StringUtils.isEmpty(projectId))
            return Response.status(Response.Status.BAD_REQUEST).build();

        Project project = projectService.findById(projectId);

        // Check if project exists
        if (project == null)
            return Response.status(Response.Status.NOT_FOUND).entity("Project not found.").build();

        // Check if user is authorized to get the information (they should be in the organization as the project)
        if (!project.getOrganization().equals(SecurityUtils.getLoggedInTechnicalResource().getOrganization()))
            return Response.status(Response.Status.FORBIDDEN).build();

        // Check if there is any content to return
        Set<ProjectPosition> projectPositions = project.getProjectPositions();
        if (projectPositions == null || projectPositions.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.status(Response.Status.OK)
                .entity(JSONSerializerBuilder.getProjectPositionSerializer().serialize(projectPositions)).build();
    }
}
