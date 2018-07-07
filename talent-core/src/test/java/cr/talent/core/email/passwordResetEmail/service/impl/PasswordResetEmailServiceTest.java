package cr.talent.core.email.passwordResetEmail.service.impl;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.core.email.passwordResetEmail.service.PasswordResetEmailService;
import cr.talent.model.Email;
import cr.talent.model.PasswordResetRequest;
import cr.talent.model.TechnicalResource;
import org.apache.commons.collections.map.HashedMap;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Class that allows to test the PasswordResetEmailServiceImpl methods
 *
 * @author Maria Jose Cubero.
 */
public class PasswordResetEmailServiceTest {

    @Test
    public void sendPasswordResetMailTest(){
        PasswordResetEmailService passwordResetEmailService = new PasswordResetEmailServiceImpl();
        EmailSenderService emailSenderService = mock(EmailSenderService.class);
        PasswordResetRequest passwordResetRequest = mock(PasswordResetRequest.class);
        TechnicalResource technicalResource = mock(TechnicalResource.class);
        String talentEmail = "talent.cr.service@gmail.com";

        ReflectionTestUtils.setField(passwordResetEmailService, "emailSenderService", emailSenderService);
        ReflectionTestUtils.setField(passwordResetEmailService, "talentEmail", talentEmail);
        Map< String, Object> model = mock(HashMap.class);

        when(passwordResetRequest.getTechnicalResource()).thenReturn(technicalResource);
        passwordResetEmailService.sendPasswordResetMail(talentEmail, passwordResetRequest);
    }

}
