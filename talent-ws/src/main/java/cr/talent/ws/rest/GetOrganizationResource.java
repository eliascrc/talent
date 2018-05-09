package cr.talent.ws.rest;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Organization;
import cr.talent.support.flexjson.JSONSerializerBuilder;
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

    /**
     * Method that returns an organization object with a given unique identifier
     * @param uniqueIdentifier the unique identifier of the desired organization
     * @return the organization with the unique id, 400 if the request is bad, 404 if there is no organization with
     * that unique identifier
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getOrganization(@QueryParam("uniqueIdentifier") String uniqueIdentifier) {
        Response response;

        if (uniqueIdentifier == null || uniqueIdentifier.equals("")) {
            response = Response.status(400).build(); // It is a bad request
        } else { // if the request is valid
            Organization organization = organizationService.getOrganizationByUniqueIdentifier(uniqueIdentifier);
            if (organization == null) { // if there's no organization with that unique identifier
                response = Response.status(404).build(); // a not found status code is returned
            } else {
                String organizationJson = JSONSerializerBuilder.getOrganizationSerializer().serialize(organization);
                response = Response.status(200).entity(organizationJson).build();
            }
        }

        return response;
    }
}
