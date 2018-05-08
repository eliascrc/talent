package cr.talent.core.capability.service.impl;

import cr.talent.core.capability.dao.CapabilityDao;
import cr.talent.core.capability.service.CapabilityService;
import cr.talent.model.Capability;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationCapabilityException;
import cr.talent.support.exceptions.AlreadyCreatedPredefinedCapabilityException;
import cr.talent.support.exceptions.NotNullOrganizationInPredefinedCapabilityException;
import cr.talent.support.exceptions.NullOrganizationInOrganizationCapabilityException;
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

    /**
     * @see cr.talent.core.capability.service.CapabilityService#createOrganizationCapability(Capability)
     */
    @Override
    public String createOrganizationCapability(Capability organizationCapability)
            throws NullOrganizationInOrganizationCapabilityException, AlreadyCreatedOrganizationCapabilityException {

        if (organizationCapability.getOrganization() == null)
            throw new NullOrganizationInOrganizationCapabilityException();

        String organizationId = organizationCapability.getOrganization().getId();
        if (this.capabilityDao.getOrganizationCapabilityByName(organizationId, organizationCapability.getName()) != null)
            throw new AlreadyCreatedOrganizationCapabilityException();

        return this.capabilityDao.create(organizationCapability);
    }

    /**
     * @see cr.talent.core.capability.service.CapabilityService#createPredefinedCapability(Capability)
     */
    @Override
    public String createPredefinedCapability(Capability predefinedCapability)
            throws NotNullOrganizationInPredefinedCapabilityException, AlreadyCreatedPredefinedCapabilityException {

        if (predefinedCapability.getOrganization() != null)
            throw new NotNullOrganizationInPredefinedCapabilityException();

        if (this.capabilityDao.getPredefinedCapabilityByName(predefinedCapability.getName()) != null)
            throw new AlreadyCreatedPredefinedCapabilityException();

        return this.capabilityDao.create(predefinedCapability);
    }
}
