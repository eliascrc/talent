package cr.talent.core.skill.service.impl;

import cr.talent.core.skill.dao.SkillDao;
import cr.talent.core.skill.service.SkillService;
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

}
