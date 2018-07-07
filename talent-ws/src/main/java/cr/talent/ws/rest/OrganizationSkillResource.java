package cr.talent.ws.rest;

import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.skillCategory.service.SkillCategoryService;
import cr.talent.model.Skill;
import cr.talent.model.SkillCategory;
import cr.talent.model.SkillType;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.AlreadyCreatedSkillException;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that creates a new skill for an organization.
 *
 * @author Josue Cubero
 */
@Component
@Scope("request")
@Path("/organization/skill/")
public class OrganizationSkillResource {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private SkillCategoryService skillCategoryService;

    /**
     * Receives the request for creating a new skill for the organization.
     *
     * @param skillCategoryId the skill category id where the skill will be added.
     * @param skillName the skill name.
     * @param skillType the skill type.
     * @return 200 and skill JSON information if the skill is correctly created, 400 if any of the parameters are null,
     * 404 if the skillCategoryId does not correspond to any skill category within the organization, 409 if the skill is
     * already created on that category skill.
     */
    @POST
    @Path("/create")
    public Response createSkill(
            @FormParam("skillCategoryId") String skillCategoryId, @FormParam("skillName") String skillName, @FormParam("skillType") String skillType) {

        if (StringUtils.isEmpty(skillCategoryId) || StringUtils.isEmpty(skillName) || StringUtils.isEmpty(skillType))
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        SkillType skillType1;
        try {
            skillType1 = SkillType.valueOf(skillType); //gets the skil, type enum.
        } catch (Exception e){ //as it can throw different exceptions, generic Exception is used.
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        SkillCategory skillCategory = this.skillCategoryService.findById(skillCategoryId);

        if(skillCategory == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        TechnicalResource technicalResource = SecurityUtils.getLoggedInTechnicalResource();

        try {
            Skill newSkill = this.organizationService.createSkill(skillCategory, skillName, skillType1, technicalResource.getOrganization());
            return Response.ok().entity(JSONSerializerBuilder.getSkillSerializer().serialize(newSkill)).build();
        } catch (AlreadyCreatedSkillException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

}
