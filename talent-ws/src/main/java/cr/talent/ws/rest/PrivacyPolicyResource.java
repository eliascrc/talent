package cr.talent.ws.rest;

import cr.talent.core.privacyPolicy.service.PrivacyPolicyService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Scope("request")
@Path("/privacypolicy")
public class PrivacyPolicyResource {


    PrivacyPolicyService privacyPolicyService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response test() {
        String privacyPolicyContent = privacyPolicyService.getActivePrivacyPolicy().getContent();

        Response response;
        if (privacyPolicyContent == null) {
            response = Response.status(204).build();
        } else {
            response = Response.status(200).entity(privacyPolicyContent).build();
        }

        return response;
    }

}
