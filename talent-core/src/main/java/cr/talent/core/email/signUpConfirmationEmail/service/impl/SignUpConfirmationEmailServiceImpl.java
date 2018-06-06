package cr.talent.core.email.signUpConfirmationEmail.service.impl;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.core.email.signUpConfirmationEmail.service.SignUpConfirmationEmailService;
import cr.talent.model.Email;
import cr.talent.model.SignUpConfirmationMessage;
import cr.talent.model.TechnicalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;


/**
 * Default implementation of the {@link cr.talent.core.email.signUpConfirmationEmail.service.SignUpConfirmationEmailService}
 *
 * @author Daniel Montes de Oca
 */
@Service("signUpConfirmationEmailService")
@Transactional
public class SignUpConfirmationEmailServiceImpl implements SignUpConfirmationEmailService {

    private static final String SIGN_UP_CONFIRMATION_SUBJECT = "TALENT confirmation code: ";
    private static final String HTML_EMAIL_FILE = "templates/signUpConfirmation.vm";

    @Value("${talent.mail.from}")
    private String talentEmail;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void sendSignUpConfirmationEmail(String destinationEmail, SignUpConfirmationMessage signUpConfirmationMessage, TechnicalResource technicalResource) {
        Email email = new Email();
        email.setFrom(talentEmail);
        email.setTo(destinationEmail);
        email.setSubject(SIGN_UP_CONFIRMATION_SUBJECT);
        email.setFileName(HTML_EMAIL_FILE);

        Map< String, Object> model = new HashMap();
        //model.put("firstName", technicalResource.getFirstName());
        //model.put("lastName", passwordResetRequest.getTechnicalResource().getLastName());
        //model.put("link", LINK + passwordResetRequest.getToken());

        this.emailSenderService.sendEmail(email, model);
    }
}
