package cr.talent.ws.rest;

import cr.talent.core.project.service.ProjectService;
import cr.talent.model.Project;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.Date;

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
     * If the unique identifier corresponds to an existing organization, it creates the project successfully.
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

        System.out.println(name+startDate+projectLead+description);
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
}
