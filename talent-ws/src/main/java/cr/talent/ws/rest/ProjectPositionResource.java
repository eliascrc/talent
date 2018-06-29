package cr.talent.ws.rest;

import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.ProjectPosition;
import cr.talent.model.ProjectPositionHolder;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.NotProjectLeadException;
import cr.talent.support.exceptions.ProjectPositionOfAnotherOrganizationException;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.support.exceptions.StartDateBeforeEndDateException;
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

/**
 * Resource that handles operations related to project positions
 *
 * @author Daniel Montes de Oca
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

    /**
     * Endpoint for assigning project positions to technical resources
     * @param username the technical resource's username
     * @param projectPositionId the identifier of the project position
     * @return  400 if a parameter was left empty or if the id was of a project position of another organization
     *          403 if the logged in user lacks the permissions to assign the project position
     *          404 if no project position with that id was found or if there is no technical resource with the assignee's
     *          username
     *          409 if the project has no active lead
     *          200 if the project position was assigned correctly
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

    /**
     * Used for unassigning a project position of a technical resource
     * @param projectPositionHolderId the id of the project position that will be unassigned
     * @param endDate the date that the resource stopped working on the project position
     * @return
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/unassign")
    public Response unassignProjectPosition(@FormParam("projectPositionHolderId") String projectPositionHolderId,
                                            @FormParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

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

}
