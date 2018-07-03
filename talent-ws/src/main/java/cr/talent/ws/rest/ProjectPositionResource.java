package cr.talent.ws.rest;

import cr.talent.model.CapabilityLevel;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.NotProjectLeadException;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.support.exceptions.ProjectOfAnotherOrganizationException;
import cr.talent.support.exceptions.ProjectPositionAlreadyExistsException;;
import cr.talent.model.Project;
import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.core.capabilityLevel.service.CapabilityLevelService;
import cr.talent.core.project.service.ProjectService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import cr.talent.core.project.service.ProjectService;
import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.*;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.*;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import flexjson.JSONSerializer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.xml.crypto.Data;
import java.text.*;
import java.util.Date;
import java.util.Set;


/**
 * Resource that handles operations related to project positions
 *
 * @author Daniel Montes de Oca, Otto Mena, Fabián Roberto Leandro
 */
@Component
@Scope("request")
@Path("/projectPosition")
public class ProjectPositionResource {

    @Autowired
    ProjectPositionService projectPositionService;

    @Autowired
    ProjectPositionHolderService projectPositionHolderService;

    @Autowired
    TechnicalResourceService technicalResourceService;

    @Autowired
    ProjectService projectService;

    @Autowired
    CapabilityLevelService capabilityLevelService;


    /**
     *
     * @param projectId The id of the project that will have the new project position.
     * @param capabilityLevelId The id of the capability level of the new project position.
     * @param totalHours The hours that the project position will have assigned.
     * @return 400 if a parameter was left empty or if the id was of a project of another organization
     *         403 if the logged in user lacks the permissions to create the project position
     *         404 if no project or capability level with that id was found
     *         409 if the project has no active lead or if the project position already exists (same project and capabilityLevel)
     *         200 if the project position was created correctly
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/create")
    public Response createProjectPosition(@FormParam("projectId") String projectId,
                                          @FormParam("capabilityLevelId") String capabilityLevelId,
                                          @FormParam("totalHours") String totalHours) {

        if (StringUtils.isEmpty(projectId) || StringUtils.isEmpty(capabilityLevelId) || StringUtils.isEmpty(totalHours))
            return Response.status(Response.Status.BAD_REQUEST).build();

        Project project = projectService.findById(projectId);
        if (project == null)
            return Response.status(Response.Status.NOT_FOUND).entity("The project was not found").build();

        CapabilityLevel capabilityLevel = capabilityLevelService.findById(capabilityLevelId);
        if (capabilityLevel == null)
            return Response.status(Response.Status.NOT_FOUND).entity("The capability level was not found").build();

        TechnicalResource assigner = SecurityUtils.getLoggedInTechnicalResource();

        int totalHoursInt;
        try {
            totalHoursInt = Integer.parseInt(totalHours);
        } catch (NumberFormatException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("The parameter totalHours not in correct format.").build();
        }

        try {
            this.projectPositionService.createProjectPosition(assigner, project, capabilityLevel, totalHoursInt);
            return Response.ok().build();
        } catch (NotProjectLeadException e) {
            return Response.status(Response.Status.FORBIDDEN).entity("The user creating the project position is not the project lead.").build();
        } catch (ProjectWithoutLeadException e) {
            return Response.status(Response.Status.CONFLICT).entity("The project has no lead.").build();
        } catch (ProjectOfAnotherOrganizationException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("The capability level and the project do not match.").build();
        } catch (ProjectPositionAlreadyExistsException e) {
            return Response.status(Response.Status.CONFLICT).entity("The project position already exists.").build();
        }
    }


    /**
     * Endpoint for assigning project positions to technical resources
     *
     * @param username          the technical resource's username
     * @param projectPositionId the identifier of the project position
     * @return 400 if a parameter was left empty or if the id was of a project position of another organization
     * or if the end date is not valid
     * 403 if the logged in user lacks the permissions to assign the project position
     * 404 if no project position with that id was not found or if there is no technical resource with the
     * assignee's username
     * 409 if the project has no active lead
     * 200 if the project position was assigned correctly
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/assign")
    public Response assignProjectPosition(@FormParam("username") String username,
                                          @FormParam("projectPositionId") String projectPositionId,
                                          @FormParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                          @FormParam("assignedHours") int assignedHours,
                                          @FormParam("active") boolean active) {

        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(projectPositionId) || startDate == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        ProjectPosition projectPosition = projectPositionService.findById(projectPositionId);
        if (projectPosition == null)
            return Response.status(Response.Status.NOT_FOUND).entity("Project position not found").build();

        TechnicalResource assigner = SecurityUtils.getLoggedInTechnicalResource();
        TechnicalResource assignee = technicalResourceService
                .getTechnicalResourceByUsernameAndOrganizationIdentifier(username,
                        assigner.getOrganization().getUniqueIdentifier());

        if (assignee == null)
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Resource with username " + username + " was not found").build();


        try {
            this.projectPositionHolderService.assignProjectPosition(assigner, assignee, projectPosition, startDate,
                    assignedHours, active);
            return Response.ok().build();
        } catch (NotProjectLeadException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (ProjectPositionOfAnotherOrganizationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (ProjectWithoutLeadException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    /**Endpoint for deleting a project position
     *
     * @param projectPositionId the id of the position to be deleted
     * @return 400 if a parameter was left empty
     *         403 if the logged in user lacks the permissions to delete the project position
     *         404 if no project position with that id was found
     *         409 if the project has no active lead
     *         200 if the project position was deleted correctly
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/delete")
    public Response deleteProjectPosition(@FormParam("projectPositionId") String projectPositionId) {

        if (StringUtils.isEmpty(projectPositionId))
            return Response.status(Response.Status.BAD_REQUEST).build();

        ProjectPosition projectPosition = projectPositionService.findById(projectPositionId);
        if (projectPosition == null)
            return Response.status(Response.Status.NOT_FOUND).entity("Project position not found").build();

        TechnicalResource lead = SecurityUtils.getLoggedInTechnicalResource();

        try {
            this.projectPositionService.deleteProjectPosition(lead, projectPosition);
            return Response.ok().build();
        } catch (NotProjectLeadException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (ProjectWithoutLeadException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

    /**
     * Used for unassigning a project position of a technical resource
     *
     * @param projectPositionHolderId the id of the project position that will be unassigned
     * @param endDate                 the date that the resource stopped working on the project position
     * @return 400 if a parameter was left empty or if the id was of a project position of another organization
     * or if the start date is not valid
     * 403 if the logged in user lacks the permissions to unassign the project position
     * 404 if no project position holder with that id was not found
     * 409 if the project has no active lead or if the provided end date is before the start date
     * 200 if the project position was unassigned correctly
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/unassign")
    public Response unassignProjectPosition(@FormParam("projectPositionHolderId") String projectPositionHolderId,
                                            @FormParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        if (StringUtils.isEmpty(projectPositionHolderId) || endDate == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        ProjectPositionHolder projectPositionHolder = projectPositionHolderService.findById(projectPositionHolderId);
        if (projectPositionHolder == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        TechnicalResource unassigner = SecurityUtils.getLoggedInTechnicalResource();

        try {
            projectPositionHolderService.unassignProjectPosition(projectPositionHolder, unassigner, endDate);
            return Response.ok().build();
        } catch (NotProjectLeadException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (ProjectPositionOfAnotherOrganizationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (ProjectWithoutLeadException e) {
            return Response.status(Response.Status.CONFLICT).entity("The project has no active lead").build();
        } catch (StartDateBeforeEndDateException e) {
            return Response.status(Response.Status.CONFLICT).entity("End date is before the start date").build();
        }

    }

    /**
     * Used for unassigning a project position of a technical resource
     *
     * @param projectPositionHolderId the id of the project position that will be unassigned
     * @return 400 if a parameter was left empty or if the id was of a project position of another organization
     * or if the start date is not valid
     * 403 if the logged in user lacks the permissions to unassign the project position
     * 404 if no project position holder with that id was not found
     * 409 if the project has no active lead
     * 200 if the project position was unassigned correctly
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/unassignBeforeStart")
    public Response unassignBeforeProjectStart(@FormParam("projectPositionHolderId") String projectPositionHolderId,
                                               @FormParam("currentDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date currentDate) {

        if (StringUtils.isEmpty(projectPositionHolderId) || currentDate == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        ProjectPositionHolder projectPositionHolder = projectPositionHolderService.findById(projectPositionHolderId);
        if (projectPositionHolder == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        TechnicalResource unassigner = SecurityUtils.getLoggedInTechnicalResource();

        try {
            projectPositionHolderService.unassignProjectPositionBeforeProjectStart(projectPositionHolder, unassigner, currentDate);
            return Response.ok().build();
        } catch (NotProjectLeadException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (ProjectPositionOfAnotherOrganizationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        } catch (ProjectWithoutLeadException e) {
            return Response.status(Response.Status.CONFLICT).entity("The project has no active lead").build();
        } catch (ProjectAlreadyStartedException e) {
            return Response.status(Response.Status.CONFLICT).entity("The project already started").build();
        }

    }

    /**
     * Endpoint to obtain a technical resource's project position from their username and a project
     * @param technicalResourceEmail the email (username) of the technical resource whose project position will be returned
     * @param projectId the unique identifier of the project from which the position will be returned, inherited from BasicEntity
     * @return 400 either string is null or empty
     *         404 with NonExistentTechnicalResource if specified technical resource does not exist within the
     *          logged user's organization
     *         404 with TechnicalResourceHasNoPositionInProject with the specified technical resource does not have a
     *          position in the specified project
     *         200 if the project position is returned successfully
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/get")
    public Response getProjectPosition(@QueryParam("technicalResource") String technicalResourceEmail,
                                       @QueryParam("projectId") String projectId) {
        if (StringUtils.isEmpty(technicalResourceEmail) || StringUtils.isEmpty(projectId))
            return Response.status(Response.Status.BAD_REQUEST).build();

        // Get the desired user using the received email and the logged in user's organization
        TechnicalResource technicalResource = technicalResourceService
                .getTechnicalResourceByUsernameAndOrganizationIdentifier(technicalResourceEmail,
                        SecurityUtils.getLoggedInTechnicalResource().getOrganization().getUniqueIdentifier());
        if (technicalResource == null)
            return Response.status(Response.Status.NOT_FOUND).entity("NonExistentTechnicalResource").build();

        // Get the project from the received id
        Project project = projectService.findById(projectId);
        if(project == null)
            return Response.status(Response.Status.NOT_FOUND).entity("NonExistentProject").build();

        ProjectPositionHolder projectPositionHolder = this.projectPositionHolderService
                .getProjectPositionByProjectAndTechnicalResource(project,technicalResource);

        if(projectPositionHolder == null)
            return Response.status(Response.Status.NOT_FOUND).entity("ProjectPositionIsNotAssignedToResource").build();

        return Response.status(Response.Status.OK).entity(JSONSerializerBuilder.getProjectPositionHolderSerializer().serialize(projectPositionHolder)).build();
    }
}
