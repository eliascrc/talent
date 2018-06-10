package cr.talent.core.termsOfService;

import cr.talent.core.termsOfService.dao.ToSDao;
import cr.talent.core.termsOfService.dao.impl.HibernateToSDao;
import cr.talent.core.termsOfService.service.ToSService;
import cr.talent.core.termsOfService.service.impl.ToSServiceImpl;
import cr.talent.model.Platform;
import cr.talent.model.TermsOfService;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Class that allows to test the ToSServiceImpl methods to know all the different paths they could take.
 *
 * @author Josue Leon Sarkis
 */
public class ToSServiceTest {

    @Test
    public void testCreateCall() {
        ToSDao toSDao = mock(HibernateToSDao.class);
        TermsOfService termsOfService = mock(TermsOfService.class);
        ToSService termsOfServiceService = new ToSServiceImpl();
        ReflectionTestUtils.setField(termsOfServiceService, "crudDao", toSDao);
        termsOfServiceService.create(termsOfService);
        verify(toSDao, times(1)).create(termsOfService);
    }

    @Test
    public void testGetActiveTermsOfService() {
        ToSDao toSDao = mock(ToSDao.class);
        TermsOfService termsOfService = mock(TermsOfService.class);
        Platform platform = termsOfService.getPlatform();
        ToSService termsOfServiceService = new ToSServiceImpl();
        ReflectionTestUtils.setField(termsOfServiceService, "toSDao", toSDao);
        when(toSDao.getActiveTermsOfService(platform)).thenReturn(termsOfService);
        TermsOfService termsOfService2 = termsOfServiceService.getActiveTermsOfService(platform);
        assertEquals(termsOfService, termsOfService2);
    }

    @Test
    public void testInitCall() {
        ToSDao toSDao = mock(HibernateToSDao.class);
        ToSService termsOfServiceService = new ToSServiceImpl();
        ReflectionTestUtils.setField(termsOfServiceService, "toSDao", toSDao);
        ReflectionTestUtils.invokeMethod(termsOfServiceService, "init");
    }
}
