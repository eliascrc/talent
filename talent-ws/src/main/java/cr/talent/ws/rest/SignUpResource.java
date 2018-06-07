package cr.talent.ws.rest;


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

    //The highest number that can be used for a confirmation code
    private static final int MAX_CONFIRMATION_CODE = 999999;

    @POST
    @Path("/stepOne")
    public Response performFirstStep(@FormParam("firstName") String firstName,
                                     @FormParam("lastName") String lastName,
                                     @FormParam("email") String email,
                                     @FormParam("password") String password) {

        if (StringUtils.isEmpty(firstName) || StringUtils.isEmpty(lastName) || StringUtils.isEmpty(email) ||
                StringUtils.isEmpty(password))
            return Response.status(Response.Status.BAD_REQUEST).build();

        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setFirstName(firstName);
        technicalResource.setLastName(lastName);
        technicalResource.setUsername(email);
        technicalResource.setPassword(password);
        technicalResource.setStatus(User.Status.INACTIVE);
        technicalResource.setAdministrator(true); //because they are signing up while creating an organization
        this.technicalResourceService.create(technicalResource);

        int confirmationCode = (int) (MAX_CONFIRMATION_CODE * Math.random());
        SignUpConfirmationMessage signUpConfirmationMessage;
        try {
            signUpConfirmationMessage = signUpConfirmationMessageService
                    .getActiveConfirmationMessage(technicalResource.getUsername());
            signUpConfirmationMessage.setConfirmationCode(String.valueOf(confirmationCode));
            signUpConfirmationMessage.setTechnicalResource(technicalResource);
            signUpConfirmationMessageService.update(signUpConfirmationMessage);
        } catch(NonExistentConfirmationMessageException e) {
            signUpConfirmationMessage = new SignUpConfirmationMessage();
            signUpConfirmationMessage.setConfirmationCode(String.valueOf(confirmationCode));
            signUpConfirmationMessage.setTechnicalResource(technicalResource);
            signUpConfirmationMessageService.create(signUpConfirmationMessage);
        }


        return Response.ok().build();
    }

}
