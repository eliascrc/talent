package cr.talent.core.organization.service.impl;

import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.organization.service.OrganizationService}
 *
 * @author Elías Calderón
 */
@Service("organizationService")
@Transactional
public class OrganizationServiceImpl extends CrudServiceImpl<Organization, String> implements OrganizationService {

    @Autowired
    private OrganizationDao organizationDao;

    public void init() {
        setCrudDao(this.organizationDao);
    }

    /**
     * @see cr.talent.core.organization.service.OrganizationService#getOrganizationByUniqueIdentifier(String)
     */
    public Organization getOrganizationByUniqueIdentifier(String uniqueIdentifier) {
        return this.organizationDao.getOrganizationByUniqueIdentifier(uniqueIdentifier);
    }

    /**
     * @see cr.talent.core.organization.service.OrganizationService#createOrganization(Organization)
     */
    public String createOrganization(Organization organization) {

        final String alreadyCreatedOrganizationExceptionMsg = "The organization with unique identifier \"" +
                organization.getUniqueIdentifier() + "\" has already been created within the system.";

        if (this.organizationDao.getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier()) != null)
            throw new AlreadyCreatedOrganizationException(alreadyCreatedOrganizationExceptionMsg);

        return this.organizationDao.create(organization);
    }

}
