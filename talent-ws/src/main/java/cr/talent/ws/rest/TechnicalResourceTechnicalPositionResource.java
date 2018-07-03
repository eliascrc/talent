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
     * @param capabilityLevel the capability that has the capability level to be assigned.
     * @param capabilityLevel the capability level to be associated with the assigned technical position.
     * @param technicalResourceEmail the technical resource to be assigned the position.
     * @param startDate the date the technical resource will be/was given the technical position.
     *
     * @return 400 if the either string parameter is null or empty, or if startDate is not a valid date.
     * 401 if no user is logged in.
     * 404 with "NonExistentTechnicalRespurce" if the specified technical resource does not exist within the logged user's organization.
     * 404 with "NonExistentCapability" if the specified capability does not exist within the logged user's organization.
     * 404 with "NonExistentCapabilityLevel" if the specified capability level does not exist within the specified capability.
     * 409 with "AlreadyAssignedTechnicalPosition" if the technical position has already been assigned to the technical
     *      resource.
     * 409 with "UserDoesNotHaveRequiredSkills" if the technical resource does not have assigned the skills required by
     *      the specified CapabilityLevel.
     * 200 if the technical position was correctly assigned.
     */
    @POST
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

        TechnicalResource assignee =
                this.technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(
                        technicalResourceEmail, loggedTechnicalResource.getOrganization().getUniqueIdentifier());

        if(assignee==null)
            return Response.status(Response.Status.NOT_FOUND).entity("NonExistentTechnicalResource").build();

        try {
            this.technicalResourceService
                    .assignTechnicalPositionToTechnicalResource(capability, capabilityLevel, assignee.getOrganization(), assignee, startDate);
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
