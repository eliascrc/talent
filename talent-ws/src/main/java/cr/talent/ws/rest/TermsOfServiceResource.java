package cr.talent.ws.rest;


import cr.talent.core.termsOfService.service.ToSService;
import cr.talent.model.Platform;
import cr.talent.support.exceptions.NoActiveTermsOfServiceException;
import cr.talent.ws.rest.support.exceptions.UnsupportedPlatformException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource with a GET endpoint to process requests for the application's terms of service.
 *
 * @author Josue Leon Sarkis
 */
@Component
@Scope("request")
@Path("/content/termsOfService")
public class TermsOfServiceResource extends ContentResource {

    /**
     * TermsOfService service to obtain the currently active version terms of service.
     */
    @Autowired
    private ToSService toSService;

    /**
     * Gets the currently active terms of service via the TermsOfService service and returns a response with
     * the corresponding content and status code.
     *
     * @return 200 if it was possible to retrieve the html content of the terms of service correctly,
     * 204 if there is no currently active terms of service content
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response processToSRequest(@QueryParam("platform") String platformName) {
        if (StringUtils.isEmpty(platformName))
            return Response.status(Response.Status.BAD_REQUEST).build();

        try {
            String toSContent;
            Platform platform = super.getPlatformByName(platformName);
            toSContent = this.toSService.getActiveTermsOfService(platform).getToSContent();

            return Response.ok().entity(toSContent).build();
        } catch (NoActiveTermsOfServiceException e) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (UnsupportedPlatformException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
