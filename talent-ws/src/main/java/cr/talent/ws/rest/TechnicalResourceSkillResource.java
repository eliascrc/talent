package cr.talent.ws.rest;


import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.core.skill.service.SkillService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Resource with one POST endpoint that handles the assign of skills to a technical resource.
 *
 * @author Josue Cubero
 */
@Component
@Scope("request")
@Path("technicalResource/skill")
public class TechnicalResourceSkillResource {

    @Autowired
    SkillService skillService;

    @Autowired
    TechnicalResourceService technicalResourceService;

    /**
     * Receives a request to assign skills to a technical resource.
     *
     * @param skills the skills to be assigned.
     * @return 400 if the list is empty, or if any skill is empty
     *         409 with "SkillAlreadyAssigned" if one or more skills have been assigned to the technical resource.
     *         404 with "NonExistentSkill" if one or more skills are non existent.
     *         401 if no user is logged in.
     *         200 if the skills were correctly assigned.
     */
    @POST
    @Path("/assign")
    public Response assignSkills(@FormParam("skills") List<String> skills) {

        if(skills.isEmpty())
            return Response.status(Response.Status.BAD_REQUEST).build();

        TechnicalResource lazySessionTechnicalResource = SecurityUtils.getLoggedInTechnicalResource();

        TechnicalResource technicalResource =
                this.technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(
                        lazySessionTechnicalResource.getUsername(),
                        lazySessionTechnicalResource.getOrganization().getUniqueIdentifier());

        if(technicalResource == null)
            return Response.status(Response.Status.UNAUTHORIZED).build();

        Organization organization = technicalResource.getOrganization();

        try {
            //this.organizationSkillService.assignSkillToTechnicalResource(skills, organization, technicalResource);
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
