package cr.talent.core.invitation.service.impl;

import cr.talent.core.email.invitationEmail.service.InvitationEmailService;
import cr.talent.core.invitation.dao.InvitationDao;
import cr.talent.core.invitation.service.InvitationService;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.Invitation;
import cr.talent.model.Organization;
import cr.talent.model.TechnicalResource;
import cr.talent.support.exceptions.AlreadyRegisteredUserException;
import cr.talent.support.exceptions.EmptyDestinationEmailException;
import cr.talent.support.exceptions.LimitOfInvitationsReachedException;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Class that allows to test the {@link cr.talent.core.invitation.service.impl.InvitationServiceImpl} methods
 *
 * @author Elías Calderón
 */
public class InvitationServiceTest {

    @Test
    public void testCreateCall() {
        InvitationDao invitationDao = mock(InvitationDao.class);
        Invitation invitation = mock(Invitation.class);
        InvitationService invitationService = new InvitationServiceImpl();

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        invitationService.create(invitation);

        verify(invitationDao, times(1)).create(invitation);
    }

    @Test
    public void testRemoveCall() {
        InvitationDao invitationDao = mock(InvitationDao.class);
        Invitation invitation = mock(Invitation.class);
        InvitationService invitationService = new InvitationServiceImpl();

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        invitationService.remove(invitation);

        verify(invitationDao, times(1)).remove(invitation);
    }

    @Test
    public void testUpdateCall() {
        InvitationDao invitationDao = mock(InvitationDao.class);
        Invitation invitation = mock(Invitation.class);
        InvitationService invitationService = new InvitationServiceImpl();

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        invitationService.update(invitation);

        verify(invitationDao, times(1)).update(invitation);
    }

    @Test
    public void testInit() {
        InvitationService invitationService = new InvitationServiceImpl();
        InvitationDao invitationDao = mock(InvitationDao.class);

        ReflectionTestUtils.setField(invitationService, "invitationDao", invitationDao);
        ReflectionTestUtils.invokeMethod(invitationService, "init");
    }

    @Test
    public void testCreateInvitationsNotNullInvitation () {
        InvitationService invitationService = new InvitationServiceImpl();
        InvitationDao invitationDao = mock(InvitationDao.class);
        TechnicalResourceService technicalResourceService = mock(TechnicalResourceService.class);
        InvitationEmailService invitationEmailService = mock(InvitationEmailService.class);
        Organization organization = mock(Organization.class);
        String uniqueId = "testId";
        Set<TechnicalResource> technicalResourceSet = new HashSet<>();
        technicalResourceSet.add(mock(TechnicalResource.class));

        when(organization.getUniqueIdentifier()).thenReturn(uniqueId);
        when(organization.getResources()).thenReturn(technicalResourceSet);

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "invitationDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "technicalResourceService", technicalResourceService);
        ReflectionTestUtils.setField(invitationService, "invitationEmailService", invitationEmailService);

        List<String> emails = new ArrayList<>();
        emails.add("email1");
        emails.add("email2");

        Invitation invitation = mock(Invitation.class);
        for (String email : emails) {
            when(invitationDao.findInvitationByEmail(email)).thenReturn(invitation);
            when(technicalResourceService.getTechnicalResourceByUsername(email)).thenReturn(null);
        }

        invitationService.createInvitations(emails, organization);

        verify(invitationDao, times(2)).update(invitation);

    }

    @Test
    public void testCreateInvitationsNullInvitation () {
        InvitationService invitationService = new InvitationServiceImpl();
        InvitationDao invitationDao = mock(InvitationDao.class);
        TechnicalResourceService technicalResourceService = mock(TechnicalResourceService.class);
        InvitationEmailService invitationEmailService = mock(InvitationEmailService.class);
        OrganizationService organizationService = mock(OrganizationService.class);
        Organization organization = mock(Organization.class);
        String uniqueId = "testId";
        Set<TechnicalResource> technicalResourceSet = new HashSet<>();
        technicalResourceSet.add(mock(TechnicalResource.class));
        Set<Invitation> invitationSet = new HashSet<>();
        invitationSet.add(mock(Invitation.class));

        when(organization.getUniqueIdentifier()).thenReturn(uniqueId);
        when(organization.getResources()).thenReturn(technicalResourceSet);
        when(organizationService.getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier())).thenReturn(organization);
        when(organizationService.getValidInvitations(organization)).thenReturn(invitationSet);

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "invitationDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "technicalResourceService", technicalResourceService);
        ReflectionTestUtils.setField(invitationService, "invitationEmailService", invitationEmailService);
        ReflectionTestUtils.setField(invitationService, "organizationService", organizationService);

        List<String> emails = new ArrayList<>();
        emails.add("email1");
        emails.add("email2");

        Invitation invitation = mock(Invitation.class);
        for (String email : emails) {
            when(invitationDao.findInvitationByEmail(email)).thenReturn(null);
            when(technicalResourceService.getTechnicalResourceByUsername(email)).thenReturn(null);
        }

        invitationService.createInvitations(emails, organization);

        verify(invitationDao, times(0)).update(invitation);

    }

    @Test
    public void testCreateInvitationsEmptyEmail () {
        InvitationService invitationService = new InvitationServiceImpl();
        InvitationDao invitationDao = mock(InvitationDao.class);
        TechnicalResourceService technicalResourceService = mock(TechnicalResourceService.class);
        InvitationEmailService invitationEmailService = mock(InvitationEmailService.class);
        Organization organization = mock(Organization.class);

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "invitationDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "technicalResourceService", technicalResourceService);
        ReflectionTestUtils.setField(invitationService, "invitationEmailService", invitationEmailService);

        List<String> emails = new ArrayList<>();
        emails.add("");

        try {
            invitationService.createInvitations(emails, organization);
            fail();
        } catch (EmptyDestinationEmailException e) {
            // It's supposed to throw this exception
        }

    }

    @Test
    public void testCreateInvitationsLimitReached () {
        InvitationService invitationService = new InvitationServiceImpl();
        InvitationDao invitationDao = mock(InvitationDao.class);
        TechnicalResourceService technicalResourceService = mock(TechnicalResourceService.class);
        InvitationEmailService invitationEmailService = mock(InvitationEmailService.class);
        OrganizationService organizationService = mock(OrganizationService.class);
        Organization organization = mock(Organization.class);
        String uniqueId = "testId";
        Set<TechnicalResource> technicalResourceSet = new HashSet<>();
        Set<Invitation> invitationSet = new HashSet<>();

        for (int i = 0; i < 7; i++) {
            technicalResourceSet.add(mock(TechnicalResource.class));
            invitationSet.add(mock(Invitation.class));
        }

        when(organization.getUniqueIdentifier()).thenReturn(uniqueId);
        when(organization.getResources()).thenReturn(technicalResourceSet);
        when(organizationService.getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier())).thenReturn(organization);
        when(organizationService.getValidInvitations(organization)).thenReturn(invitationSet);

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "invitationDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "technicalResourceService", technicalResourceService);
        ReflectionTestUtils.setField(invitationService, "invitationEmailService", invitationEmailService);
        ReflectionTestUtils.setField(invitationService, "organizationService", organizationService);

        List<String> emails = new ArrayList<>();
        emails.add("email1");

        for (String email : emails) {
            when(invitationDao.findInvitationByEmail(email)).thenReturn(null);
        }

        try {
            invitationService.createInvitations(emails, organization);
            fail();
        } catch (LimitOfInvitationsReachedException e) {
            // It's supposed to throw this exception
        }

    }

    @Test
    public void testCreateInvitationsAlreadyRegisteredUser () {
        InvitationService invitationService = new InvitationServiceImpl();
        InvitationDao invitationDao = mock(InvitationDao.class);
        TechnicalResourceService technicalResourceService = mock(TechnicalResourceService.class);
        InvitationEmailService invitationEmailService = mock(InvitationEmailService.class);
        OrganizationService organizationService = mock(OrganizationService.class);
        Organization organization = mock(Organization.class);
        String uniqueId = "testId";
        Set<TechnicalResource> technicalResourceSet = new HashSet<>();
        Set<Invitation> invitationSet = new HashSet<>();

        when(organization.getUniqueIdentifier()).thenReturn(uniqueId);
        when(organization.getResources()).thenReturn(technicalResourceSet);
        when(organizationService.getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier())).thenReturn(organization);
        when(organizationService.getValidInvitations(organization)).thenReturn(invitationSet);

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "invitationDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "technicalResourceService", technicalResourceService);
        ReflectionTestUtils.setField(invitationService, "invitationEmailService", invitationEmailService);
        ReflectionTestUtils.setField(invitationService, "organizationService", organizationService);

        List<String> emails = new ArrayList<>();
        emails.add("email1");

        for (String email : emails) {
            when(invitationDao.findInvitationByEmail(email)).thenReturn(null);
            when(technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(
                    email, organization.getUniqueIdentifier())).thenReturn(mock(TechnicalResource.class));
        }

        try {
            invitationService.createInvitations(emails, organization);
            fail();
        } catch (AlreadyRegisteredUserException e) {
            // It's supposed to throw this exception
        }

    }

}
