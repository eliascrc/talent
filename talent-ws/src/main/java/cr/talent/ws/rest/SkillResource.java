package cr.talent.ws.rest;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.skill.service.SkillService;
import cr.talent.model.Organization;
import cr.talent.model.OrganizationSkill;
import cr.talent.model.Skill;
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
 * Resource with a POST endpoint that creates a new skill
 *
 * @author Elías Calderón
 */
@Component
@Scope("request")
@Path("/skill")
public class SkillResource {

    @Autowired
    SkillService skillService;

    @Autowired
    OrganizationService organizationService;

    /**
     * Receives the request for creating a new skill.
     * @param organizationUniqueIdentifier the skill's organization unique identifier
     * @param name the skill's name
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

        OrganizationSkill skill = new OrganizationSkill();
        skill.setName(name);
        this.skillService.create(skill);

        return Response.ok().build();
    }

}
