package cr.talent.core.privacyPolicy.service.impl;

import cr.talent.core.privacyPolicy.dao.PrivacyPolicyDao;
import cr.talent.core.privacyPolicy.dao.impl.HibernatePrivacyPolicyDao;
import cr.talent.core.privacyPolicy.service.PrivacyPolicyService;
import cr.talent.model.PrivacyPolicy;
import cr.talent.support.dao.CrudDao;
import cr.talent.support.service.CrudService;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@ContextConfiguration
public class PrivacyPolicyServiceTest {

    /*
    @Configuration
    static class PrivacyPolicyServiceTestContextConfiguration {

        @Bean
        public PrivacyPolicyService privacyPolicyService() {

            return new PrivacyPolicyServiceImpl();
        }

        @Bean
        public PrivacyPolicyDao privacyPolicyDao() {
            return mock(PrivacyPolicyDao.class);
        }
    }

    @Before
    public void setup() {
        when(privacyPolicyDao.getActivePrivacyPolicy()).thenReturn(mock(PrivacyPolicy.class));
    }

    @Autowired
    private PrivacyPolicyService privacyPolicyService;
    @Autowired
    private PrivacyPolicyDao privacyPolicyDao;
    */

    @Test
    public void testCreateCall() {
        PrivacyPolicyDao privacyPolicyDao = mock(HibernatePrivacyPolicyDao.class);
        PrivacyPolicy privacyPolicy = mock(PrivacyPolicy.class);
        PrivacyPolicyService privacyPolicyService = new PrivacyPolicyServiceImpl();
        ReflectionTestUtils.setField(privacyPolicyService, "crudDao", privacyPolicyDao);
        privacyPolicyService.create(privacyPolicy);
        verify(privacyPolicyDao, times(1)).create(privacyPolicy);
    }

    @Test
    public void testRemoveCall() {
        PrivacyPolicyDao privacyPolicyDao = mock(HibernatePrivacyPolicyDao.class);
        PrivacyPolicy privacyPolicy = mock(PrivacyPolicy.class);
        PrivacyPolicyService privacyPolicyService = new PrivacyPolicyServiceImpl();
        ReflectionTestUtils.setField(privacyPolicyService, "crudDao", privacyPolicyDao);
        privacyPolicyService.remove(privacyPolicy);
        verify(privacyPolicyDao, times(1)).remove(privacyPolicy);
    }

    @Test
    public void testUpdateCall() {
        PrivacyPolicyDao privacyPolicyDao = mock(HibernatePrivacyPolicyDao.class);
        PrivacyPolicy privacyPolicy = mock(PrivacyPolicy.class);
        PrivacyPolicyService privacyPolicyService = new PrivacyPolicyServiceImpl();
        ReflectionTestUtils.setField(privacyPolicyService, "crudDao", privacyPolicyDao);
        privacyPolicyService.update(privacyPolicy);
        verify(privacyPolicyDao, times(1)).update(privacyPolicy);
    }

    @Test
    public void testGetActive() {
        PrivacyPolicyDao privacyPolicyDao = mock(HibernatePrivacyPolicyDao.class);
        PrivacyPolicy privacyPolicy = mock(PrivacyPolicy.class);
        when(privacyPolicyDao.getActivePrivacyPolicy()).thenReturn(privacyPolicy);
        PrivacyPolicyService privacyPolicyService = new PrivacyPolicyServiceImpl();
        ReflectionTestUtils.setField(privacyPolicyService, "privacyPolicyDao", privacyPolicyDao);
        ReflectionTestUtils.setField(privacyPolicyService, "crudDao", privacyPolicyDao);
        assertTrue(privacyPolicy.equals(privacyPolicyService.getActivePrivacyPolicy()));
        verify(privacyPolicyDao, times(1)).getActivePrivacyPolicy();
    }

    @Test
    public void testInit() {
        PrivacyPolicyService privacyPolicyService = new PrivacyPolicyServiceImpl();
        PrivacyPolicyDao privacyPolicyDao = mock(HibernatePrivacyPolicyDao.class);
        ReflectionTestUtils.setField(privacyPolicyService, "privacyPolicyDao", privacyPolicyDao);
        ReflectionTestUtils.invokeMethod(privacyPolicyService, "init");
    }

}
