package cr.talent.core.email.contactEmail.unauthenticatedContactEmail.service.impl;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.core.email.contactEmail.unauthenticatedContactEmail.service.UnauthenticatedContactEmailService;
import cr.talent.model.UnauthenticatedContactEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link cr.talent.core.email.contactEmail.unauthenticatedContactEmail.service.UnauthenticatedContactEmailService}.
 *
 * @author Fabián Roberto Leandro
 */
@Service("unauthenticatedContactEmailServiceImpl")
@Transactional
public class UnauthenticatedContactEmailServiceImpl implements UnauthenticatedContactEmailService {

    private static final String CONTACT_US_SUBJECT = "Talent! Contact Request";
    private static final String HTML_EMAIL_FILE = "templates/contactUs.vm";

    @Value("${talent.mail.from}")
    private String talentEmail;

    @Autowired
    private EmailSenderService emailSenderService;

    @Override
    public void sendUnauthenticatedContactEmail(UnauthenticatedContactEmail unauthenticatedContactEmail) {

        // Set constants of the email, other properties should already be set
        unauthenticatedContactEmail.setFileName(HTML_EMAIL_FILE);
        unauthenticatedContactEmail.setSubject(CONTACT_US_SUBJECT);
        unauthenticatedContactEmail.setFrom(talentEmail);

        // Build the Map EmailSenderService uses to make the email
        Map< String, Object> model = new HashMap();
        model.put("firstName", unauthenticatedContactEmail.getFirstName());
        model.put("lastName", unauthenticatedContactEmail.getLastName());
        model.put("issueType", unauthenticatedContactEmail.getIssueType().getIssueText());
        model.put("content",unauthenticatedContactEmail.getContent());

        this.emailSenderService.sendEmail(unauthenticatedContactEmail, model);
    }
}
