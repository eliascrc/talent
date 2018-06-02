package cr.talent.ws.rest;

import cr.talent.core.contactEmail.service.ContactEmailService;
import flexjson.JSON;
import flexjson.JSONContext;
import jdk.nashorn.internal.parser.JSONParser;
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
     * @param formInformation json with first name, last name and email if the user is not signed in (otherwise it is
     *                        obtained from the logged in user) and subject and body of the message.
     *
     * @return 200 if the predefined capability is correctly created,
     *          400 if the name is null or an empty string,
     *          409 if there's already a predefined capability with the specified name,
     *          500 if the predefined capability's organization attribute is not null.
     */
    @POST
    @Path("create")
    public Response createContactEmail(@FormParam("formInformation") String formInformation) {

        return Response.status(Response.Status.OK).build();
    }
}
