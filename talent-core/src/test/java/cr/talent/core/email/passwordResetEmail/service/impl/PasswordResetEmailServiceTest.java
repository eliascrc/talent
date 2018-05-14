package cr.talent.core.email.passwordResetEmail.service.impl;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.core.email.passwordResetEmail.service.PasswordResetEmailService;
import cr.talent.model.Email;
import cr.talent.model.PasswordResetRequest;
import cr.talent.model.TechnicalResource;
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

    private static final String PASSWORD_RESET_SUBJECT = "TALENT Password Reset";
    private static final String HTML_EMAIL_FILE = "forgotPassword.vm";
    private static final String LINK = "http://localhost:8080/talent/ws/passwordReset/new/?token=";

    @Test
    public void sendPasswordResetMailTest(){
    }

}
