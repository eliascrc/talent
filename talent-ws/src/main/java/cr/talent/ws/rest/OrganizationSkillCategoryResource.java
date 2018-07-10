package cr.talent.ws.rest;

import cr.talent.core.skillCategory.service.SkillCategoryService;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.SkillCategory;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.SkillCategoryOfAnotherOrganizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import cr.talent.model.*;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.AlreadyCreatedSkillCategoryException;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import org.springframework.util.StringUtils;


import javax.ws.rs.core.Response;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Resource with a POST endpoint that manages an organization skillCategories.
 *
 * @author Otto Mena, Josue Cubero
 */
@Component
@Scope("request")
@Path("/organization/skillCategory")
public class OrganizationSkillCategoryResource {

    @Autowired
    private SkillCategoryService skillCategoryService;

    @Autowired
    private OrganizationService organizationService;

    /**
     * Receives the request for deleting an organization's skillCategory.
     * @param organizationUniqueIdentifier the skillCategory's organization unique identifier
     * @param skillCategoryId the skillCategory's name.
     * @return 200 if the organization skillCategory is correctly deleted,
     *          400 if any of the parameters are null or empty strings,
     *          404 if the unique identifier does not belong to any organization, or the skill category does not exist,
     *          or the skill category does not belong to the organization.
     */
    @POST
    @Path("/delete")
    public Response deleteOrganizationSkillCategory(
            @FormParam("organizationUniqueIdentifier") String organizationUniqueIdentifier,
            @FormParam("skillCategoryId") String skillCategoryId) {

        if (organizationUniqueIdentifier == null || skillCategoryId == null
                || organizationUniqueIdentifier.equals("") || skillCategoryId.equals(""))
            return Response.status(Response.Status.BAD_REQUEST).build(); //Form Parameters should not be null or empty

        Organization organization = organizationService.getOrganizationByUniqueIdentifier(organizationUniqueIdentifier);
        if (organization == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        SkillCategory skillCategory = skillCategoryService.findById(skillCategoryId);
        if (skillCategory == null)
            return Response.status(Response.Status.NOT_FOUND).build();


        try {

            this.skillCategoryService.deleteOrganizationSkillCategory(organization, skillCategory);
            return Response.ok().build();

        } catch (SkillCategoryOfAnotherOrganizationException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }



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
