package cr.talent.core.signUpConfirmationMessage.service.impl;

import cr.talent.core.signUpConfirmationMessage.dao.SignUpConfirmationMessageDao;
import cr.talent.core.signUpConfirmationMessage.dao.impl.HibernateSignUpConfirmationMessageDao;
import cr.talent.core.signUpConfirmationMessage.service.SignUpConfirmationMessageService;
import cr.talent.model.SignUpConfirmationMessage;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

/**
 * Class that allows to test the SignUpConfirmationServiceImpl methods
 *
 * @author Daniel Montes de Oca
 */
public class SignUpConfirmationMessageServiceTest {

    @Test
    public void testInit() {
        SignUpConfirmationMessageService signUpConfirmationMessageService = new SignUpConfirmationMessageServiceImpl();
        SignUpConfirmationMessageDao signUpConfirmationMessageDao = mock(HibernateSignUpConfirmationMessageDao.class);
        ReflectionTestUtils.setField(signUpConfirmationMessageService, "signUpConfirmationMessageDao", signUpConfirmationMessageDao);
        ReflectionTestUtils.invokeMethod(signUpConfirmationMessageService, "init");
    }

}
