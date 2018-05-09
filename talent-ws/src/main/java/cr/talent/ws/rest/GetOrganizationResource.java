package cr.talent.ws.rest;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Resource with a GET endpoint that returns an organization with a given unique identifier
 *
 * @author Daniel Montes de Oca
 */
@Component
@Scope("request")
@Path("/content/getOrganizationTest")
public class GetOrganizationResource {

    @Autowired
    OrganizationService organizationService;

    @GET
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getOrganization(@QueryParam("uniqueIdentifier") String uniqueIdentifier) {
        Response response;

        if (uniqueIdentifier == null || uniqueIdentifier.equals("")) {
            response = Response.status(400).build();
        } else {
            Organization organization = organizationService.getOrganizationWithUniqueId(uniqueIdentifier);
            if (organization == null) {
                response = Response.status(404).build();
            } else {
                response = Response.status(200).entity(uniqueIdentifier).build();response = Response.status(200).entity(uniqueIdentifier).build();
            }
        }

        return response;
    }
}
