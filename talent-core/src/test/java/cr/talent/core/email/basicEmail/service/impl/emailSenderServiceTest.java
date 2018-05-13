package cr.talent.core.email.basicEmail.service.impl;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.model.Email;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

/**
 * Class that allows to test the EmailSenderServiceImpl methods
 *
 * @author Daniel Montes de Oca
 */
public class emailSenderServiceTest {

    @Test
    public void testSendEmail(){
        JavaMailSender javaMailSender = mock(JavaMailSender.class);
        VelocityEngine velocityEngine = mock(VelocityEngine.class);
        Email email = mock(Email.class);
        EmailSenderService emailSenderService = new EmailSenderServiceImpl();
        ReflectionTestUtils.setField(emailSenderService, "javaMailSender", javaMailSender);
        ReflectionTestUtils.setField(emailSenderService, "velocityEngine", velocityEngine);
    }
}
