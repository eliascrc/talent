package cr.talent.core.organization.service.impl;

import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.core.organization.dao.impl.HibernateOrganizationDao;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Invitation;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.model.User;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationException;
import cr.talent.support.exceptions.NotNullInviteLinkInOrganizationException;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Class that allows to test the {@link cr.talent.core.organization.service.impl.OrganizationServiceImpl} methods
 *
 * @author Elías Calderón
 */
public class OrganizationServiceTest {

    private static final String USERNAME = "majo.qbro@gmail.com";
    private static final String NAME = "Gorilla Logic";
    private static final String UNIQUE_IDENTIFIER = "gl";

    @Test
    public void testCreateCall() {
        OrganizationDao organizationDao = mock(OrganizationDao.class);
        Organization organization = mock(Organization.class);
        OrganizationService organizationService = new OrganizationServiceImpl();

        ReflectionTestUtils.setField(organizationService, "crudDao", organizationDao);
        organizationService.create(organization);

        verify(organizationDao, times(1)).create(organization);
    }

    @Test
    public void testRemoveCall() {
        OrganizationDao organizationDao = mock(OrganizationDao.class);
        Organization organization = mock(Organization.class);
        OrganizationService organizationService = new OrganizationServiceImpl();

        ReflectionTestUtils.setField(organizationService, "crudDao", organizationDao);
        organizationService.remove(organization);

        verify(organizationDao, times(1)).remove(organization);
    }

    @Test
    public void testUpdateCall() {
        OrganizationDao organizationDao = mock(OrganizationDao.class);
        Organization organization = mock(Organization.class);
        OrganizationService organizationService = new OrganizationServiceImpl();

        ReflectionTestUtils.setField(organizationService, "crudDao", organizationDao);
        organizationService.update(organization);

        verify(organizationDao, times(1)).update(organization);
    }

    @Test
    public void testInit() {
        OrganizationService organizationService = new OrganizationServiceImpl();
        OrganizationDao organizationDao = mock(OrganizationDao.class);

        ReflectionTestUtils.setField(organizationService, "organizationDao", organizationDao);
        ReflectionTestUtils.invokeMethod(organizationService, "init");
    }

    @Test
    public void testCreateOrganizationNullDaoQuery() {
        OrganizationDao organizationDao = mock(HibernateOrganizationDao.class);
        TechnicalResourceService technicalResourceService = mock(TechnicalResourceService.class);
        when(organizationDao.getOrganizationByUniqueIdentifier(UNIQUE_IDENTIFIER)).thenReturn(null);

        OrganizationService organizationService = new OrganizationServiceImpl();
        TechnicalResource technicalResource = new TechnicalResource();
        ReflectionTestUtils.setField(organizationService, "organizationDao", organizationDao);
        ReflectionTestUtils.setField(organizationService, "technicalResourceService", technicalResourceService);
        technicalResource.setStatus(User.Status.ACTIVE);
        when(technicalResourceService.getTechnicalResourceByUsernameWithNullOrganization(USERNAME)).thenReturn(technicalResource);
        organizationService.createOrganization(USERNAME, UNIQUE_IDENTIFIER, NAME);
    }

    @Test
    public void testCreateOrganizationNotNullDaoQuery() {
        OrganizationDao organizationDao = mock(HibernateOrganizationDao.class);
        Organization organization = new Organization();
        TechnicalResourceService technicalResourceService = mock(TechnicalResourceService.class);
        when(organizationDao.getOrganizationByUniqueIdentifier(UNIQUE_IDENTIFIER)).thenReturn(organization);
        Mockito.doReturn("created").when(organizationDao).create(organization);

        OrganizationService organizationService = new OrganizationServiceImpl();
        TechnicalResource technicalResource = new TechnicalResource();
        ReflectionTestUtils.setField(organizationService, "organizationDao", organizationDao);
        ReflectionTestUtils.setField(organizationService, "technicalResourceService", technicalResourceService);

        when(technicalResourceService.getTechnicalResourceByUsernameWithNullOrganization(USERNAME)).thenReturn(technicalResource);

        try {
            organizationService.createOrganization(USERNAME, UNIQUE_IDENTIFIER, NAME);
        } catch (AlreadyCreatedOrganizationException e) {
            // It's supposed to throw this exception
        }
    }

    @Test
    public void testGetOrganizationByUniqueIdentifier() {
        OrganizationDao organizationDao = mock(HibernateOrganizationDao.class);
        Organization organization = mock(Organization.class);
        String uniqueId = "uniqueId";

        OrganizationService organizationService = new OrganizationServiceImpl();
        ReflectionTestUtils.setField(organizationService, "organizationDao", organizationDao);
        ReflectionTestUtils.setField(organizationService, "crudDao", organizationDao);

        when(organizationDao.getOrganizationByUniqueIdentifier(uniqueId)).thenReturn(organization);

        organizationService.getOrganizationByUniqueIdentifier(uniqueId);

        verify(organizationDao, times(1)).getOrganizationByUniqueIdentifier(uniqueId);
    }

    @Test
    public void testcreateInviteLinkNullInviteLink() {
        OrganizationDao organizationDao = mock(HibernateOrganizationDao.class);
        Organization organization = mock(Organization.class);

        OrganizationService organizationService = new OrganizationServiceImpl();
        ReflectionTestUtils.setField(organizationService, "organizationDao", organizationDao);
        ReflectionTestUtils.setField(organizationService, "crudDao", organizationDao);

        when(organization.getInviteLink()).thenReturn(null);

        organizationService.createInviteLink(organization);

        verify(organizationDao, times(1)).update(organization);
    }

    @Test
    public void testcreateInviteLinkNotNullInviteLink() {
        OrganizationDao organizationDao = mock(HibernateOrganizationDao.class);
        Organization organization = mock(Organization.class);
        String inviteLink = "inviteLink";

        OrganizationService organizationService = new OrganizationServiceImpl();
        ReflectionTestUtils.setField(organizationService, "organizationDao", organizationDao);
        ReflectionTestUtils.setField(organizationService, "crudDao", organizationDao);

        when(organization.getInviteLink()).thenReturn(inviteLink);

        try {
            organizationService.createInviteLink(organization);
            fail();
        } catch (NotNullInviteLinkInOrganizationException e) {
            // Nothing, it should throw an exception.
        }
    }

    @Test
    public void testGetValidInvitations() {
        List<Invitation> invitationList = new ArrayList<>();
        OrganizationDao organizationDao = mock(HibernateOrganizationDao.class);
        Organization organization = mock(Organization.class);
        when(organizationDao.findValidInvitations(organization.getUniqueIdentifier())).thenReturn(invitationList);

        OrganizationService organizationService = new OrganizationServiceImpl();
        ReflectionTestUtils.setField(organizationService, "organizationDao", organizationDao);
        ReflectionTestUtils.setField(organizationService, "crudDao", organizationDao);

        organizationService.getValidInvitations(organization);

        verify(organizationDao, times(1)).findValidInvitations(organization.getUniqueIdentifier());
    }

}
