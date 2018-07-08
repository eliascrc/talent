package cr.talent.ws.rest;

import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Feedback;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import flexjson.JSONSerializer;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

/**
 * Resource that handles the requests related to technical resources.
 *
 * @author Daniel Montes de Oca, Fabián Roberto Leandro
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

    /**
     * Post endpoint used by organization administrators for editing the basic information of a technical resource
     * @param technicalResourceId the identifier of the technical resource
     * @param firstName the new first name for the technical resource
     * @param lastName the new last name for the technical resource
     * @param nickname the new nickname for the technical resource
     *
     * @return  400 if a parameter was not sent or sent empty, or if the user performing the request does not belong to
     *              an organization
     *          403 if the resource is not the administrator of the organization
     *          404 if no resource was found with the provided identifier or if the resource that was found belongs to
     *              another organization (not found is used for security reasons)
     *          200 and a JSON with the user information if the request was successful
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/basicInformation/edit")
    public Response editBasicInformation(@FormParam("technicalResourceId") String technicalResourceId,
                                         @FormParam("firstName") String firstName,
                                         @FormParam("lastName") String lastName,
                                         @FormParam("nickname") String nickname) {
        if (StringUtils.isEmpty(technicalResourceId) || StringUtils.isEmpty(firstName) || StringUtils.isEmpty(nickname))
            return Response.status(Response.Status.BAD_REQUEST).entity("A parameter was left empty or not sent")
                    .build();

        TechnicalResource loggedInTechnicalResource = SecurityUtils.getLoggedInTechnicalResource();

        if (loggedInTechnicalResource.getOrganization() == null)
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("You must belong to an organization to edit resources").build();

        if (!loggedInTechnicalResource.isAdministrator())
            return Response.status(Response.Status.FORBIDDEN).build();

        TechnicalResource technicalResource = technicalResourceService.findById(technicalResourceId);

        // Not found is returned if no technical resource was found or the one that was found belongs to another organization
        if (technicalResource == null || !loggedInTechnicalResource.getOrganization().equals(technicalResource.getOrganization()))
            return Response.status(Response.Status.NOT_FOUND).build();

        this.technicalResourceService.editBasicInformation(technicalResource, firstName, lastName, nickname);

        String serializedResource = JSONSerializerBuilder.getTechnicalResourceSerializer().serialize(technicalResource);
        return Response.ok().entity(serializedResource).build();
    }

    /**
     * Post endpoint for editing the basic information of the logged in technical resource
     * @param nickname the new nickname for the technical resource
     *
     * @return  400 if a parameter was not sent or sent empty
     *          200 and a JSON with the user information if the request was successful
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/basicInformation/edit/nickname")
    public Response editBasicInformation(@FormParam("nickname") String nickname) {
        if (StringUtils.isEmpty(nickname))
            return Response.status(Response.Status.BAD_REQUEST).entity("A parameter was left empty or not sent")
                    .build();

        TechnicalResource loggedInTechnicalResource = SecurityUtils.getLoggedInTechnicalResource();

        this.technicalResourceService.editBasicInformation(loggedInTechnicalResource,
                loggedInTechnicalResource.getFirstName(), loggedInTechnicalResource.getLastName(), nickname);

        String serializedResource = JSONSerializerBuilder.getTechnicalResourceSerializer()
                .serialize(loggedInTechnicalResource);

        return Response.ok().entity(serializedResource).build();
    }

    /**
     * TODO documentation
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/basicInformation/feedback/get")
    public Response getFeedback(@FormParam("technicalResource") String technicalResourceEmail) {
        if (StringUtils.isEmpty(technicalResourceEmail))
            return Response.status(Response.Status.BAD_REQUEST).build();

        // Get the user whose kudos/warnings will be returned, using their username (received from the request)
        // and organization identifier (obtained from the logged in user's organization)
        TechnicalResource loggedInUser = SecurityUtils.getLoggedInTechnicalResource();
        TechnicalResource observee = this.technicalResourceService
                .getTechnicalResourceByUsernameAndOrganizationIdentifier(technicalResourceEmail,
                        loggedInUser.getOrganization().getUniqueIdentifier());

        // Get the user making the request from security utils
        TechnicalResource observer = this.technicalResourceService
                .getTechnicalResourceByUsernameAndOrganizationIdentifier(loggedInUser.getUsername(),
                        loggedInUser.getOrganization().getUniqueIdentifier());

        Set<Feedback> feedback = this.technicalResourceService.getFeedback(observer,observee);

        String serializedFeedback = JSONSerializerBuilder.getFeedbackSerializer()
                .serialize(feedback);

        return Response.ok().entity(serializedFeedback).build();
    }

}
