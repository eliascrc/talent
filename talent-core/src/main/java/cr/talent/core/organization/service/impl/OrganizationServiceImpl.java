package cr.talent.core.organization.service.impl;

import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationException;
import cr.talent.support.exceptions.NotNullInviteLinkInOrganizationException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.UUID;

/**
 * Default implementation of the {@link cr.talent.core.organization.service.OrganizationService}
 *
 * @author Elías Calderón
 */
@Service("organizationService")
@Transactional
public class OrganizationServiceImpl extends CrudServiceImpl<Organization, String> implements OrganizationService {

    private static final String BASE_LINK = ".talent.cr/#/join?token=";
    private static final String HTTP_PREFIX = "http://";

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

    /**
     * @see cr.talent.core.organization.service.OrganizationService#createInviteLink(Organization)
     */
    @Override
    public String createInviteLink(Organization organization) {

        final String notNullInviteLinkInOrganizationExceptionMsg = "The invite link to create in the organization is not null";

        if (organization.getInviteLink() != null) {
            throw new NotNullInviteLinkInOrganizationException(notNullInviteLinkInOrganizationExceptionMsg);
        }

        String invitationToken = UUID.randomUUID().toString();
        String inviteLink = HTTP_PREFIX + organization.getUniqueIdentifier() + BASE_LINK + invitationToken;
        organization.setInviteLink(inviteLink);

        this.organizationDao.update(organization);

        return inviteLink;
    }

}