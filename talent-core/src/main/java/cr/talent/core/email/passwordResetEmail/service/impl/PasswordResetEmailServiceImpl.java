package cr.talent.core.email.passwordResetEmail.service.impl;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.core.email.passwordResetEmail.service.PasswordResetEmailService;
import cr.talent.model.Email;
import cr.talent.model.PasswordResetRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link cr.talent.core.email.passwordResetEmail.service.PasswordResetEmailService}
 *
 * @author María José Cubero
 */
@Service("passwordResetEmailService")
@Transactional
public class PasswordResetEmailServiceImpl implements PasswordResetEmailService{

    private static final String PASSWORD_RESET_SUBJECT = "TALENT Password Reset";
    private static final String HTML_EMAIL_FILE = "forgotPassword.vm";
    private static final String LINK = "http://ws.talent.cr/ws/passwordReset/new/?token=";

    @Value("${talent.mail.from}")
    private String talentEmail;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void sendPasswordResetMail(String destinationEmail, PasswordResetRequest passwordResetRequest) {
        Email email = new Email();
        email.setFrom(talentEmail);
        email.setTo(destinationEmail);
        email.setSubject(PASSWORD_RESET_SUBJECT);
        email.setFileName(HTML_EMAIL_FILE);

        Map< String, Object> model = new HashMap();
        model.put("firstName", passwordResetRequest.getTechnicalResource().getFirstName());
        model.put("lastName", passwordResetRequest.getTechnicalResource().getLastName());
        model.put("link", LINK + passwordResetRequest.getToken());

        this.emailSenderService.sendEmail(email, model);
    }
}
