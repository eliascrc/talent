package cr.talent.core.skillCategory.service;

import cr.talent.model.Organization;
import cr.talent.model.SkillCategory;
import cr.talent.support.service.CrudService;

/**
 * Provides business logic services related to {@link cr.talent.model.SkillCategory} entities.
 *
 * @author Otto Mena
 */
public interface SkillCategoryService extends CrudService<SkillCategory, String> {

    /**Used to delete the skill category of an organization.
     *
     * @param organization the instance of the organization from which the skill category skill be deleted.
     * @param skillCategory the instance of the skill category that will be deleted from the organization.
     */
    void deleteOrganizationSkillCategory (Organization organization, SkillCategory skillCategory);

    /**
     * Used for getting the skill categories of a given organization
     *
     * @param organization the organization that has the skill categories
     * @return a string with the organization's skills serialized, organized by category
     */
    String getSerializedSkillCategories(Organization organization);

}
