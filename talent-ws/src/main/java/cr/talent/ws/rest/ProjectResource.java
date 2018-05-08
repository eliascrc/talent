package cr.talent.ws.rest;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.project.service.ProjectService;
import cr.talent.model.Organization;
import cr.talent.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new project
 *
 * @author Elías Calderón
 */
@Component
@Scope("request")
@Path("/project")
public class ProjectResource {

    private ProjectService projectService;
    private OrganizationService organizationService;

    @Autowired
    public ProjectResource (ProjectService projectService, OrganizationService organizationService) {
        this.projectService = projectService;
        this.organizationService = organizationService;
    }

    /**
     * Receives the request for creating a new project within an organization.
     * If the unique identifier corresponds to an existing organization, it creates the project successfully.
     *
     * @param organizationUniqueIdentifier the organization's unique identifier.
     * @param name the name of the new project.
     * @return 200 if the project is correctly created, 400 if any of the parameters are null or empty strings,
     *          404 if the unique identifier does not belong to any organization.
     */
    @POST
    @Path("/create")
    public Response createProject(
            @FormParam("organizationUniqueIdentifier") String organizationUniqueIdentifier,
            @FormParam("name") String name) {

        if (organizationUniqueIdentifier == null || name == null
                || organizationUniqueIdentifier.equals("") || name.equals(""))
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
}
