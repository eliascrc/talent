package cr.talent.core.capability.service.impl;

import cr.talent.core.capability.dao.CapabilityDao;
import cr.talent.core.capability.service.CapabilityService;
import cr.talent.model.Capability;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationCapabilityException;
import cr.talent.support.exceptions.AlreadyCreatedPredefinedCapabilityException;
import cr.talent.support.exceptions.NotNullOrganizationInPredefinedCapabilityException;
import cr.talent.support.exceptions.NullOrganizationInOrganizationCapabilityException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Set;

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
    public String createOrganizationCapability(Capability organizationCapability) {

        final String nullOrganizationInOrganizationCapabilityExceptionMsg = "An organization capability with the name " +
                organizationCapability.getName() + " is tried to be created without a related organization.";
        final String alreadyCreatedOrganizationCapabilityExceptionMsg = "An organization capability with the name \"" +
                organizationCapability.getName() + "\" is already registered within the organization \""
                + organizationCapability.getOrganization().getName() + "\"";

        if (organizationCapability.getOrganization() == null)
            throw new NullOrganizationInOrganizationCapabilityException(nullOrganizationInOrganizationCapabilityExceptionMsg);

        String organizationId = organizationCapability.getOrganization().getId();
        if (this.capabilityDao.getOrganizationCapabilityByName(organizationId, organizationCapability.getName()) != null)
            throw new AlreadyCreatedOrganizationCapabilityException(alreadyCreatedOrganizationCapabilityExceptionMsg);

        return this.capabilityDao.create(organizationCapability);
    }

    /**
     * @see cr.talent.core.capability.service.CapabilityService#createPredefinedCapability(Capability)
     */
    @Override
    public String createPredefinedCapability(Capability predefinedCapability) {

        final String notNullOrganizationInPredefinedCapabilityExceptionMsg = "A predefined capability with the name " +
                predefinedCapability.getName() + " is tried to be created with a not null organization.";
        final String alreadyCreatedPredefinedCapabilityExceptionMsg = "A predefined capability with the name \"" +
                predefinedCapability.getName() + "\" is already registered within the system";

        if (predefinedCapability.getOrganization() != null)
            throw new NotNullOrganizationInPredefinedCapabilityException(notNullOrganizationInPredefinedCapabilityExceptionMsg);

        if (this.capabilityDao.getPredefinedCapabilityByName(predefinedCapability.getName()) != null)
            throw new AlreadyCreatedPredefinedCapabilityException(alreadyCreatedPredefinedCapabilityExceptionMsg);

        return this.capabilityDao.create(predefinedCapability);
    }

    @Override
    public Set<Capability> getCapabilitiesFromOrganization(Organization organization) {
        return organization.getCapabilities();
    }
}
