package cr.talent.ws.rest;


import cr.talent.core.organizationSkill.service.OrganizationSkillService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Organization;
import cr.talent.model.OrganizationSkill;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.*;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

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
    OrganizationSkillService organizationSkillService;

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
            this.organizationSkillService.assignSkillToTechnicalResource(skills, organization, technicalResource);
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

    /**
     * Receives a request to obtain a a technical resource's skills.
     *
     * @param technicalResourceEmail the email (username) of the resource whose skills will be returned.
     * @param organizationIdentifier the unique identifier of the user's organization
     * @return 400 if the list is empty, or if any skill is empty
     *         409 with "SkillAlreadyAssigned" if one or more skills have been assigned to the technical resource.
     *         404 with "NonExistentSkill" if one or more skills are non existent.
     *         401 if no user is logged in.
     *         200 if the skills were correctly assigned.
     */
    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSkills(
            @QueryParam("technicalResource") String technicalResourceEmail,
            @QueryParam("organizationIdentifier") String organizationIdentifier){

        if(StringUtils.isEmpty(organizationIdentifier)||StringUtils.isEmpty(technicalResourceEmail))
            return Response.status(Response.Status.BAD_REQUEST).build(); // The request is malformed

        TechnicalResource technicalResource =
                this.technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(
                        technicalResourceEmail,organizationIdentifier);

        if(technicalResource==null)
            return Response.status(Response.Status.NOT_FOUND).build(); // The technical resource was not found

        Set<OrganizationSkill> assignedSkills = technicalResource.getSkills();
        if(assignedSkills.isEmpty())
            return Response.status(Response.Status.NO_CONTENT).build(); // The resource has no assigned skills

        String organizationJson = JSONSerializerBuilder.getOrganizationSkillSerializer().serialize(assignedSkills);
        return Response.status(200).entity(organizationJson).build();
    }
}
