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
@Path("/admin/capability")
public class PredefinedCapabilityResource {

    @Autowired
    private CapabilityService capabilityService;

    /**
     * Receives the request for creating a new predefined capability.
     * @return  200 if the contact email is correctly created,
     *          400 if the json is malformed or missing information,
     *          500 if the predefined capability's organization attribute is not null.
     */
    @POST
    @Path("/create")
    public Response createOrganizationCapability(
            @FormParam("name") String name) {

        if (name == null || name.equals(""))
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        Capability capability = new Capability();
        capability.setName(name);

        try {

            this.capabilityService.createPredefinedCapability(capability);
            return Response.ok().build();

        } catch (NotNullOrganizationInPredefinedCapabilityException e) {
            // If the capability has an organization associated there's a problem in the server
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        } catch (AlreadyCreatedPredefinedCapabilityException e) {
            // If the capability already exists, there's a conflict
            return Response.status(Response.Status.CONFLICT).build();
        }
    }

}
