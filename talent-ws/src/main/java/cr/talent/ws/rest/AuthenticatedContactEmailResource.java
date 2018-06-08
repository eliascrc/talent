package cr.talent.ws.rest;

import cr.talent.core.contactUsNotification.authenticatedContactUsNotification.service.AuthenticatedContactUsNotificationService;
import cr.talent.core.email.contactEmail.authenticatedContactEmail.service.AuthenticatedContactEmailService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.*;
import cr.talent.support.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new Contact Us request for an authenticated user.
 *
 * @author Fabián Roberto Leandro
 */
@Component
@Scope("request")
@Path("/contactUs/authenticated")
public class AuthenticatedContactEmailResource {

    @Autowired
    AuthenticatedContactUsNotificationService authenticatedContactUsNotificationService;

    @Autowired
    AuthenticatedContactEmailService authenticatedContactEmailService;

    /**
     * Receives the request to create a new contact email.
     * @param issueType the issue type selected by the user from a drop down box, represented in the model by an enum.
     * @param issue the content of the message left by the user.
     *
     * @return  200 if the contactEmail is correctly created,
     *          400 if the issue or issueType is empty or null, or if the issue type is not valid.
     */
    @POST
    public Response authenticatedContactUs(
            @FormParam("issueType") String issueType,
            @FormParam("issue") String issue) {

        // Check if the request is valid
        ContactUsIssueType issueTypeEnum;
        if(issue == null || issue.equals("") || issueType == null || issueType.equals("")
                || (issueTypeEnum = ContactUsIssueType.fromString(issueType)) == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        // Get the logged in user
        TechnicalResource loggedUser = (TechnicalResource) SecurityUtils.getLoggedInUser();

        // Create the notification
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        contactUsNotification.setTechnicalResource(loggedUser);
        contactUsNotification.setIssue(issue);
        contactUsNotification.setIssueType(issueTypeEnum);
        // Persist the notification
        authenticatedContactUsNotificationService.create(contactUsNotification);

        // Create the email object
        AuthenticatedContactEmail authenticatedContactEmail= new AuthenticatedContactEmail();
        authenticatedContactEmail.setUser(loggedUser);
        authenticatedContactEmail.setTo(loggedUser.getUsername());
        authenticatedContactEmail.setContent(issue);
        authenticatedContactEmail.setIssueType(issueTypeEnum);
        // Send the email
        authenticatedContactEmailService.sendAuthenticatedContactEmail(authenticatedContactEmail);

        return Response.status(Response.Status.OK).build();
    }
}
