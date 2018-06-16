package cr.talent.core.organization.service.impl;

import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Invitation;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Organization;
import cr.talent.model.OrganizationState;
import cr.talent.model.TechnicalResource;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationException;
import cr.talent.support.exceptions.NotNullInviteLinkInOrganizationException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @Autowired
    private TechnicalResourceService technicalResourceService;

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
     * @see cr.talent.core.organization.service.OrganizationService#createOrganization(String, String, String)
     */
    public void createOrganization(String username, String uniqueIdentifier, String name) {
        Organization organization = new Organization();
        organization.setUniqueIdentifier(uniqueIdentifier);
        organization.setName(name);
        organization.setState(OrganizationState.ENABLED);
        organization.setTotalUsers(1);

        final String alreadyCreatedOrganizationExceptionMsg = "The organization with unique identifier \"" +
                organization.getUniqueIdentifier() + "\" has already been created within the system.";

        if (this.organizationDao.getOrganizationByUniqueIdentifier(uniqueIdentifier) != null)
            throw new AlreadyCreatedOrganizationException(alreadyCreatedOrganizationExceptionMsg);

        TechnicalResource technicalResource = this.technicalResourceService.getTechnicalResourceByUsername(username);
        technicalResource.setAdministrator(true);
        technicalResource.setOrganization(organization);
        this.technicalResourceService.update(technicalResource);
        this.organizationDao.create(organization);

        Authentication authentication = new UsernamePasswordAuthenticationToken(technicalResource, null, technicalResource.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
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

    /**
     * @see cr.talent.core.organization.service.OrganizationService#getValidInvitations(Organization)
     */
    @Override
    public Set<Invitation> getValidInvitations(Organization organization) {
        List<Invitation> invitationList = this.organizationDao.findValidInvitations(organization.getUniqueIdentifier());
        return new HashSet<>(invitationList);
    }

}