package cr.talent.core.organizationSkillCategory.service.impl;

import cr.talent.core.organizationSkillCategory.dao.OrganizationSkillCategoryDao;
import cr.talent.core.organizationSkillCategory.service.OrganizationSkillCategoryService;
import cr.talent.model.OrganizationSkillCategory;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.organizationSkillCategory.service.OrganizationSkillCategoryService}.
 *
 * @author Elías Calderón
 */
@Service("organizationSkillCategoryService")
@Transactional
public class OrganizationSkillCategoryServiceImpl extends CrudServiceImpl<OrganizationSkillCategory, String> implements OrganizationSkillCategoryService {

    @Autowired
    private OrganizationSkillCategoryDao organizationSkillCategoryDao;

    public void init() {
        setCrudDao(this.organizationSkillCategoryDao);
    }

}
