package cr.talent.ws.rest;

import cr.talent.core.privacyPolicy.service.PrivacyPolicyService;
import cr.talent.model.PrivacyPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource with a GET endpoint that returns the active privacy policy
 *
 * @author Daniel Montes de Oca
 */
@Component
@Scope("request")
@Path("/content/privacy-policy")
public class PrivacyPolicyResource {

    @Autowired
    PrivacyPolicyService privacyPolicyService;

    /**
     * GET endpoint that returns the active privacy policy
     * @return a 200 response with the active privacy policy, 204 code if there is none
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getActivePrivacyPolicy() {
        PrivacyPolicy activePrivacyPolicy = privacyPolicyService.getActivePrivacyPolicy();

        Response response;
        if (activePrivacyPolicy == null) {
            response = Response.status(204).build();
        } else {
            String privacyPolicyContent = activePrivacyPolicy.getContent();
            response = Response.status(200).entity(privacyPolicyContent).build();
        }

        return response;
    }

}
