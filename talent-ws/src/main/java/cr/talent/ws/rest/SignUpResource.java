package cr.talent.ws.rest;


import cr.talent.core.email.signUpConfirmationEmail.service.SignUpConfirmationEmailService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService;
import cr.talent.model.SignUpConfirmationMessage;
import cr.talent.model.TechnicalResource;
import cr.talent.model.User;
import cr.talent.support.exceptions.NonExistentConfirmationMessageException;
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
    TechnicalResourceService technicalResourceService;

    @Autowired
    SignUpConfirmationMessageService signUpConfirmationMessageService;

    @Autowired
    SignUpConfirmationEmailService signUpConfirmationEmailService;

    //The highest number that can be used for a confirmation code
    private static final int MAX_CONFIRMATION_CODE = 999999;

    //The number of digits in the confirmation code
    private static final int DIGITS = 6;

    @POST
    @Path("/stepOne")
    public Response performFirstStep(@FormParam("firstName") String firstName,
                                     @FormParam("lastName") String lastName,
                                     @FormParam("email") String email,
                                     @FormParam("password") String password) {

        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName) || StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(password))
            return Response.status(Response.Status.BAD_REQUEST).build();

        TechnicalResource technicalResource;
        SignUpConfirmationMessage signUpConfirmationMessage;
        boolean hadAnotherConfirmationMessage = false;
        int confirmationCode = (int) (MAX_CONFIRMATION_CODE * Math.random());
        try {
            signUpConfirmationMessage = signUpConfirmationMessageService
                    .getActiveConfirmationMessage(email);
            technicalResource = signUpConfirmationMessage.getTechnicalResource();
            hadAnotherConfirmationMessage = true;
        } catch(NonExistentConfirmationMessageException e) {
            technicalResource = new TechnicalResource();
            signUpConfirmationMessage = new SignUpConfirmationMessage();
        }

        technicalResource.setFirstName(firstName);
        technicalResource.setLastName(lastName);
        technicalResource.setUsername(email);
        technicalResource.setPassword(password);
        technicalResource.setStatus(User.Status.INACTIVE);
        technicalResource.setAdministrator(true); //because they are signing up while creating an organization

        signUpConfirmationMessage.setConfirmationCode(String.format("%0" + DIGITS + "d", confirmationCode));
        signUpConfirmationMessage.setTechnicalResource(technicalResource);


        try {
            if (hadAnotherConfirmationMessage) {
                this.technicalResourceService.update(technicalResource);
                this.signUpConfirmationMessageService.update(signUpConfirmationMessage);
            } else {
                this.technicalResourceService.create(technicalResource);
                this.signUpConfirmationMessageService.create(signUpConfirmationMessage);
            }
            signUpConfirmationEmailService.sendSignUpConfirmationEmail(signUpConfirmationMessage);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }


        return Response.ok().build();
    }

}
