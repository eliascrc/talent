package cr.talent.core.skillCategory.service.impl;

import cr.talent.core.capabilityLevel.dao.CapabilityLevelDao;
import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.core.security.technicalResource.dao.TechnicalResourceDao;
import cr.talent.core.skill.dao.SkillDao;
import cr.talent.core.skillCategory.dao.SkillCategoryDao;
import cr.talent.core.skillCategory.service.SkillCategoryService;
import cr.talent.model.*;
import cr.talent.support.service.impl.CrudServiceImpl;
import cr.talent.support.exceptions.SkillCategoryOfAnotherOrganizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

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

    @Autowired
    private SkillDao skillDao;

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private TechnicalResourceDao technicalResourceDao;

    @Autowired
    private CapabilityLevelDao capabilityLevelDao;

    public void init() {
        setCrudDao(this.skillCategoryDao);
    }

    /**
     *@see cr.talent.core.skillCategory.service.SkillCategoryService#deleteOrganizationSkillCategory(Organization, SkillCategory) (Organization, Skill Category)-
     */
    public void deleteOrganizationSkillCategory (Organization organization, SkillCategory skillCategory){

        SkillCategory skillCategoryToDelete = null;

        for (SkillCategory skillCategoryIterator : organization.getSkillCategories()) { //finds the skill category to delete
            if (skillCategoryIterator.equals(skillCategory))
                skillCategoryToDelete = skillCategoryIterator;
        }

        if (skillCategoryToDelete == null)
            throw new SkillCategoryOfAnotherOrganizationException();

        //delete the skills from the skill category and from the technical resource.
        for (TechnicalResource technicalResourceIterator : organization.getResources())
        {
            for (Skill skillIterator : skillCategoryToDelete.getSkills()) {
                technicalResourceIterator.getSkills().remove(skillIterator);
                skillIterator.setResources(null);
                skillDao.update(skillIterator);
            }
            technicalResourceDao.update(technicalResourceIterator);
        }

        //remove every required skill belonging to the skill category that will be deleted from each capability level.
        for (Skill skillIterator : skillCategoryToDelete.getSkills()) {
            for (Capability capabilityIterator : organization.getCapabilities()){
                for(CapabilityLevel capabilityLevelIterator : capabilityIterator.getLevelHierarchy()) {
                    capabilityLevelIterator.getRequiredSkills().remove(skillIterator);
                    capabilityLevelDao.update(capabilityLevelIterator);
                }
            }
        }


        organization.getSkillCategories().remove(skillCategoryToDelete);
        organizationDao.update(organization);
        skillCategoryDao.remove(skillCategoryToDelete);
    }
}
