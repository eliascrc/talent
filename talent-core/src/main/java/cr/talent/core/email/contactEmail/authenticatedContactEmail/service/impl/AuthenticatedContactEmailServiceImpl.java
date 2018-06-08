package cr.talent.core.email.contactEmail.authenticatedContactEmail.service.impl;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.core.email.contactEmail.authenticatedContactEmail.service.AuthenticatedContactEmailService;
import cr.talent.model.AuthenticatedContactEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link cr.talent.core.email.contactEmail.authenticatedContactEmail.service.AuthenticatedContactEmailService}.
 *
 * @author Fabián Roberto Leandro
 */
@Service("authenticatedContactEmailServiceImpl")
@Transactional
public class AuthenticatedContactEmailServiceImpl implements AuthenticatedContactEmailService {

    private static final String CONTACT_US_SUBJECT = "Talent! Contact Request";
    private static final String HTML_EMAIL_FILE = "templates/contactUs.vm";

    @Value("${talent.mail.from}")
    private String talentEmail;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void sendAuthenticatedContactEmail(AuthenticatedContactEmail authenticatedContactEmail) {

        // Set constants of the email, other properties should already be set
        authenticatedContactEmail.setFileName(HTML_EMAIL_FILE);
        authenticatedContactEmail.setSubject(CONTACT_US_SUBJECT);
        authenticatedContactEmail.setFrom(talentEmail);

        Map< String, Object> model = new HashMap();
        model.put("firstName", authenticatedContactEmail.getUser().getFirstName());
        model.put("lastName", authenticatedContactEmail.getUser().getLastName());
        model.put("issueType", authenticatedContactEmail.getIssueType().getIssueText());
        model.put("content",authenticatedContactEmail.getContent());

        this.emailSenderService.sendEmail(authenticatedContactEmail,model);
    }
}
