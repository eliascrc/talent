package cr.talent.ws.rest;


import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.passwordResetRequest.service.PasswordResetRequestService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * This class will hold web services for automation tests.
 *
 * @author Josue Cubero.
 */
@Component
@Scope("request")
@Path("/automation")
public class AutomationResource {

    @Autowired
    PasswordResetRequestService passwordResetRequestService;

    @Autowired
    SignUpConfirmationMessageService signUpConfirmationMessageService;

    @Autowired
    private TechnicalResourceService technicalResourceService;

    @Autowired
    OrganizationService organizationService;

    @POST
    @Path ("/forgotPasswordToken")
    public Response forgotPasswordToken (@FormParam("email") String email,
                                         @FormParam("organizationIdentifier") String organizationIdentifier){
        if(StringUtils.isEmpty(email) || StringUtils.isEmpty(organizationIdentifier))
            return Response.status(Response.Status.BAD_REQUEST).build();

        String token = this.passwordResetRequestService.getToken(email,organizationIdentifier);
        if(token == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().entity(token).build();
    }

    @POST
    @Path ("/signUpCode")
    public Response signUpCode (@FormParam("email") String email){
        if (StringUtils.isEmpty(email))
            return Response.status(Response.Status.BAD_REQUEST).build();

        String code = this.signUpConfirmationMessageService.getCode(email);
        if(code == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok().entity(code).build();
    }

    @POST
    @Path ("/deleteTechnicalResource")
    public Response deleteTechnicalResource (@FormParam("email") String email){
        if (StringUtils.isEmpty(email))
            return Response.status(Response.Status.BAD_REQUEST).build();

        TechnicalResource technicalResource = this.technicalResourceService.getTechnicalResourceByUsername(email);
        if(technicalResource == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        this.technicalResourceService.remove(technicalResource);
        return Response.ok().build();
    }

    @POST
    @Path ("/deleteOrganization")
    public Response deleteOrganization (@FormParam("uniqueIdentifier") String uniqueIdentifier){
        if (StringUtils.isEmpty(uniqueIdentifier))
            return Response.status(Response.Status.BAD_REQUEST).build();

        Organization organization = this.organizationService.getOrganizationByUniqueIdentifier(uniqueIdentifier);
        if(organization == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        this.organizationService.remove(organization);
        return Response.ok().build();
    }
}
