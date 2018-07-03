package cr.talent.ws.rest;

import cr.talent.core.project.service.ProjectService;
import cr.talent.model.Project;
import cr.talent.model.TechnicalResource;
import cr.talent.model.ProjectPosition;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.NotProjectLeadException;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.support.exceptions.StartDateBeforeEndDateException;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Date;
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

    /**
     * Receives the request for creating a new project within an organization.
     *
     * @param name the name of the new project.
     * @return 200 and project information in JSON format if the project is correctly created,
     *          400 if any of the parameters are null or empty strings,
     *          404 if the unique identifier does not belong to any organization.
     */
    @POST
    @Path("/create")
    public Response createProject(@FormParam("name") String name,
                                  @FormParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                  @FormParam("projectLead") String projectLead, @FormParam("description") String description) {

        if (StringUtils.isEmpty(name) || startDate == null || StringUtils.isEmpty(description)) //project lead is optional
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        TechnicalResource technicalResource = SecurityUtils.getLoggedInTechnicalResource();

        Project project = this.projectService.createProject(name, startDate, projectLead, description, technicalResource);

        return Response.ok().entity(JSONSerializerBuilder.getProjectInformationSerializer().serialize(project)).build();
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

    /**This endpoint receives a request to change a project status, it sets the endDate of the actual projectEvent and
     * creates a new projectEvent representing the new projec status.
     *
     * @param projectId the id of the project that will have its status changed.
     * @param startDate the start date of the new projectEvent
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
            @FormParam("newProjectStatus") String newProjectStatus,
            @FormParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate){

        if (StringUtils.isEmpty(projectId) || StringUtils.isEmpty(newProjectStatus) || startDate == null)
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        if(!(newProjectStatus.equals("ON_HOLD") || newProjectStatus.equals("START") || newProjectStatus.equals("END"))){
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid status.").build();
        }

        Project project = this.projectService.findById(projectId);
        if (project == null)
            return Response.status(Response.Status.NOT_FOUND).entity("No project with this Id was found.").build();

        if (project.getcurrentState().getEventType().name().equals(newProjectStatus))
        {
            return Response.status(Response.Status.CONFLICT).entity("The status sent in the body is already the status of the project.").build();
        }

        TechnicalResource assigner = SecurityUtils.getLoggedInTechnicalResource();

        try {
            this.projectService.changeProjectState(project, newProjectStatus, assigner, startDate);
        } catch (NotProjectLeadException e) {
            return Response.status(Response.Status.FORBIDDEN).entity("The user creating the project is not the project lead.").build();
        } catch (ProjectWithoutLeadException e) {
            return Response.status(Response.Status.CONFLICT).entity("The project has no lead.").build();
        } catch (StartDateBeforeEndDateException e){
            return Response.status(Response.Status.CONFLICT).entity("The new date is before the startDate of the current projectEvent").build();
        }

        return Response.ok().build();
    }

    /**
     * Returns every position in the project, along with each position's capability and holder history.
     *
     * @param projectId the unique identifier of the Project entity, inherited from BasicEntity
     * @return 400 if the string is either null or empty
     * 404 if the project does not exist in the logged user's organization
     * 204 if the project has no positions
     * 200 of the information is returned successfully
     */
    @GET
    @Path("/getHistory")
    public Response getHistory(@QueryParam("projectId") String projectId) {
        if (org.apache.commons.lang.StringUtils.isEmpty(projectId))
            return Response.status(Response.Status.BAD_REQUEST).build();

        Project project = projectService.findById(projectId);

        // Check if project exists
        if (project == null || !project.getOrganization().equals(SecurityUtils.getLoggedInTechnicalResource().getOrganization()))
            return Response.status(Response.Status.NOT_FOUND).entity("Project not found.").build();

        // Check if there is any content to return
        Set<ProjectPosition> projectPositions = project.getProjectPositions();
        if (projectPositions == null || projectPositions.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build();

        return Response.status(Response.Status.OK)
                .entity(JSONSerializerBuilder.getProjectPositionSerializer().serialize(projectPositions)).build();
    }
}
