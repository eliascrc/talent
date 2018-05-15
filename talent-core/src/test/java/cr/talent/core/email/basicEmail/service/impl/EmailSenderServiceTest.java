package cr.talent.core.email.basicEmail.service.impl;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.model.Email;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Class that allows to test the EmailSenderServiceImpl methods
 *
 * @author Maria Jose Cubero.
 */
public class EmailSenderServiceTest {

    @Test
    public void testSendEmail(){
        EmailSenderService emailSenderService = new EmailSenderServiceImpl();
        JavaMailSender javaMailSender = mock(JavaMailSender.class);
        VelocityEngine velocityEngine = mock(VelocityEngine.class);
        Email email = mock(Email.class);
        Map< String, Object> model = mock(HashMap.class);
        MimeMessage mimeMessage = mock(MimeMessage.class);

        ReflectionTestUtils.setField(emailSenderService, "javaMailSender", javaMailSender);
        ReflectionTestUtils.setField(emailSenderService, "velocityEngine", velocityEngine);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(email.getSubject()).thenReturn("subject");
        when(email.getFrom()).thenReturn("from");
        when(email.getTo()).thenReturn("to");
        when(email.getFileName()).thenReturn("fileName");
        when(email.getContent()).thenReturn("content");

        emailSenderService.sendEmail(email, model);
        verify(javaMailSender, times(1)).createMimeMessage();
        verify(javaMailSender, times(1)).send(any(MimeMessage.class));
    }

    @Test
    public void testSendEmailNullVelocityEngineException(){
        EmailSenderService emailSenderService = new EmailSenderServiceImpl();
        JavaMailSender javaMailSender = mock(JavaMailSender.class);
        Email email = mock(Email.class);
        Map< String, Object> model = mock(HashMap.class);
        MimeMessage mimeMessage = mock(MimeMessage.class);

        ReflectionTestUtils.setField(emailSenderService, "javaMailSender", javaMailSender);
        when(javaMailSender.createMimeMessage()).thenReturn(mimeMessage);
        when(email.getSubject()).thenReturn("subject");
        when(email.getFrom()).thenReturn("from");
        when(email.getTo()).thenReturn("to");
        when(email.getFileName()).thenReturn("fileName");
        when(email.getContent()).thenReturn("content");

        emailSenderService.sendEmail(email, model);
    }

}
