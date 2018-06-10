package cr.talent.core.signUpConfirmationMessage.service.impl;

import cr.talent.core.email.signUpConfirmationEmail.service.SignUpConfirmationEmailService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.core.signUpConfirmationMessage.dao.SignUpConfirmationMessageDao;
import cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService;
import cr.talent.model.SignUpConfirmationMessage;
import cr.talent.model.TechnicalResource;
import cr.talent.model.User;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService}.
 *
 * @author Daniel Montes de Oca
 */
@Service("signUpConfirmationMessageService")
@Transactional
public class SignUpConfirmationMessageServiceImpl extends CrudServiceImpl<SignUpConfirmationMessage, String> implements SignUpConfirmationMessageService {

    @Autowired
    private SignUpConfirmationMessageDao signUpConfirmationMessageDao;

    @Autowired
    private TechnicalResourceService technicalResourceService;

    @Autowired
    SignUpConfirmationEmailService signUpConfirmationEmailService;

    //The highest number that can be used for a confirmation code
    private static final int MAX_CONFIRMATION_CODE = 999999;

    //The number of digits in the confirmation code
    private static final int DIGITS = 6;

    public void init() { setCrudDao(this.signUpConfirmationMessageDao); }

    @Override
    public SignUpConfirmationMessage getActiveConfirmationMessage(String username) {
        SignUpConfirmationMessage signUpConfirmation = signUpConfirmationMessageDao.getActiveConfirmationMessage(username);

        return signUpConfirmation;
    }

    @Override
    public void sendMessage(String firstName, String lastName, String username, String password) {
        TechnicalResource technicalResource;
        SignUpConfirmationMessage signUpConfirmationMessage;
        boolean hadAnotherConfirmationMessage = false;
        int confirmationCode = (int) (MAX_CONFIRMATION_CODE * Math.random());

        signUpConfirmationMessage = this.getActiveConfirmationMessage(username);
        if (signUpConfirmationMessage == null) {
            technicalResource = new TechnicalResource();
            signUpConfirmationMessage = new SignUpConfirmationMessage();
        } else {
            technicalResource = signUpConfirmationMessage.getTechnicalResource();
            hadAnotherConfirmationMessage = true;
        }

        technicalResource.setFirstName(firstName);
        technicalResource.setLastName(lastName);
        technicalResource.setUsername(username);
        technicalResource.setPassword(password);
        technicalResource.setStatus(User.Status.INACTIVE);
        technicalResource.setAdministrator(true); //because they are signing up while creating an organization

        signUpConfirmationMessage.setConfirmationCode(String.format("%0" + DIGITS + "d", confirmationCode));
        signUpConfirmationMessage.setTechnicalResource(technicalResource);

        if (hadAnotherConfirmationMessage) {
            this.technicalResourceService.update(technicalResource);
            this.update(signUpConfirmationMessage);
        } else {
            this.technicalResourceService.create(technicalResource);
            this.create(signUpConfirmationMessage);
        }
        signUpConfirmationEmailService.sendSignUpConfirmationEmail(signUpConfirmationMessage);
    }

}
