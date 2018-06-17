package cr.talent.core.signUpConfirmationMessage.service.impl;

import cr.talent.core.email.signUpConfirmationEmail.service.SignUpConfirmationEmailService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.core.signUpConfirmationMessage.dao.SignUpConfirmationMessageDao;
import cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService;
import cr.talent.model.SignUpConfirmationMessage;
import cr.talent.model.TechnicalResource;
import cr.talent.model.User;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.NonExistentConfirmationMessageException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    //The highest number that can be used for a confirmation code
    private static final int MAX_CONFIRMATION_CODE = 999999;

    //The number of digits in the confirmation code
    private static final int DIGITS = 6;

    /**
     * Data access object reference to perform SignUpConfirmationMessage operations.
     */
    @Autowired
    private SignUpConfirmationMessageDao signUpConfirmationMessageDao;

    /**
     * Used to perform operations with technical resources
     */
    @Autowired
    private TechnicalResourceService technicalResourceService;

    /**
     * Used to send confirmation emails for the first step of the sign up
     */
    @Autowired
    SignUpConfirmationEmailService signUpConfirmationEmailService;

    /**
     * Password encoder provided by spring to cipher a user's password and store it in the database so that it can
     * be compared later when the user enters his password.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void init() { setCrudDao(this.signUpConfirmationMessageDao); }

    /**
     * Retrieves the last confirmation message that the user has not yet confirmed if it does not exist
     * @param username The username of the user performing the first step of the sign up
     * @return the confirmation message if it exists for that username
     */
    private SignUpConfirmationMessage getActiveConfirmationMessage(String username) {
        SignUpConfirmationMessage signUpConfirmation = signUpConfirmationMessageDao.getActiveConfirmationMessage(username);

        return signUpConfirmation;
    }

    /**
     * @see cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService#sendMessage(String, String, String, String)
     */
    @Override
    public TechnicalResource sendMessage(String firstName, String lastName, String username, String password) {
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

            /* The password should only be encoded if the resource is being updated, because if it is being created t
            because if it is being created the create method of the service will take care of that
            */
            SecurityUtils.validatePassword(password); // throws IllegalArgumentException if it is not valid
            password = this.passwordEncoder.encode(password); // the password is encoded
        }

        technicalResource.setFirstName(firstName);
        technicalResource.setLastName(lastName);
        technicalResource.setUsername(username);
        technicalResource.setPassword(password);
        technicalResource.setStatus(User.Status.INACTIVE);
        technicalResource.setAdministrator(true); // because they are signing up while creating an organization

        signUpConfirmationMessage.setConfirmationCode(String.format("%0" + DIGITS + "d", confirmationCode));
        signUpConfirmationMessage.setTechnicalResource(technicalResource);

        if (hadAnotherConfirmationMessage) {
            this.technicalResourceService.update(technicalResource);
            super.update(signUpConfirmationMessage);
        } else {
            this.technicalResourceService.create(technicalResource);
            super.create(signUpConfirmationMessage);
        }
        signUpConfirmationEmailService.sendSignUpConfirmationEmail(signUpConfirmationMessage);

        return technicalResource;
    }

    /**
     * @see cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService#confirmEmail(String, String)
     */
    @Override
    public boolean confirmEmail(String code, String username) {
        SignUpConfirmationMessage signUpConfirmationMessage = this.getActiveConfirmationMessage(username);
        if (signUpConfirmationMessage == null)
            throw new NonExistentConfirmationMessageException();

        boolean codeMatchesConfirmationMessage = code.equals(signUpConfirmationMessage.getConfirmationCode());
        if (codeMatchesConfirmationMessage) {
            TechnicalResource technicalResource = signUpConfirmationMessage.getTechnicalResource();
            super.remove(signUpConfirmationMessage);
            technicalResource.setStatus(User.Status.ACTIVE);
            technicalResourceService.update(technicalResource);
        }

        return codeMatchesConfirmationMessage;
    }

    @Override
    public String getCode(String email) {
        SignUpConfirmationMessage signUpConfirmationMessage = this.getActiveConfirmationMessage(email);
        if (signUpConfirmationMessage == null)
            return null;

        String code = signUpConfirmationMessage.getConfirmationCode();
        return code;
    }

}
