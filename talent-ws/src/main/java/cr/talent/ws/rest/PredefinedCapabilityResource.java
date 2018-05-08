package cr.talent.ws.rest;

import cr.talent.core.capability.service.CapabilityService;
import cr.talent.model.Capability;
import cr.talent.support.exceptions.AlreadyCreatedPredefinedCapabilityException;
import cr.talent.support.exceptions.NotNullOrganizationInPredefinedCapabilityException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new predefined capability
 *
 * @author Elías Calderón
 */
@Component
@Scope("request")
@Path("/predefinedCapability")
public class PredefinedCapabilityResource {

    @Autowired
    CapabilityService capabilityService;

    /**
     * Receives the request for creating a new predefined capability.
     * @param name the predefined capability's name
     * @return 200 if the predefined capability is correctly created, 400 if the name is null or an empty string,
     *          409 if the predefined capability has already been created with the specified name,
     *          500 if the predefined capability's organization attribute is not null.
     */
    @POST
    @Path("/create")
    public Response createOrganizationCapability(
            @FormParam("name") String name) {

        if (name == null || name.equals(""))
            return Response.status(Response.Status.BAD_REQUEST).build();

        Capability capability = new Capability();
        capability.setName(name);

        try {
            this.capabilityService.createPredefinedCapability(capability);
        } catch (NotNullOrganizationInPredefinedCapabilityException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } catch (AlreadyCreatedPredefinedCapabilityException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }

        return Response.ok().build();
    }

}
