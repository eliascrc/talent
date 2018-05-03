package cr.talent.ws.rest;


import cr.talent.core.termsOfService.service.ToSService;
import cr.talent.model.TermsOfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Web service to process requests for the application's terms of service.
 *
 * @author Josue Leon Sarkis
 */
@Component
@Scope("request")
@Path("/content/termsOfService")
public class TermsOfServiceResource {

    /**
     * TermsOfService service to obtain the currently active version terms of service.
     */
    @Autowired
    private ToSService toSService;

    /**
     * Gets the currently active terms of service via the TermsOfService service and returns a response with
     * the corresponding content and status code.
     *
     * @return Response with the corresponding http status code and the html content of the terms of service.
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    public Response processToSRequest() {
        Response response;
        TermsOfService termsOfService = this.toSService.getActiveTermsOfService();
        if (termsOfService != null) {
            String toSContent = termsOfService.getToSContent();
            response = Response.status(200).entity(toSContent).build();
        } else {
            response = Response.status(204).build();
        }
        return response;
    }
}
