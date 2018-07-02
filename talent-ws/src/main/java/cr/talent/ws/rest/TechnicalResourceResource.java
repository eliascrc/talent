package cr.talent.ws.rest;

import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import flexjson.JSONSerializer;
import org.apache.commons.lang.StringUtils;
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
     * GET endpoint that returns the basic information of a technical resource
     * @param username the username of the resource
     * @return a 200 response with a json with the resource's information,
     *          404 code if no technical resource was found with that username
     *          400 if the parameter is empty or if the user requesting the log in does not belong to an organization
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/basicInformation")
    public Response getBasicInformation(@QueryParam("username") String username) {
        if (StringUtils.isEmpty(username))
            return Response.status(Response.Status.BAD_REQUEST).build();

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

    /**
     * GET endpoint that returns the basic information of a technical resource
     * @param id the id of the resource
     * @return a 200 response with a json with the resource's information,
     *          404 code if no technical resource was found with that id
     *          400 if the parameter is empty or if the user requesting the log in does not belong to an organization
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/basicInformation/id")
    public Response getBasicInformationById(@QueryParam("id") String id) {
        if (StringUtils.isEmpty(id))
            return Response.status(Response.Status.BAD_REQUEST).build();

        TechnicalResource technicalResource = this.technicalResourceService.findById(id);

        if (technicalResource == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        Organization organization = SecurityUtils.getLoggedInTechnicalResource().getOrganization();

        if (!organization.equals(technicalResource.getOrganization()))
            return Response.status(Response.Status.NOT_FOUND).build();

        String serializedResource = JSONSerializerBuilder.getTechnicalResourceSerializer().serialize(technicalResource);
        return Response.ok().entity(serializedResource).build();
    }

}
