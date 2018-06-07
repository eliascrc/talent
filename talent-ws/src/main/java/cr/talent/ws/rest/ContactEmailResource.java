package cr.talent.ws.rest;

import cr.talent.model.ContactEmail;
import cr.talent.model.ContactEmailIssueType;
import cr.talent.model.User;
import cr.talent.support.SecurityUtils;
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

    @Auto

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
    @Path ("/unauthenticated")
    @POST
    public Response unauthenticatedContactUs(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("issueType") String issueType,
            @FormParam("issue") String issue) {

        // Check if the request is valid
        if(issue == null || issue.equals("")|| issueType == null || !ContactEmailIssueType.isValidIssueType(issueType)
                || firstName == null || firstName.equals("") || lastName == null || lastName.equals("")
                || email == null || email.equals(""))
            return Response.status(Response.Status.BAD_REQUEST).build();




        return Response.status(Response.Status.OK).build();
    }

    /**
     * Receives the request to create a new contact email.
     * @param firstName the first name of the person creating the Contact Us request.
     * @param lastName the last name of the person creating the Contact Us request.
     * @param email the email of the person creating the Contact Us request, where the reply will be sent to.
     * @param issueType the issue type selected by the user from a drop down box, represented in the model by an enum.
     * @param issue the content of the message left by the user.
     *
     * @return  200 if the contactEmail is correctly created,
     *          400 if the email, first name, last name, issue or issueType is empty or null, or if the issue type is not valid.
     */
    @Path ("/authenticated")
    @POST
    public Response authenticatedContactUs(
            @FormParam("firstName") String firstName,
            @FormParam("lastName") String lastName,
            @FormParam("email") String email,
            @FormParam("issueType") String issueType,
            @FormParam("issue") String issue) {

        // Check if the request is valid
        if(issue == null || issue.equals("")|| issueType == null || !ContactEmailIssueType.isValidIssueType(issueType)
                || firstName == null || firstName.equals("") || lastName == null || lastName.equals("")
                || email == null || email.equals(""))
            return Response.status(Response.Status.BAD_REQUEST).build();




        return Response.status(Response.Status.OK).build();
    }
}
