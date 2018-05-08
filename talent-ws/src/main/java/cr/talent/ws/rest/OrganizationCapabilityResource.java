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

    @Autowired
    CapabilityService capabilityService;

    @Autowired
    OrganizationService organizationService;

    /**
     * Receives the request for creating a new capability.
     * @param organizationUniqueIdentifier the capability's organization unique identifier
     * @param name the organization capability's name
     * @return 200 if the org capability is correctly created, 400 if any of the parameters are null or empty strings,
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
            return Response.status(Response.Status.BAD_REQUEST).build();

        Organization organization = this.organizationService.getOrganizationByUniqueIdentifier(organizationUniqueIdentifier);
        if (organization == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        Capability capability = new Capability();
        capability.setName(name);
        capability.setOrganization(organization);

        try {

            this.capabilityService.createOrganizationCapability(capability);
            organization.getCapabilities().add(capability);
            this.organizationService.update(organization);

        } catch (NullOrganizationInOrganizationCapabilityException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } catch (AlreadyCreatedOrganizationCapabilityException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.ok().build();
    }

}
