package cr.talent.ws.rest;

import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.Date;

/**
 * Resource that handles the getting and setting of a technical resource's technical position
 *
 * @author Fabián Roberto Leandro
 */

@Component
@Scope("request")
@Path("/technicalResource/technicalPosition")
public class TechnicalResourceTechnicalPositionResource {

    @Autowired
    TechnicalResourceService technicalResourceService;

    /**
     * Receives a request to assign a technical position to a technical resource.
     *
     * @param capabilityLevel      the technical position to be assigned.
     * @param technicalResourceEmail the technical resource to be assigned the position.
     * @return 400 if the either parameter is null or empty
     * 409 with "TechnicalPositionAlreadyAssigned" if the technical position has already been assigned to the technical resource.
     * 404 with "NonExistentCapabilityException" if the technical position is non existent.
     * 401 if no user is logged in.
     * 200 if the technical position was correctly assigned.
     */
    @POST
    @Produces(MediaType.TEXT_HTML)
    @Path("/assign")
    public Response assignProjectPosition(@FormParam("capability") String capability,
                                          @FormParam("capabilityLevel") String capabilityLevel,
                                          @FormParam("technicalResource") String technicalResourceEmail,
                                          @FormParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate) {

        if (StringUtils.isEmpty(capability) || StringUtils.isEmpty(capabilityLevel)
                || StringUtils.isEmpty(technicalResourceEmail) || startDate==null)
            return Response.status(Response.Status.BAD_REQUEST).build();

        // Get the lazy-loaded logged in user
        TechnicalResource loggedTechnicalResource = SecurityUtils.getLoggedInTechnicalResource();

        if (loggedTechnicalResource == null)
            return Response.status(Response.Status.UNAUTHORIZED).build();

        TechnicalResource asignee =
                this.technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(
                        technicalResourceEmail, loggedTechnicalResource.getOrganization().getUniqueIdentifier());

        if(asignee==null)
            return Response.status(Response.Status.NOT_FOUND).entity("NonExistentTechnicalResource").build();

        try {
            this.technicalResourceService
                    .assignTechnicalPositionToTechnicalResource(capability, capabilityLevel, asignee.getOrganization(), asignee, startDate);
        } catch (NonExistentCapabilityException e) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("NonExistentCapability").build();
        } catch (NonExistentCapabilityLevelException e) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("NonExistentCapabilityLevel").build();
        } catch (AlreadyAssignedTechnicalPositionException e) {
            return Response.status(Response.Status.CONFLICT).
                    entity("AlreadyAssignedTechnicalPosition").build();
        } catch (UserDoesNotHaveRequiredSkillsException e) {
            return Response.status(Response.Status.CONFLICT).
                    entity("UserDoesNotHaveRequiredSkills").build();
        }

        return Response.ok().build();
    }
}
