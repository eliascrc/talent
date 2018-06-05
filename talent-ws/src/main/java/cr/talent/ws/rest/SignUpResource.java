package cr.talent.ws.rest;

import cr.talent.core.technicalResource.service.TechnicalResourceService;
import cr.talent.model.TechnicalResource;
import cr.talent.model.User;
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
@Path("/ws/signUp")
public class SignUpResource {

    @Autowired
    TechnicalResourceService technicalResourceService;

    //The highest number that can be used for a confirmation code
    private static final int MAX_CONFIRMATION_CODE = 999999;

    @POST
    @Path("/step1")
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

        return Response.ok().build();
    }

}
