package cr.talent.core.email.contactEmail.unauthenticatedContactEmail;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.core.email.contactEmail.unauthenticatedContactEmail.service.UnauthenticatedContactEmailService;
import cr.talent.core.email.contactEmail.unauthenticatedContactEmail.service.impl.UnauthenticatedContactEmailServiceImpl;
import cr.talent.model.ContactUsIssueType;
import cr.talent.model.UnauthenticatedContactEmail;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class that allows to test the {@link cr.talent.core.email.contactEmail.unauthenticatedContactEmail.service.UnauthenticatedContactEmailService} methods
 *
 * @author Fabián Roberto Leandro
 */
public class UnauthenticatedContactEmailServiceTest {

    @Test
    public void sendUnauthenticatedContactEmailTest(){
        UnauthenticatedContactEmailService contactEmailService = new UnauthenticatedContactEmailServiceImpl();
        UnauthenticatedContactEmail contactEmail = mock(UnauthenticatedContactEmail.class);

        EmailSenderService emailSenderService = mock(EmailSenderService.class);

        String talentEmail = "talent.cr.service@gmail.com";
        ContactUsIssueType issueType = ContactUsIssueType.ACCOUNT_CLOSING;

        ReflectionTestUtils.setField(contactEmailService, "emailSenderService", emailSenderService);
        ReflectionTestUtils.setField(contactEmailService, "talentEmail", talentEmail);
        Map< String, Object> model = mock(HashMap.class);

        when(contactEmail.getIssueType()).thenReturn(issueType);
        contactEmailService.sendUnauthenticatedContactEmail(contactEmail);
    }
}
