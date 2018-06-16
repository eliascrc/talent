package cr.talent.ws.rest;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new organization
 *
 * @author Elías Calderón
 */
@Component
@Scope("request")
@Path("/organization")
public class OrganizationResource {

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
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
