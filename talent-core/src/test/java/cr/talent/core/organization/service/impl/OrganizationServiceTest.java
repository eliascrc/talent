package cr.talent.core.organization.service.impl;

import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.core.organization.dao.impl.HibernateOrganizationDao;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.model.Invitation;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationException;
import org.junit.Test;
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
        Organization organization = mock(Organization.class);
        when(organizationDao.getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier())).thenReturn(null);
        when(organizationDao.create(any())).thenReturn("1234");

        OrganizationService organizationService = new OrganizationServiceImpl();
        ReflectionTestUtils.setField(organizationService, "organizationDao", organizationDao);

        try {
            assertEquals("1234", organizationService.createOrganization(organization));
        } catch (AlreadyCreatedOrganizationException e) {
            fail();
        }

        verify(organizationDao, times(1)).getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier());
    }

    @Test
    public void testCreateOrganizationNotNullDaoQuery() {
        OrganizationDao organizationDao = mock(HibernateOrganizationDao.class);
        Organization organization = mock(Organization.class);
        when(organizationDao.getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier())).thenReturn(organization);

        OrganizationService organizationService = new OrganizationServiceImpl();
        ReflectionTestUtils.setField(organizationService, "organizationDao", organizationDao);
        ReflectionTestUtils.setField(organizationService, "crudDao", organizationDao);

        try {
            organizationService.createOrganization(organization);
            fail();
        } catch (AlreadyCreatedOrganizationException e) {
            // It's supposed to throw this exception
        }

        verify(organizationDao, times(1)).getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier());
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
