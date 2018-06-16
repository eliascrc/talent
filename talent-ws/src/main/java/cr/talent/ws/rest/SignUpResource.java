package cr.talent.ws.rest;

import cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService;
import cr.talent.support.exceptions.NonExistentConfirmationMessageException;
import org.apache.commons.validator.EmailValidator;
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
     * @return 200 if the supplied information is valid, 400 if any of the parameters is null or empty or if the
	 *  			password is not valid
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

        EmailValidator emailValidator = EmailValidator.getInstance();
        if (!emailValidator.isValid(email))
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            this.signUpConfirmationMessageService.sendMessage(firstName, lastName, email, password);
        } catch(IllegalArgumentException e) { //if the password is not valid
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }

    /**
     * Activates a user account if the supplied code matches the one sent in the previous step for the provided email
     * @param code the confirmation code entered by the user
     * @param email the user's email
     * @return 200 if the code matches the one sent in the previous step for the provided email,
     *          409 if no confirmation message is found for the provided email or if the provided code is not correct
     */
    @POST
    @Path("/stepTwo")
    public Response performSecondStep(@FormParam("code") String code, @FormParam("email") String email) {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(email))
            return Response.status(Response.Status.BAD_REQUEST).build();

        Response response;
        try {
            response = signUpConfirmationMessageService.confirmEmail(code, email) ? Response.ok().build()
                    : Response.status(Response.Status.CONFLICT).build();
        } catch (NonExistentConfirmationMessageException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return response;
    }

}
