package cr.talent.core.technicalPosition.service.impl;

import cr.talent.core.technicalPosition.dao.TechnicalPositionDao;
import cr.talent.core.technicalPosition.service.TechnicalPositionService;
import cr.talent.model.*;
import cr.talent.support.exceptions.AlreadyAssignedTechnicalPositionException;
import cr.talent.support.exceptions.NonExistentCapabilityException;
import cr.talent.support.exceptions.NonExistentCapabilityLevelException;
import cr.talent.support.exceptions.UserDoesNotHaveRequiredSkillsException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.technicalPosition.service.TechnicalPositionService}.
 *
 * @author Elías Calderón
 */
@Service("technicalPositionService")
@Transactional
public class TechnicalPositionServiceImpl extends CrudServiceImpl<TechnicalPosition, String> implements TechnicalPositionService {

    @Autowired
    private TechnicalPositionDao technicalPositionDao;

    public void init() {
        setCrudDao(this.technicalPositionDao);
    }

}
