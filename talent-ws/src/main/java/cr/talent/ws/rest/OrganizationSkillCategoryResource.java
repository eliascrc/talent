package cr.talent.ws.rest;

import cr.talent.core.skillCategory.service.SkillCategoryService;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.SkillCategory;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.SkillCategoryOfAnotherOrganizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Resource with a POST endpoint that manages an organization skillCategorys.
 *
 * @author Otto Mena
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
     *          404 if the unique identifier does not belong to any organization,
     *          409 if the organization skillCategory has already been created within the organization.
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
            // An organization skillCategory should always have an organization associated
            return Response.status(Response.Status.NOT_FOUND).build();
        }

    }

}
