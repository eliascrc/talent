package cr.talent.ws.rest;

import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import flexjson.JSONSerializer;
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
 * Resource that handles the retrieval of information related to technical resources.
 *
 * @author Daniel Montes de Oca
 */
@Component
@Scope("request")
@Path("/technicalResource")
public class TechnicalResourceResource {

    @Autowired
    TechnicalResourceService technicalResourceService;

    /**
     * GET endpoint that returns the active privacy policy
     * @return a 200 response with the active privacy policy, 204 code if there is none
     */
    @GET
    @Produces(MediaType.TEXT_HTML)
    @Path("/basicInformation")
    public Response getBasicInformation(@QueryParam("username") String username) {
        Organization organization = SecurityUtils.getLoggedInTechnicalResource().getOrganization();

        if (organization == null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        TechnicalResource technicalResource = technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(username,
                organization.getUniqueIdentifier());

        if (technicalResource == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        String serializedResource = JSONSerializerBuilder.getTechnicalResourceSerializer().serialize(technicalResource);
        return Response.ok().entity(serializedResource).build();
    }

}
