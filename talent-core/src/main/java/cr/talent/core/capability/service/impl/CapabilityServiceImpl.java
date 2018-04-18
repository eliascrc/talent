package cr.talent.core.capability.service.impl;

import cr.talent.core.capability.dao.CapabilityDao;
import cr.talent.core.capability.service.CapabilityService;
import cr.talent.model.Capability;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.capability.service.CapabilityService}.
 *
 * @author Elías Calderón
 */
@Service("capabilityService")
@Transactional
public class CapabilityServiceImpl extends CrudServiceImpl<Capability, String> implements CapabilityService {

    @Autowired
    private CapabilityDao capabilityDao;

    public void init() {
        setCrudDao(this.capabilityDao);
    }

}
