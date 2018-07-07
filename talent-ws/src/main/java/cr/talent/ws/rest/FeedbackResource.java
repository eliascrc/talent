package cr.talent.ws.rest;

import cr.talent.core.feedback.service.FeedbackService;
import cr.talent.core.project.service.ProjectService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Project;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with two POST endpoints that create kudos and warnings
 *
 * @author Josue Cubero
 */
@Component
@Scope("request")
@Path("/organization/feedback")
public class FeedbackResource {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private TechnicalResourceService technicalResourceService;

    @Autowired
    private FeedbackService feedbackService;

    /**
     * Receives the request for creating a warning for a technical resource.
     *
     * @param description the description of the warning.
     * @param observeeUsername the technical resource receiving the warning.
     * @param projectId the related project id.
     * @return 200 if the warning is correctly created,
     *         401 if the user is not authenticated,
     *         400 if any of the parameters are null or empty strings,
     *         404 if the observee or the related project don't not exist.
     *         409 if the observee or observer are not related to the project or the observer is not a lead or administrator.
     */
    @POST
    @Path("/create/warning")
    public Response createWarning(@FormParam("description") String description, @FormParam("observee") String observeeUsername,
                                  @FormParam("projectId") String projectId) {

        if (StringUtils.isEmpty(description) || StringUtils.isEmpty(observeeUsername) || StringUtils.isEmpty(projectId))
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        TechnicalResource observer = SecurityUtils.getLoggedInTechnicalResource();
        TechnicalResource observee = this.technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(
                observeeUsername, observer.getOrganization().getUniqueIdentifier());

        Project project = this.projectService.findById(projectId);

        if(observee == null || project == null)
            return Response.status(Response.Status.NOT_FOUND).build(); //The observee and the related project should exist

        if(!this.feedbackService.createWarning(observer, observee, project, description))
            return Response.status(Response.Status.CONFLICT).build(); //The observee and the related project should exist

        return Response.ok().build();
    }

}