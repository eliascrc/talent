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

    @Test
    public void testGetActive() {
        SignUpConfirmationMessageDao signUpConfirmationMessageDao = mock(HibernateSignUpConfirmationMessageDao.class);
        SignUpConfirmationMessage signUpConfirmationMessage = mock(SignUpConfirmationMessage.class);
        String email = "email";
        when(signUpConfirmationMessageDao.getActiveConfirmationMessage(email)).thenReturn(signUpConfirmationMessage);
        SignUpConfirmationMessageService signUpConfirmationMessageService = new SignUpConfirmationMessageServiceImpl();
        ReflectionTestUtils.setField(signUpConfirmationMessageService, "signUpConfirmationMessageDao", signUpConfirmationMessageDao);
        ReflectionTestUtils.setField(signUpConfirmationMessageService, "crudDao", signUpConfirmationMessageDao);
        assertTrue(signUpConfirmationMessage.equals(signUpConfirmationMessageService.getActiveConfirmationMessage(email)));
        verify(signUpConfirmationMessageDao, times(1)).getActiveConfirmationMessage(email);
    }

}
