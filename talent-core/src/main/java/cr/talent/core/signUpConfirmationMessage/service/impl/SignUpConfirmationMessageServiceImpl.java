package cr.talent.core.signUpConfirmationMessage.service.impl;

import cr.talent.core.signUpConfirmationMessage.dao.SignUpConfirmationMessageDao;
import cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService;
import cr.talent.model.SignUpConfirmationMessage;
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

    public void init() { setCrudDao(this.signUpConfirmationMessageDao); }

    @Override
    public SignUpConfirmationMessage getActiveConfirmationMessage(String username){
        SignUpConfirmationMessage signUpConfirmation = signUpConfirmationMessageDao.getActiveConfirmationMessage(username);
        if (signUpConfirmation == null)
            throw new NonExistentConfirmationMessageException();

        return signUpConfirmation;
    }

}
