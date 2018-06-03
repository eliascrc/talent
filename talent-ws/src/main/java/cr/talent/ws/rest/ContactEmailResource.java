package cr.talent.ws.rest;

import cr.talent.core.contactEmail.service.ContactEmailService;
import cr.talent.model.ContactEmail;
import cr.talent.model.ContactEmailIssueType;
import cr.talent.model.User;
import cr.talent.support.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new Contact Us request.
 *
 * @author Fabián Roberto Leandro
 */
@Component
@Scope("request")
@Path("/contactUs")
public class ContactEmailResource {

    @Autowired
    ContactEmailService contactEmailService;

    /**
     * Receives the request to create a new contact email.
     * @param firstName the first name of the person creating the Contact Us request.
     * @param lastName the last name of the person creating the Contact Us request.
     * @param email the email of the person creating the Contact Us request, where the reply will be sent to.
     * @param issueType the issue type selected by the user from a drop down box, represented in the model by an enum.
     * @param issue the content of the message left by the user.
     *
     * @return  200 if the contactEmail is correctly created,
     *          400 if the issue or issueType is empty or null, or if email and first/last name are empty or null and
     *          there is no logged in user, or if the issue type is not valid.
     */
    @POST
    @Path("/create")
    public Response createContactEmail(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("issueType") String issueType,
            @FormParam("issue") String issue) {

        ContactEmail contactEmail = new ContactEmail();

        // Process name, firstName and email
        User loggedUser;
        // Check if a user is logged in
        if ((loggedUser = (User)SecurityUtils.getLoggedInUser()) == null) {
            // If no user is logged in then first name, last name and email should not be empty or null
            if(/*firstName == null || firstName.equals("")
                    || lastName == null || lastName.equals("")
                    || */email == null || email.equals("")){
                System.out.println(1);
                return Response.status(Response.Status.BAD_REQUEST).build();
            }

            // If it's not empty or null, use the received info
            contactEmail.setEmail(email);
            contactEmail.setFirstName(firstName);
            contactEmail.setLastName(lastName);

        } else {
            // If there is a logged user then get the info from that user
            contactEmail.setEmail(loggedUser.getUsername());
            contactEmail.setFirstName(loggedUser.getFirstName());
            contactEmail.setLastName(loggedUser.getLastName());
        }

        // Process the issue
        if(issue == null || issue.equals("")) { // The issue should not be empty or null
            System.out.println(2);
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        contactEmail.setContent(issue);

        // Process the issue type
        switch(issueType) {
            case "Authentication issue":
                contactEmail.setIssueType(ContactEmailIssueType.AUTHENTICATION_ISSUES);
                break;

            case "Account closing":
                contactEmail.setIssueType(ContactEmailIssueType.ACCOUNT_CLOSING);
                break;

            case "Other":
                contactEmail.setIssueType(ContactEmailIssueType.OTHER);
                break;

            default:
                // Return 400 if an invalid issue type is received
                System.out.println(3);
                return Response.status(Response.Status.BAD_REQUEST).build();
        }

        this.contactEmailService.create(contactEmail);

        return Response.status(Response.Status.OK).build();
    }
}
