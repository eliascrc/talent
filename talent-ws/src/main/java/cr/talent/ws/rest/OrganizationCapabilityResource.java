package cr.talent.ws.rest;

import cr.talent.core.capability.service.CapabilityService;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Capability;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationCapabilityException;
import cr.talent.support.exceptions.NullOrganizationInOrganizationCapabilityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new organization capability
 *
 * @author Elías Calderón
 */
@Component
@Scope("request")
@Path("/organizationCapability")
public class OrganizationCapabilityResource {

    private CapabilityService capabilityService;
    private OrganizationService organizationService;

    @Autowired
    public OrganizationCapabilityResource (CapabilityService capabilityService, OrganizationService organizationService) {
        this.capabilityService = capabilityService;
        this.organizationService = organizationService;
    }

    /**
     * Receives the request for creating a new organization capability.
     * @param organizationUniqueIdentifier the capability's organization unique identifier
     * @param name the capability's name
     * @return 200 if the organization capability is correctly created,
     *          400 if any of the parameters are null or empty strings,
     *          404 if the unique identifier does not belong to any organization,
     *          409 if the organization capability has already been created within the organization.
     */
    @POST
    @Path("/create")
    public Response createOrganizationCapability(
            @FormParam("organizationUniqueIdentifier") String organizationUniqueIdentifier,
            @FormParam("name") String name) {

        if (organizationUniqueIdentifier == null || name == null
                || organizationUniqueIdentifier.equals("") || name.equals(""))
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        Organization organization = this.organizationService.getOrganizationByUniqueIdentifier(organizationUniqueIdentifier);
        if (organization == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        Capability capability = new Capability();
        capability.setName(name);
        capability.setOrganization(organization);

        try {
            this.capabilityService.createOrganizationCapability(capability);

        } catch (NullOrganizationInOrganizationCapabilityException e) {
            // An organization capability should always have an organization associated
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (AlreadyCreatedOrganizationCapabilityException e) {
            // The capability was already created within an organization, so there's a conflict
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.ok().build();
    }

}
