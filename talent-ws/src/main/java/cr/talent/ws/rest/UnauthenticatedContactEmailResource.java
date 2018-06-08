package cr.talent.ws.rest;

import cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.service.UnauthenticatedContactUsNotificationService;
import cr.talent.core.email.contactEmail.unauthenticatedContactEmail.service.UnauthenticatedContactEmailService;
import cr.talent.model.AuthenticatedContactUsNotification;
import cr.talent.model.ContactUsIssueType;
import cr.talent.model.UnauthenticatedContactEmail;
import cr.talent.model.UnauthenticatedContactUsNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new Contact Us request for an Unauthenticated user.
 *
 * @author Fabián Roberto Leandro
 */
@Component
@Scope("request")
@Path("/contactUs/unauthenticated")
public class UnauthenticatedContactEmailResource {

    @Autowired
    UnauthenticatedContactUsNotificationService unauthenticatedContactUsNotificationService;

    @Autowired
    UnauthenticatedContactEmailService unauthenticatedContactEmailService;

    /**
     * Receives a Contact Us request, sends a copy of the message to the user and stores a notification in the DB.
     * @param firstName the first name of the person creating the Contact Us request.
     * @param lastName the last name of the person creating the Contact Us request.
     * @param email the email of the person creating the Contact Us request, where the reply will be sent to.
     * @param issueType the issue type selected by the user from a drop down box, represented in the model by an enum.
     * @param issue the content of the message left by the user.
     *
     * @return  200 if the notification is created and the email is sent,
     *          400 if the email, first name, last name, issue or issueType is empty or null, or if the issue type is not valid.
     */
    @POST
    public Response unauthenticatedContactUs(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("issueType") String issueType,
            @FormParam("issue") String issue) {

        // Check if the request is valid
        ContactUsIssueType issueTypeEnum;
        if(firstName == null || firstName.equals("") || lastName == null || lastName.equals("")
                || email == null || email.equals("")|| issue == null || issue.equals("")
                || issueType == null || issueType.equals("")
                || (issueTypeEnum = ContactUsIssueType.fromString(issueType)) == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        // Create the notification
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();
        contactUsNotification.setFirstName(firstName);
        contactUsNotification.setLastName(lastName);
        contactUsNotification.setEmail(email);
        contactUsNotification.setIssue(issue);
        contactUsNotification.setIssueType(issueTypeEnum);
        // Persist the notification
        unauthenticatedContactUsNotificationService.create(contactUsNotification);

        // Create the email object
        UnauthenticatedContactEmail unauthenticatedContactEmail = new UnauthenticatedContactEmail();
        unauthenticatedContactEmail.setFirstName(firstName);
        unauthenticatedContactEmail.setLastName(lastName);
        unauthenticatedContactEmail.setTo(email);
        unauthenticatedContactEmail.setContent(issue);
        unauthenticatedContactEmail.setIssueType(issueTypeEnum);

        // Send the email
        unauthenticatedContactEmailService.sendUnauthenticatedContactEmail(unauthenticatedContactEmail);

        return Response.status(Response.Status.OK).build();
    }

}
