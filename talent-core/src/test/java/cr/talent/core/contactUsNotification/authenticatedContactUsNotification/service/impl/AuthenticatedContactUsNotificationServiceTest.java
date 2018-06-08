package cr.talent.core.contactUsNotification.authenticatedContactUsNotification.service.impl;

import cr.talent.core.contactUsNotification.authenticatedContactUsNotification.dao.AuthenticatedContactUsNotificationDao;
import cr.talent.core.contactUsNotification.authenticatedContactUsNotification.dao.impl.HibernateAuthenticatedContactUsNotificationDao;
import cr.talent.core.contactUsNotification.authenticatedContactUsNotification.service.AuthenticatedContactUsNotificationService;
import cr.talent.model.AuthenticatedContactUsNotification;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

public class AuthenticatedContactUsNotificationServiceTest {
    @Test
    public void testCreateCall() {
        AuthenticatedContactUsNotificationDao authenticatedContactUsNotificationDao = mock(HibernateAuthenticatedContactUsNotificationDao.class);
        AuthenticatedContactUsNotification authenticatedContactUsNotification = mock(AuthenticatedContactUsNotification.class);
        AuthenticatedContactUsNotificationService authenticatedContactUsNotificationService = new AuthenticatedContactUsNotificationServiceImpl();
        ReflectionTestUtils.setField(authenticatedContactUsNotificationService, "crudDao", authenticatedContactUsNotificationDao);
        authenticatedContactUsNotificationService.create(authenticatedContactUsNotification);
        verify(authenticatedContactUsNotificationDao, times(1)).create(authenticatedContactUsNotification);
    }

    @Test
    public void testRemoveCall() {
        AuthenticatedContactUsNotificationDao authenticatedContactUsNotificationDao = mock(HibernateAuthenticatedContactUsNotificationDao.class);
        AuthenticatedContactUsNotification authenticatedContactUsNotification = mock(AuthenticatedContactUsNotification.class);
        AuthenticatedContactUsNotificationService authenticatedContactUsNotificationService = new AuthenticatedContactUsNotificationServiceImpl();
        ReflectionTestUtils.setField(authenticatedContactUsNotificationService, "crudDao", authenticatedContactUsNotificationDao);
        authenticatedContactUsNotificationService.remove(authenticatedContactUsNotification);
        verify(authenticatedContactUsNotificationDao, times(1)).remove(authenticatedContactUsNotification);
    }

    @Test
    public void testUpdateCall() {
        AuthenticatedContactUsNotificationDao authenticatedContactUsNotificationDao = mock(HibernateAuthenticatedContactUsNotificationDao.class);
        AuthenticatedContactUsNotification authenticatedContactUsNotification = mock(AuthenticatedContactUsNotification.class);
        AuthenticatedContactUsNotificationService authenticatedContactUsNotificationService = new AuthenticatedContactUsNotificationServiceImpl();
        ReflectionTestUtils.setField(authenticatedContactUsNotificationService, "crudDao", authenticatedContactUsNotificationDao);
        authenticatedContactUsNotificationService.update(authenticatedContactUsNotification);
        verify(authenticatedContactUsNotificationDao, times(1)).update(authenticatedContactUsNotification);
    }

    @Test
    public void testInit() {
        AuthenticatedContactUsNotificationService authenticatedContactUsNotificationService = new AuthenticatedContactUsNotificationServiceImpl();
        AuthenticatedContactUsNotificationDao authenticatedContactUsNotificationDao = mock(HibernateAuthenticatedContactUsNotificationDao.class);
        ReflectionTestUtils.setField(authenticatedContactUsNotificationService, "authenticatedContactUsDao", authenticatedContactUsNotificationDao);
        ReflectionTestUtils.invokeMethod(authenticatedContactUsNotificationService, "init");
    }
}
