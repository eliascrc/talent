package cr.talent.core.capabilityLevel.service.impl;

import cr.talent.core.capabilityLevel.dao.CapabilityLevelDao;
import cr.talent.core.capabilityLevel.service.CapabilityLevelService;
import cr.talent.model.CapabilityLevel;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.capabilityLevel.service.CapabilityLevelService}.
 *
 * @author Elías Calderón
 */
@Service("capabilityLevelService")
@Transactional
public class CapabilityLevelServiceImpl extends CrudServiceImpl<CapabilityLevel, String> implements CapabilityLevelService {

    @Autowired
    private CapabilityLevelDao capabilityLevelDao;

    public void init() {
        setCrudDao(this.capabilityLevelDao);
    }

}
