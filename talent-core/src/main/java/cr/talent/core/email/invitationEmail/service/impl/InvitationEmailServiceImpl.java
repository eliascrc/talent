package cr.talent.core.email.invitationEmail.service.impl;

import cr.talent.core.email.basicEmail.service.EmailSenderService;
import cr.talent.core.email.invitationEmail.service.InvitationEmailService;
import cr.talent.model.Email;
import cr.talent.model.Invitation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link cr.talent.core.email.invitationEmail.service.InvitationEmailService}
 *
 * @author Elias Calderon
 */
@Service("invitationEmailService")
@Transactional
public class InvitationEmailServiceImpl implements InvitationEmailService {

    private static final String INVITATION_SUBJECT = "Talent Invitation";
    private static final String HTML_EMAIL_FILE = "templates/inviteEmail.vm";
    private static final String HTTP_PREFIX = "http://";
    private static final String TALENT_PAGE = "www.talent.cr/#/";
    private static final String ACCEPT_INVITE_LINK = "accept-invite?token=";

    @Value("${talent.mail.from}")
    private String talentEmail;

    @Autowired
    private EmailSenderService emailSenderService;

    /**
     * @see cr.talent.core.email.invitationEmail.service.InvitationEmailService#sendInvitationEmail(Invitation)
     */
    @Override
    public void sendInvitationEmail(Invitation invitation) {
        Email email = new Email();
        email.setFrom(talentEmail);
        email.setTo(invitation.getEmail());
        email.setSubject(INVITATION_SUBJECT);
        email.setFileName(HTML_EMAIL_FILE);

        Map<String, Object> model = new HashMap<>();
        model.put("inviteLink", HTTP_PREFIX + TALENT_PAGE + ACCEPT_INVITE_LINK + invitation.getToken());
        model.put("firstName", invitation.getInviterResourceFirstName());
        model.put("lastName", invitation.getInviterResourceLastName());
        model.put("organization", invitation.getOrganization().getName());

        this.emailSenderService.sendEmail(email, model);
    }
}
