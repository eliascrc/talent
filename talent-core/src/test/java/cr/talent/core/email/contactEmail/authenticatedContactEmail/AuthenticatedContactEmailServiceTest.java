package cr.talent.core.email.contactEmail.authenticatedContactEmail;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.core.email.contactEmail.authenticatedContactEmail.service.AuthenticatedContactEmailService;
import cr.talent.core.email.contactEmail.authenticatedContactEmail.service.impl.AuthenticatedContactEmailServiceImpl;
import cr.talent.model.AuthenticatedContactEmail;
import cr.talent.model.ContactUsIssueType;
import cr.talent.model.TechnicalResource;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class that allows to test the {@link cr.talent.core.email.contactEmail.authenticatedContactEmail.service.AuthenticatedContactEmailService} methods
 *
 * @author Fabián Roberto Leandro
 */
public class AuthenticatedContactEmailServiceTest {

    @Test
    public void sendAuthenticatedContactEmailTest(){
        AuthenticatedContactEmailService contactEmailService = new AuthenticatedContactEmailServiceImpl();
        AuthenticatedContactEmail contactEmail = mock(AuthenticatedContactEmail.class);
        ContactUsIssueType issueType = ContactUsIssueType.ACCOUNT_CLOSING;

        EmailSenderService emailSenderService = mock(EmailSenderService.class);

        TechnicalResource technicalResource = mock(TechnicalResource.class);
        String talentEmail = "talent.cr.service@gmail.com";

        ReflectionTestUtils.setField(contactEmailService, "emailSenderService", emailSenderService);
        ReflectionTestUtils.setField(contactEmailService, "talentEmail", talentEmail);
        Map< String, Object> model = mock(HashMap.class);

        when(contactEmail.getTechnicalResource()).thenReturn(technicalResource);
        when(contactEmail.getIssueType()).thenReturn(issueType);
        contactEmailService.sendAuthenticatedContactEmail(contactEmail);
    }
}
