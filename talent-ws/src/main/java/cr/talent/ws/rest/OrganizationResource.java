package cr.talent.ws.rest;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationException;
import cr.talent.support.exceptions.NonExistentUserWithNullOrganization;
import cr.talent.support.exceptions.NotOrganizationAdministratorException;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new organization
 *
 * @author Elías Calderón, Fabián Roberto Leandro
 */
@Component
@Scope("request")
@Path("/organization")
public class OrganizationResource {

    private static long MAX_FILE_SIZE = 5242880;

    @Autowired
    private OrganizationService organizationService;

    /**
     * Receives the request for creating a new organization. If the unique identifier is not already
     * registered, it creates the organization successfully.
     *
     * @param uniqueIdentifier the organization's unique identifier.
     * @param name             the organization's name.
     * @param username         technical resource's username.
     * @return 200 if the organization is correctly created, 400 if any of the parameters are null,
     * 409 if an organization already has the specified unique identifier.
     */
    @POST
    @Path("/create")
    public Response createOrganization(
            @FormParam("username") String username,
            @FormParam("uniqueIdentifier") String uniqueIdentifier,
            @FormParam("name") String name,
            @FormParam("termsOfServiceAccepted") Boolean termsOfServiceAccepted) {

        if (StringUtils.isEmpty(uniqueIdentifier) || StringUtils.isEmpty(name) || StringUtils.isEmpty(username) ||
                termsOfServiceAccepted == null || !termsOfServiceAccepted)
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        try {
            this.organizationService.createOrganization(username, uniqueIdentifier, name);
            return Response.ok().build();

        } catch (AlreadyCreatedOrganizationException e) {
            // If the organization is already created a conflict should be returned
            return Response.status(Response.Status.CONFLICT).
                    entity("AlreadyCreatedOrganization").build();
        } catch (NonExistentUserWithNullOrganization e){
            //If the user with the username does not have a null organization where the new organization can be assigned
            return Response.status(Response.Status.CONFLICT).
                    entity("NonExistentUserWithNullOrganization").build();
        }
    }

    /**
     *  Receives the request for getting the basic information for the organization of the logged in user.
     *
     * @return 200 if the organization is correctly returned,
     *  403 if the user has no organization associated.
     */
    @GET
    @Path("/get")
    public Response getOrganizationBasicInformation() {

        Organization organization = SecurityUtils.getLoggedInTechnicalResource().getOrganization();

        if (organization == null)
            return Response.status(Response.Status.FORBIDDEN).build();

        return Response.ok().entity(JSONSerializerBuilder.getOrganizationSerializer().serialize(organization)).build();
    }

    /**
     * This endpoint changes an organization's name or unique identifier.
     *
     * @param name              the organization's new name
     * @param uniqueIdentifier  the organization's new unique identifier
     *
     * @return 401 if the currently logged in user is not their organization's administrator
     *         409 if the received unique identifier is already being used by another organization
     *         200 if the changes were done correctly
     */
    @POST
    @Path("/edit")
    public Response editOrganizationBasicInformation(
            @FormParam("name") String name,
            @FormParam("uniqueIdentifier") String uniqueIdentifier) {

        // Get the logged in user
        TechnicalResource organizationAdministrator = SecurityUtils.getLoggedInTechnicalResource();

        // Get the organization to be edited
        Organization organization = this.organizationService
                .findById(organizationAdministrator.getOrganization().getId());

        try {
            this.organizationService.editBasicInformation(organization, organizationAdministrator, name, uniqueIdentifier);
        } catch(NotOrganizationAdministratorException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        } catch(DataIntegrityViolationException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.ok().build();
    }

}
