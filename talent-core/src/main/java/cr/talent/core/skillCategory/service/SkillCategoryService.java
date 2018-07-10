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

    /**
     * Used for getting the skill categories of a given organization
     *
     * @param organization the organization that has the skill categories
     * @return a string with the organization's skills serialized, organized by category
     */
    String getSerializedSkillCategories(Organization organization);

}
