package cr.talent.core.Email.BasicEmail.service.impl;

import cr.talent.core.Email.BasicEmail.service.SendEmailService;
import cr.talent.model.Email;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.util.Map;

/**
 * Default implementation of the {@link cr.talent.core.Email.BasicEmail.service.SendEmailService}
 *
 * @author María José Cubero
 */
@Service("sendEmailService")
@Transactional
public class SendEmailImpl implements SendEmailService{

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VelocityEngine velocityEngine;

    @Override
    public void sendEmail(Email email, Map< String, Object> model) {
        MimeMessage mimeMessage = this.javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = null;

        try {
            mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setSubject(email.getSubject());
            mimeMessageHelper.setFrom(email.getFrom());
            mimeMessageHelper.setTo(email.getTo());

            StringBuffer templateContent = new StringBuffer();
            try {
                templateContent.append(VelocityEngineUtils.mergeTemplateIntoString(this.velocityEngine, email.getFileName(), model));
            } catch (Exception e) {
                e.printStackTrace();
            }
            email.setContent(templateContent.toString());

            mimeMessageHelper.setText(email.getContent(), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        javaMailSender.send(mimeMessageHelper.getMimeMessage());
    }
}

