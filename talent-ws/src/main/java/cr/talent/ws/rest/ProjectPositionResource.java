package cr.talent.ws.rest;

import cr.talent.model.CapabilityLevel;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.NotProjectLeadException;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.support.exceptions.ProjectOfAnotherOrganizationException;
import cr.talent.model.Project;
import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.core.capabilityLevel.service.CapabilityLevelService;
import cr.talent.core.project.service.ProjectService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource that handles operations related to project positions
 *
 * @author Daniel Montes de Oca, Otto Mena Kikut
 */
@Component
@Scope("request")
@Path("/projectPosition")
public class ProjectPositionResource {

    @Autowired
    ProjectPositionService projectPositionService;

    @Autowired
    ProjectService projectService;

    @Autowired
    CapabilityLevelService capabilityLevelService;



    /**
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/create")
    public Response createProjectPosition(@QueryParam("projectId") String projectId,
                                          @QueryParam("capabilityLevelId") String capabilityLevelId,
                                          @QueryParam("totalHours") String totalHours) {

        if (StringUtils.isEmpty(projectId) || StringUtils.isEmpty(capabilityLevelId))
            return Response.status(Response.Status.BAD_REQUEST).build();

        Project project = projectService.findById(projectId);
        if (project == null)
            return Response.status(Response.Status.NOT_FOUND).entity("The project was not found").build();

        CapabilityLevel capabilityLevel = capabilityLevelService.findById(capabilityLevelId);
        if (capabilityLevel == null)
            return Response.status(Response.Status.NOT_FOUND).entity("The capability level was not found").build();

        TechnicalResource assigner = SecurityUtils.getLoggedInTechnicalResource();

        int totalHoursInt = Integer.parseInt(totalHours);

        try {
            this.projectPositionService.createProjectPosition(assigner, project, capabilityLevel, totalHoursInt);
            return Response.ok().build();
        } catch (NotProjectLeadException e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        } catch (ProjectWithoutLeadException e) {
            return Response.status(Response.Status.CONFLICT).build();
        } catch (ProjectOfAnotherOrganizationException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
