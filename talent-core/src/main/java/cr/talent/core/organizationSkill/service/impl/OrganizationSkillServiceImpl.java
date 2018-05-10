package cr.talent.core.organizationSkill.service.impl;

import cr.talent.core.organizationSkill.dao.OrganizationSkillDao;
import cr.talent.core.organizationSkill.service.OrganizationSkillService;
import cr.talent.model.OrganizationSkill;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.organizationSkill.service.OrganizationSkillService}.
 *
 * @author Elías Calderón
 */
@Service("organizationSkillService")
@Transactional
public class OrganizationSkillServiceImpl extends CrudServiceImpl<OrganizationSkill, String> implements OrganizationSkillService {

    @Autowired
    private OrganizationSkillDao organizationSkillDao;

    public void init() {
        setCrudDao(this.organizationSkillDao);
    }

}
