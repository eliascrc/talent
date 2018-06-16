package cr.talent.core.security.token.service.impl;

import cr.talent.core.security.technicalResource.dao.TechnicalResourceDao;
import cr.talent.core.security.token.service.TokenAuthenticationService;
import cr.talent.model.TechnicalResource;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.util.ReflectionTestUtils;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Class that allows to test the TokenAuthenticationServiceImpl methods to know all the different paths they could take.
 *
 * @author Josue Leon Sarkis
 */
public class TokenAuthenticationServiceTest {

    @Test
    public void loadUserByUsernameTest() {
        TechnicalResourceDao technicalResourceDao = mock(TechnicalResourceDao.class);
        TechnicalResource technicalResource = mock(TechnicalResource.class);
        String token = "tokenTest";
        TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationServiceImpl();
        ReflectionTestUtils.setField(tokenAuthenticationService, "technicalResourceDao", technicalResourceDao);

        when(technicalResourceDao.findByAuthenticationToken(token)).thenReturn(technicalResource);
        UserDetails userDetails = tokenAuthenticationService.loadUserByUsername(token);
        assertEquals(technicalResource, userDetails);
        verify(technicalResourceDao, times(1)).findByAuthenticationToken(token);
    }

    @Test
    public void loadUserByUsernameNullUserTest() {
        TechnicalResourceDao technicalResourceDao = mock(TechnicalResourceDao.class);
        String token = "tokenTest";
        TokenAuthenticationService tokenAuthenticationService = new TokenAuthenticationServiceImpl();
        ReflectionTestUtils.setField(tokenAuthenticationService, "technicalResourceDao", technicalResourceDao);

        when(technicalResourceDao.findByAuthenticationToken(token)).thenReturn(null);
        try {
            tokenAuthenticationService.loadUserByUsername(token);
        } catch (UsernameNotFoundException e) {
            // null user
        }
    }
}
