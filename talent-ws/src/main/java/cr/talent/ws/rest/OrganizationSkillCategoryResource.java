package cr.talent.ws.rest;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.skillCategory.service.SkillCategoryService;
import cr.talent.model.*;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.AlreadyCreatedSkillCategoryException;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new skill category for an organization.
 *
 * @author Josue Cubero
 */
@Component
@Scope("request")
@Path("/organization/skillCategory/")
public class OrganizationSkillCategoryResource {

    @Autowired
    private OrganizationService organizationService;

    /**
     * Receives the request for creating a new skill for the organization.
     *
     * @param name the skill category name.
     * @return 200 and skillCategory JSON information if the skill is correctly created, 400 if the name is empty or null,
     * 409 if the skillCategory is already created on the organization.
     */
    @POST
    @Path("/create")
    public Response createSkillCategory(@FormParam("name") String name) {

        if (StringUtils.isEmpty(name))
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        TechnicalResource technicalResource = SecurityUtils.getLoggedInTechnicalResource();

        //needed because of lazy load.
        Organization organization = this.organizationService.getOrganizationByUniqueIdentifier(technicalResource.getOrganization().getUniqueIdentifier());

        try {
            SkillCategory newSkillCategory = this.organizationService.createSkillCategory(name, organization);
            return Response.ok().entity(JSONSerializerBuilder.getSkillCategorySerializer().serialize(newSkillCategory)).build();
        } catch (AlreadyCreatedSkillCategoryException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

}
