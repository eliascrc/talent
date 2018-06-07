package cr.talent.core.email.signUpConfirmationEmail.service.impl;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.core.email.signUpConfirmationEmail.service.SignUpConfirmationEmailService;
import cr.talent.model.SignUpConfirmationMessage;
import cr.talent.model.TechnicalResource;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Class that allows to test the SignUpConfirmationEmailServiceImpl methods
 *
 * @author Daniel Montes de Oca
 */
public class SignUpConfirmationEmailServiceTest {

    @Test
    public void sendSignUpConfirmationEmailTest(){
        SignUpConfirmationEmailService signUpConfirmationEmailService = new SignUpConfirmationEmailServiceImpl();
        EmailSenderService emailSenderService = mock(EmailSenderService.class);
        SignUpConfirmationMessage signUpConfirmationMessage = mock(SignUpConfirmationMessage.class);
        TechnicalResource technicalResource = mock(TechnicalResource.class);
        String talentEmail = "qa.talent.cr@gmail.com";

        ReflectionTestUtils.setField(signUpConfirmationEmailService, "emailSenderService", emailSenderService);
        ReflectionTestUtils.setField(signUpConfirmationEmailService, "talentEmail", talentEmail);
        Map< String, Object> model = mock(HashMap.class);

        when(signUpConfirmationMessage.getTechnicalResource()).thenReturn(technicalResource);
        signUpConfirmationEmailService.sendSignUpConfirmationEmail(signUpConfirmationMessage);
    }

}
