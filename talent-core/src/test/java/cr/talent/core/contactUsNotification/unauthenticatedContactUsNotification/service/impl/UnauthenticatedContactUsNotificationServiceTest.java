package cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.service.impl;

import cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.dao.UnauthenticatedContactUsNotificationDao;
import cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.dao.impl.HibernateUnauthenticatedContactUsNotificationDao;
import cr.talent.core.contactUsNotification.unauthenticatedContactUsNotification.service.UnauthenticatedContactUsNotificationService;
import cr.talent.model.UnauthenticatedContactUsNotification;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;

public class UnauthenticatedContactUsNotificationServiceTest {
    @Test
    public void testCreateCall() {
        UnauthenticatedContactUsNotificationDao contactUsNotificationDao = mock(HibernateUnauthenticatedContactUsNotificationDao.class);
        UnauthenticatedContactUsNotification contactUsNotification = mock(UnauthenticatedContactUsNotification.class);
        UnauthenticatedContactUsNotificationService contactUsNotificationService = new UnauthenticatedContactUsNotificationServiceImpl();
        ReflectionTestUtils.setField(contactUsNotificationService, "crudDao", contactUsNotificationDao);
        contactUsNotificationService.create(contactUsNotification);
        verify(contactUsNotificationDao, times(1)).create(contactUsNotification);
    }

    @Test
    public void testRemoveCall() {
        UnauthenticatedContactUsNotificationDao contactUsNotificationDao = mock(HibernateUnauthenticatedContactUsNotificationDao.class);
        UnauthenticatedContactUsNotification contactUsNotification = mock(UnauthenticatedContactUsNotification.class);
        UnauthenticatedContactUsNotificationService contactUsNotificationService = new UnauthenticatedContactUsNotificationServiceImpl();
        ReflectionTestUtils.setField(contactUsNotificationService, "crudDao", contactUsNotificationDao);
        contactUsNotificationService.remove(contactUsNotification);
        verify(contactUsNotificationDao, times(1)).remove(contactUsNotification);
    }

    @Test
    public void testUpdateCall() {
        UnauthenticatedContactUsNotificationDao contactUsNotificationDao = mock(HibernateUnauthenticatedContactUsNotificationDao.class);
        UnauthenticatedContactUsNotification contactUsNotification = mock(UnauthenticatedContactUsNotification.class);
        UnauthenticatedContactUsNotificationService contactUsNotificationService = new UnauthenticatedContactUsNotificationServiceImpl();
        ReflectionTestUtils.setField(contactUsNotificationService, "crudDao", contactUsNotificationDao);
        contactUsNotificationService.update(contactUsNotification);
        verify(contactUsNotificationDao, times(1)).update(contactUsNotification);
    }

    @Test
    public void testInit() {
        UnauthenticatedContactUsNotificationService contactUsNotificationService = new UnauthenticatedContactUsNotificationServiceImpl();
        UnauthenticatedContactUsNotificationDao contactUsNotificationDao = mock(HibernateUnauthenticatedContactUsNotificationDao.class);
        ReflectionTestUtils.setField(contactUsNotificationService, "unauthenticatedContactUsDao", contactUsNotificationDao);
        ReflectionTestUtils.invokeMethod(contactUsNotificationService, "init");
    }
}
