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
import cr.talent.support.exceptions.InvalidJSONException;
import cr.talent.support.exceptions.LimitOfInvitationsReachedException;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;

/**
 * Class that allows to test the {@link cr.talent.core.invitation.service.impl.InvitationServiceImpl} methods
 *
 * @author Elías Calderón, Josue Cubero.
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
        OrganizationService organizationService = mock(OrganizationService.class);
        Organization organization = mock(Organization.class);
        Set<TechnicalResource> technicalResourceSet = new HashSet<>();
        technicalResourceSet.add(mock(TechnicalResource.class));
        Invitation invitation = mock(Invitation.class);
        String uniqueId = "testId";

        when(organization.getUniqueIdentifier()).thenReturn(uniqueId);
        when(organization.getResources()).thenReturn(technicalResourceSet);
        when(organizationService.getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier())).thenReturn(organization);
        // this findInvitationByEmail will be done manually (and not iterating the JSON) because otherwise we'll have to test if
        // JSON is valid in here and that should be on the serviceImpl.
        when(invitationDao.findInvitationByEmail("jo96guerre@gmail.com")).thenReturn(invitation);
        when(invitationDao.findInvitationByEmail("jo96cube@hotmail.com")).thenReturn(invitation);
        when(invitationDao.findInvitationByEmail("xbaseucr@gmail.com")).thenReturn(invitation);

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "invitationDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "technicalResourceService", technicalResourceService);
        ReflectionTestUtils.setField(invitationService, "invitationEmailService", invitationEmailService);
        ReflectionTestUtils.setField(invitationService, "organizationService", organizationService);

        String invitations = "{\"invitations\":[{\"email\":\"jo96guerre@gmail.com\",\"firstName\":\"Joaquin\",\"lastName\":\"Guerrero\",\"token\":\"token\",\"isValid\":true}," +
                                               "{\"email\":\"jo96cube@hotmail.com\",\"firstName\":\"Josue\",\"lastName\":\"Cubero\",\"token\":\"toke\",\"isValid\":false}," +
                                               "{\"email\":\"xbaseucr@gmail.com\",\"firstName\":\"Michael\",\"lastName\":\"Kiske\",\"token\":\"token222\",\"isValid\":true}" +
                              "]}";
        invitationService.createInvitations(invitations, organization);

        verify(invitationDao, times(3)).update(invitation);
    }

    @Test
    public void testCreateInvitationsNullInvitation () {

        InvitationService invitationService = new InvitationServiceImpl();
        InvitationDao invitationDao = mock(InvitationDao.class);
        TechnicalResourceService technicalResourceService = mock(TechnicalResourceService.class);
        InvitationEmailService invitationEmailService = mock(InvitationEmailService.class);
        OrganizationService organizationService = mock(OrganizationService.class);
        Organization organization = mock(Organization.class);
        Set<TechnicalResource> technicalResourceSet = new HashSet<>();
        technicalResourceSet.add(mock(TechnicalResource.class));
        String uniqueId = "testId";

        when(organization.getUniqueIdentifier()).thenReturn(uniqueId);
        when(organization.getResources()).thenReturn(technicalResourceSet);
        when(organizationService.getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier())).thenReturn(organization);

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "invitationDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "technicalResourceService", technicalResourceService);
        ReflectionTestUtils.setField(invitationService, "invitationEmailService", invitationEmailService);
        ReflectionTestUtils.setField(invitationService, "organizationService", organizationService);

        String invitations = null;
        try {
            invitationService.createInvitations(invitations, organization);
            fail();
        } catch (InvalidJSONException e){
            // this is the exception that should be thrown.
        }
    }

    @Test
    public void testCreateInvitationsEmptyInvitation () {

        InvitationService invitationService = new InvitationServiceImpl();
        InvitationDao invitationDao = mock(InvitationDao.class);
        TechnicalResourceService technicalResourceService = mock(TechnicalResourceService.class);
        InvitationEmailService invitationEmailService = mock(InvitationEmailService.class);
        OrganizationService organizationService = mock(OrganizationService.class);
        Organization organization = mock(Organization.class);
        Set<TechnicalResource> technicalResourceSet = new HashSet<>();
        technicalResourceSet.add(mock(TechnicalResource.class));
        String uniqueId = "testId";

        when(organization.getUniqueIdentifier()).thenReturn(uniqueId);
        when(organization.getResources()).thenReturn(technicalResourceSet);
        when(organizationService.getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier())).thenReturn(organization);

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "invitationDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "technicalResourceService", technicalResourceService);
        ReflectionTestUtils.setField(invitationService, "invitationEmailService", invitationEmailService);
        ReflectionTestUtils.setField(invitationService, "organizationService", organizationService);

        String invitations = "";
        try {
            invitationService.createInvitations(invitations, organization);
            fail();
        } catch (InvalidJSONException e){
            // this is the exception that should be thrown.
        }

    }

    @Test
    public void testCreateInvitationsLimitReachedInvitation () {

        InvitationService invitationService = new InvitationServiceImpl();
        InvitationDao invitationDao = mock(InvitationDao.class);
        TechnicalResourceService technicalResourceService = mock(TechnicalResourceService.class);
        InvitationEmailService invitationEmailService = mock(InvitationEmailService.class);
        OrganizationService organizationService = mock(OrganizationService.class);
        Organization organization = mock(Organization.class);
        Set<TechnicalResource> technicalResourceSet = new HashSet<>();
        technicalResourceSet.add(mock(TechnicalResource.class));
        String uniqueId = "testId";

        Set<Invitation> invitationSet = new HashSet<>();

        for (int i = 0; i < 8; i++) {
            technicalResourceSet.add(mock(TechnicalResource.class));
            invitationSet.add(mock(Invitation.class));
        }

        when(organization.getUniqueIdentifier()).thenReturn(uniqueId);
        when(organization.getResources()).thenReturn(technicalResourceSet);
        when(organizationService.getOrganizationByUniqueIdentifier(organization.getUniqueIdentifier())).thenReturn(organization);
        when(organizationService.getValidInvitations(organization)).thenReturn(invitationSet);
        // this findInvitationByEmail will be done manually (and not iterating the JSON) because otherwise we'll have to test if
        // JSON is valid in here and that should be on the serviceImpl.
        when(invitationDao.findInvitationByEmail("jo96guerre@gmail.com")).thenReturn(null);
        when(invitationDao.findInvitationByEmail("jo96cube@hotmail.com")).thenReturn(null);
        when(invitationDao.findInvitationByEmail("xbaseucr@gmail.com")).thenReturn(null);

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "invitationDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "technicalResourceService", technicalResourceService);
        ReflectionTestUtils.setField(invitationService, "invitationEmailService", invitationEmailService);
        ReflectionTestUtils.setField(invitationService, "organizationService", organizationService);

        String invitations = "{\"invitations\":[{\"email\":\"jo96guerre@gmail.com\",\"firstName\":\"Joaquin\",\"lastName\":\"Guerrero\",\"token\":\"token\",\"isValid\":true}," +
                "{\"email\":\"jo96cube@hotmail.com\",\"firstName\":\"Josue\",\"lastName\":\"Cubero\",\"token\":\"toke\",\"isValid\":false}," +
                "{\"email\":\"xbaseucr@gmail.com\",\"firstName\":\"Michael\",\"lastName\":\"Kiske\",\"token\":\"token222\",\"isValid\":true}" +
                "]}";

        try {
            invitationService.createInvitations(invitations, organization);
            fail();
        } catch (LimitOfInvitationsReachedException e){
            // this exception should be thrown.
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
        // this findInvitationByEmail will be done manually (and not iterating the JSON) because otherwise we'll have to test if
        // JSON is valid in here and that should be on the serviceImpl.
        when(invitationDao.findInvitationByEmail("jo96guerre@gmail.com")).thenReturn(null);
        when(invitationDao.findInvitationByEmail("jo96cube@hotmail.com")).thenReturn(null);
        when(invitationDao.findInvitationByEmail("xbaseucr@gmail.com")).thenReturn(null);
        when(technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier("jo96guerre@gmail.com",
                organization.getUniqueIdentifier())).thenReturn(mock(TechnicalResource.class));

        ReflectionTestUtils.setField(invitationService, "crudDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "invitationDao", invitationDao);
        ReflectionTestUtils.setField(invitationService, "technicalResourceService", technicalResourceService);
        ReflectionTestUtils.setField(invitationService, "invitationEmailService", invitationEmailService);
        ReflectionTestUtils.setField(invitationService, "organizationService", organizationService);

        String invitations = "{\"invitations\":[{\"email\":\"jo96guerre@gmail.com\",\"firstName\":\"Joaquin\",\"lastName\":\"Guerrero\",\"token\":\"token\",\"isValid\":true}," +
                "{\"email\":\"jo96cube@hotmail.com\",\"firstName\":\"Josue\",\"lastName\":\"Cubero\",\"token\":\"toke\",\"isValid\":false}," +
                "{\"email\":\"xbaseucr@gmail.com\",\"firstName\":\"Michael\",\"lastName\":\"Kiske\",\"token\":\"token222\",\"isValid\":true}" +
                "]}";

        try {
            invitationService.createInvitations(invitations, organization);
            fail();
        } catch (AlreadyRegisteredUserException e) {
            // It's supposed to throw this exception
        }

    }

}
