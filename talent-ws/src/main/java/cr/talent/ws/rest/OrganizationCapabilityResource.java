package cr.talent.ws.rest;

import cr.talent.core.capability.service.CapabilityService;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Capability;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
@Path("/organizationCapability")
public class OrganizationCapabilityResource {

    @Autowired
    CapabilityService capabilityService;

    @Autowired
    OrganizationService organizationService;

    /**
     * Receives the request for creating a new capability.
     * @param organizationUniqueIdentifier the organization's unique identifier
     * @param name the organization's name
     * @return
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


        return Response.ok().build();
    }

}
