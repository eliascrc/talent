package cr.talent.ws.rest;

import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.core.technicalPosition.service.TechnicalPositionService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.AlreadyAssignedSkillException;
import cr.talent.support.exceptions.EmptySkillException;
import cr.talent.support.exceptions.NonExistentSkillException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

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

    @Autowired
    TechnicalPositionService technicalPositionService;

    /**
     * Receives a request to assign a technical position to a technical resource.
     *
     * @param technicalPosition      the technical position to be assigned.
     * @param technicalResourceEmail the technical resource to be assigned the position.
     * @return 400 if the either parameter is null or empty
     * 409 with "TechnicalPositionAlreadyAssigned" if the technical position has already been assigned to the technical resource.
     * 404 with "NonExistentTechnicalPosition" if the technical position is non existent.
     * 401 if no user is logged in.
     * 200 if the technical position was correctly assigned.
     */
    @POST
    @Path("/assign")
    public Response assignProjectPosition(@FormParam("technicalPosition") String technicalPosition,
                                          @FormParam("technicalResource") String technicalResourceEmail) {

        if (StringUtils.isEmpty(technicalPosition) || StringUtils.isEmpty(technicalResourceEmail))
            return Response.status(Response.Status.BAD_REQUEST).build();

        // Get the logged user in order to know the organization
        TechnicalResource loggedTechnicalResource = SecurityUtils.getLoggedInTechnicalResource();

        if (loggedTechnicalResource == null)
            return Response.status(Response.Status.UNAUTHORIZED).build();

        TechnicalResource asignee =
                this.technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(
                        technicalResourceEmail, loggedTechnicalResource.getOrganization().getUniqueIdentifier());

        try {
            this.technicalPositionService
                    .assignTechnicalPositionToTechnicalResource(technicalPosition, asignee.getOrganization(), asignee);
        } catch (NonExistentSkillException e) {
            return Response.status(Response.Status.NOT_FOUND).
                    entity("NonExistentSkill").build();
        } catch (AlreadyAssignedSkillException e) {
            return Response.status(Response.Status.CONFLICT).
                    entity("AlreadyAssignedSkill").build();
        } catch (EmptySkillException e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        return Response.ok().build();
    }
}
