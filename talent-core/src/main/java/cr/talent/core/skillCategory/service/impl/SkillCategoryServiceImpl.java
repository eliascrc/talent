package cr.talent.core.skillCategory.service.impl;

import cr.talent.core.skillCategory.dao.SkillCategoryDao;
import cr.talent.core.skillCategory.service.SkillCategoryService;
import cr.talent.model.SkillCategory;
import cr.talent.support.service.impl.CrudServiceImpl;
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

    public void init() {
        setCrudDao(this.skillCategoryDao);
    }
}
