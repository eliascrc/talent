package cr.talent.core.skillCategory.service.impl;

import cr.talent.core.skillCategory.dao.SkillCategoryDao;
import cr.talent.core.skillCategory.service.SkillCategoryService;
import cr.talent.model.Organization;
import cr.talent.model.Skill;
import cr.talent.model.SkillCategory;
import cr.talent.support.flexjson.JSONSerializerBuilder;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

/**
 * Default implementation of the {@link cr.talent.core.skillCategory.service.SkillCategoryService}.
 *
 * @author Otto Mena
 */
@Service("skillCategoryService")
@Transactional
public class SkillCategoryServiceImpl extends CrudServiceImpl<SkillCategory, String> implements SkillCategoryService {

    @Autowired
    private SkillCategoryDao skillCategoryDao;

    public void init() {
        setCrudDao(this.skillCategoryDao);
    }

    /**
     * @see cr.talent.core.skillCategory.service.SkillCategoryService#getSerializedSkillCategories(Organization)
     */
    @Override
    public String getSerializedSkillCategories(Organization organization) {
        Set<SkillCategory> skillCategories = new HashSet<>();

        for (SkillCategory organizationSkillCategory : organization.getSkillCategories()) {
            SkillCategory skillCategory = new SkillCategory();
            skillCategory.setName(organizationSkillCategory.getName());

            Set<Skill> skillCategorySkills = new HashSet<>();
            for (Skill skill : organizationSkillCategory.getSkills()) {
                if (organization.equals(skill.getOrganization()))
                    skillCategorySkills.add(skill);
            }

            skillCategory.setSkills(skillCategorySkills);
            skillCategories.add(skillCategory);
        }

        return JSONSerializerBuilder.getOrganizationSkillsSerializer().serialize(skillCategories);
    }
}
