package cr.talent.ws.rest;


import cr.talent.core.email.signUpConfirmationEmail.service.SignUpConfirmationEmailService;
import cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a GET endpoint that returns the active privacy policy
 *
 * @author Daniel Montes de Oca
 */
@Component
@Scope("request")
@Path("/signUp")
public class SignUpResource {

    @Autowired
    SignUpConfirmationMessageService signUpConfirmationMessageService;

    /**
     * Creates a technical resource with the supplied information if it is valid and sends a confirmation email. If the
     * information is not valid it returns a response with a code that reflects the problem.
     * @param firstName the first name of the resource performing the first step of the sign up
     * @param lastName the last name of the resource performing the first step of the sign up
     * @param email the email of the resource performing the first step of the sign up
     * @param password the password of the resource performing the first step of the sign up
     * @return
     */
    @POST
    @Path("/stepOne")
    public Response performFirstStep(@FormParam("firstName") String firstName,
                                     @FormParam("lastName") String lastName,
                                     @FormParam("email") String email,
                                     @FormParam("password") String password) {

        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName) || StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(password))
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            this.signUpConfirmationMessageService.sendMessage(firstName, lastName, email, password);
        } catch(IllegalArgumentException e) { //if the password is not valid
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }

}
