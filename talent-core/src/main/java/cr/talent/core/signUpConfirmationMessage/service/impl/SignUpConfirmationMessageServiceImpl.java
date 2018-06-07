package cr.talent.core.signUpConfirmationMessage.service.impl;

import cr.talent.core.email.signUpConfirmationEmail.service.SignUpConfirmationEmailService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.core.signUpConfirmationMessage.dao.SignUpConfirmationMessageDao;
import cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService;
import cr.talent.model.SignUpConfirmationMessage;
import cr.talent.model.TechnicalResource;
import cr.talent.support.exceptions.NonExistentConfirmationMessageException;
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

    public void init() { setCrudDao(this.signUpConfirmationMessageDao); }

    @Override
    public SignUpConfirmationMessage getActiveConfirmationMessage(String username){
        SignUpConfirmationMessage signUpConfirmation = signUpConfirmationMessageDao.getActiveConfirmationMessage(username);
        if (signUpConfirmation == null)
            throw new NonExistentConfirmationMessageException();

        return signUpConfirmation;
    }

    @Override
    public void sendMessage(SignUpConfirmationMessage signUpConfirmationMessage, TechnicalResource technicalResource, boolean hadAnotherConfirmationMessage) {
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
