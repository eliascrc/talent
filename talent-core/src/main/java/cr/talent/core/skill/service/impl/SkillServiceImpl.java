package cr.talent.core.skill.service.impl;

import cr.talent.core.skill.dao.SkillDao;
import cr.talent.core.skill.service.SkillService;
import cr.talent.model.Skill;
import cr.talent.model.Skill;
import cr.talent.support.exceptions.AlreadyCreatedPredefinedSkillException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.skill.service.SkillService}.
 *
 * @author Elías Calderón
 */
@Service("skillService")
@Transactional
public class SkillServiceImpl extends CrudServiceImpl<Skill, String> implements SkillService {

    @Autowired
    private SkillDao skillDao;

    public void init() {
        setCrudDao(this.skillDao);
    }

    /**
     * @see cr.talent.core.skill.service.SkillService#createPredefinedSkill(Skill)
     */
    @Override
    public String createPredefinedSkill(Skill predefinedSkill) {

        final String alreadyCreatedPredefinedSkillExceptionMsg = "The predefined skill with name " +
                predefinedSkill.getName() + " has already been created within the system.";

        if (this.skillDao.getPredefinedSkillByName(predefinedSkill.getName()) != null)
            throw new AlreadyCreatedPredefinedSkillException(alreadyCreatedPredefinedSkillExceptionMsg);

        return this.skillDao.create(predefinedSkill);
    }
}
