package cr.talent.ws.rest;

import cr.talent.core.privacyPolicy.service.PrivacyPolicyService;
import cr.talent.model.Platform;
import cr.talent.support.exceptions.NoActivePrivacyPolicyException;
import cr.talent.ws.ContentResource;
import cr.talent.ws.rest.support.exceptions.UnsupportedPlatformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource with a GET endpoint that returns the active privacy policy
 *
 * @author Daniel Montes de Oca
 */
@Component
@Scope("request")
@Path("/content/privacyPolicy")
public class PrivacyPolicyResource extends ContentResource {

    @Autowired
    PrivacyPolicyService privacyPolicyService;

    /**
     * GET endpoint that returns the active privacy policy
     * @return a 200 response with the active privacy policy, 204 code if there is none
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response getActivePrivacyPolicy(@QueryParam("platform") String platformName) {
        try {
            String privacyPolicyContent;
            Platform platform = super.getPlatformByName(platformName);
            privacyPolicyContent = this.privacyPolicyService.getActivePrivacyPolicy(platform).getContent();

            return Response.ok().entity(privacyPolicyContent).build();
        } catch (NoActivePrivacyPolicyException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (UnsupportedPlatformException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

}
