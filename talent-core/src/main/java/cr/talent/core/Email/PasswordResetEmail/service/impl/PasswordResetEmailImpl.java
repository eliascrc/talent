package cr.talent.core.Email.PasswordResetEmail.service.impl;

import cr.talent.core.Email.BasicEmail.service.SendEmailService;
import cr.talent.core.Email.PasswordResetEmail.service.PasswordResetEmailService;
import cr.talent.model.Email;
import cr.talent.model.PasswordResetRequest;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link cr.talent.core.Email.PasswordResetEmail.service.PasswordResetEmailService}
 *
 * @author María José Cubero
 */
@Service("passwordResetEmailService")
@Transactional
public class PasswordResetEmailImpl implements PasswordResetEmailService{

    //REVISAR ESTAS PROPIEDADES
    private static final String PASSWORD_RESET_SUBJECT = "TALENT Password Reset";
    private static final String TALENT_EMAIL = "qa.talent.cr@gmail.com";
    private static final String HTML_EMAIL_FILE = "forgotPassword.vm";
    private static final String LINK = "http://localhost:8080/talent/ws/passwordReset/new/?token=";

    @Autowired
    private SendEmailService sendEmailService;

    @Override
    public void sendPasswordResetMail(String destinationEmail, PasswordResetRequest passwordResetRequest) {
        Email email = new Email();
        email.setFrom(TALENT_EMAIL);
        email.setTo(destinationEmail);
        email.setSubject(PASSWORD_RESET_SUBJECT);
        email.setFileName(HTML_EMAIL_FILE);

        Map< String, Object> model = new HashMap();
        model.put("firstName", passwordResetRequest.getTechnicalResource().getFirstName());
        model.put("lastName", passwordResetRequest.getTechnicalResource().getLastName());
        model.put("link", LINK + passwordResetRequest.getToken());

        this.sendEmailService.sendEmail(email, model);
    }
}
