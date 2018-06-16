package cr.talent.core.organization.service.impl;

import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.core.organization.dao.impl.HibernateOrganizationDao;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationException;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

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

        when(technicalResourceService.getTechnicalResourceByUsername(USERNAME)).thenReturn(technicalResource);
        organizationService.createOrganization(USERNAME, UNIQUE_IDENTIFIER, NAME);

        verify(organizationDao, times(1)).getOrganizationByUniqueIdentifier(UNIQUE_IDENTIFIER);
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

        when(technicalResourceService.getTechnicalResourceByUsername(USERNAME)).thenReturn(technicalResource);

        try {
            organizationService.createOrganization(USERNAME, UNIQUE_IDENTIFIER, NAME);;
            fail();
        } catch (AlreadyCreatedOrganizationException e) {
            // It's supposed to throw this exception
        }

        verify(organizationDao, times(1)).getOrganizationByUniqueIdentifier(UNIQUE_IDENTIFIER);
    }

}
