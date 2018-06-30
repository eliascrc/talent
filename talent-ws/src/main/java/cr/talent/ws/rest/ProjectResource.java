package cr.talent.ws.rest;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.project.service.ProjectService;
import cr.talent.model.Organization;
import cr.talent.model.Project;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.NotProjectLeadException;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

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
     * @param name the name of the new project.
     * @return 200 if the project is correctly created,
     *          400 if any of the parameters are null or empty strings,
     *          404 if the unique identifier does not belong to any organization.
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
     * @return  200 if the project's information is correctly retrieved in JSON format,
     *          400 if the parameter is null or empty,
     *          404 if the id does not belong to any project,
     *          401 if no user is logged in.
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

    /**This endpoint receives a request to change a project status, it sets the endDate of the actual projectEvent and
     * creates a new projectEvent representing the new projec status.
     *
     * @param projectId the id of the project that will have its status changed.
     * @param newProjectStatus the new status of the project.
     * @return 400 if a parameter was left empty
     *         403 if the logged in user lacks the permissions to change the project state
     *         404 if no project with that id was found
     *         409 if the project has no active lead or if the project already is in that state.
     *         200 if the project state was succesfully changed.
     */
    @POST
    @Path("/changeStatus")
    public Response changeProjectStatus(
            @FormParam("projectId") String projectId,
            @FormParam("newProjectStatus") String newProjectStatus){

        if (StringUtils.isEmpty(projectId) || StringUtils.isEmpty(newProjectStatus))
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        Project project = this.projectService.findById(projectId);
        if (project == null)
            return Response.status(Response.Status.NOT_FOUND).entity("No project with this Id was found.").build();


        if (project.getcurrentState().getEventType().equals(newProjectStatus))
        {
            return Response.status(Response.Status.CONFLICT).entity("The status sent in the body is already the status of the project.").build();
        }

        TechnicalResource assigner = SecurityUtils.getLoggedInTechnicalResource();

        try {
            this.projectService.changeProjectState(project, newProjectStatus, assigner);
        } catch (NotProjectLeadException e) {
            return Response.status(Response.Status.FORBIDDEN).entity("The user creating the project is not the project lead.").build();
        } catch (ProjectWithoutLeadException e) {
            return Response.status(Response.Status.CONFLICT).entity("The project has no lead.").build();
        }

        return Response.ok().build();
    }

}
