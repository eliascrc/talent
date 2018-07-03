package cr.talent.core.email.invitationEmail.service.impl;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.core.email.invitationEmail.service.InvitationEmailService;
import cr.talent.model.Invitation;
import cr.talent.model.Organization;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class that allows to test the {@link cr.talent.core.email.invitationEmail.service.InvitationEmailService} methods
 *
 * @author Elias Calderon, Josue Cubero
 */
public class InvitationEmailTest {

    @Test
    public void sendPasswordResetMailTest(){
        InvitationEmailService invitationEmailService = new InvitationEmailServiceImpl();
        EmailSenderService emailSenderService = mock(EmailSenderService.class);
        Invitation invitation = mock(Invitation.class);
        Organization organization = mock(Organization.class);
        String talentEmail = "talent.cr.service@gmail.com";
        String organizationName = "monkey-labs";

        ReflectionTestUtils.setField(invitationEmailService, "emailSenderService", emailSenderService);
        ReflectionTestUtils.setField(invitationEmailService, "talentEmail", talentEmail);

        when(invitation.getEmail()).thenReturn(talentEmail);
        when(organization.getName()).thenReturn(organizationName);
        when(invitation.getOrganization()).thenReturn(organization);

        invitationEmailService.sendInvitationEmail(invitation);
    }

}
