package cr.talent.core.passwordResetRequest.service.impl;

import cr.talent.core.email.passwordResetEmail.service.PasswordResetEmailService;
import cr.talent.core.passwordResetRequest.dao.PasswordResetRequestDao;
import cr.talent.core.passwordResetRequest.dao.impl.HibernatePasswordResetRequestDao;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.PasswordResetRequest;
import cr.talent.model.TechnicalResource;
import org.junit.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

/**
 * Class that allows to test the PasswordResetEmailServiceImpl methods
 *
 * @author Maria Jose Cubero.
 */
public class PasswordResetRequestTest {

    private PasswordResetRequestDao passwordResetRequestDao = mock(HibernatePasswordResetRequestDao.class);
    private TechnicalResourceService technicalResourceService = mock(TechnicalResourceService.class);
    private PasswordResetEmailService passwordResetEmailService = mock(PasswordResetEmailService.class);
    private PasswordEncoder passwordEncoder = mock(PasswordEncoder.class);

    @Test
    public void testInitCall() {
        PasswordResetRequestServiceImpl passwordResetRequestService = new PasswordResetRequestServiceImpl();
        ReflectionTestUtils.setField(passwordResetRequestService, "passwordResetRequestDao", this.passwordResetRequestDao);
        ReflectionTestUtils.invokeMethod(passwordResetRequestService, "init");
    }

    @Test
    public void testCreatePasswordRequestReset(){
        PasswordResetRequestServiceImpl passwordResetRequestService = new PasswordResetRequestServiceImpl();
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
        TechnicalResource technicalResource = mock(TechnicalResource.class);
        String email = "qa.talent.cr@gmail.com";
        String organizationIdentifier = "monkey-labs";

        ReflectionTestUtils.setField(passwordResetRequestService, "crudDao", this.passwordResetRequestDao);
        ReflectionTestUtils.setField(passwordResetRequestService, "passwordResetRequestDao", this.passwordResetRequestDao);
        ReflectionTestUtils.setField(passwordResetRequestService, "technicalResourceService", this.technicalResourceService);
        ReflectionTestUtils.setField(passwordResetRequestService, "passwordResetEmailService", this.passwordResetEmailService);

        when(this.technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(email,organizationIdentifier)).thenReturn(technicalResource);
        when(this.passwordResetRequestDao.findByEmailAndOrganizationIdentifier(email,organizationIdentifier)).thenReturn(passwordResetRequest);

        passwordResetRequestService.createPasswordRequestReset(email,organizationIdentifier);

        verify(passwordResetRequestDao, times(1)).findByEmailAndOrganizationIdentifier(email,organizationIdentifier);
    }

    @Test
    public void testCreatePasswordRequestResetReturningNull(){
        PasswordResetRequestServiceImpl passwordResetRequestService = new PasswordResetRequestServiceImpl();
        TechnicalResource technicalResource = mock(TechnicalResource.class);
        String email = "qa.talent.cr@gmail.com";
        String organizationIdentifier = "monkey-labs";

        ReflectionTestUtils.setField(passwordResetRequestService, "crudDao", this.passwordResetRequestDao);
        ReflectionTestUtils.setField(passwordResetRequestService, "passwordResetRequestDao", this.passwordResetRequestDao);
        ReflectionTestUtils.setField(passwordResetRequestService, "technicalResourceService", this.technicalResourceService);
        ReflectionTestUtils.setField(passwordResetRequestService, "passwordResetEmailService", this.passwordResetEmailService);

        when(this.technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(email,organizationIdentifier)).thenReturn(technicalResource);
        when(this.passwordResetRequestDao.findByEmailAndOrganizationIdentifier(email,organizationIdentifier)).thenReturn(null);

        passwordResetRequestService.createPasswordRequestReset(email,organizationIdentifier);

        verify(passwordResetRequestDao, times(1)).findByEmailAndOrganizationIdentifier(email,organizationIdentifier);
    }

    @Test
    public void testIsTokenValid() {
        PasswordResetRequestServiceImpl passwordResetRequestService = new PasswordResetRequestServiceImpl();
        PasswordResetRequest passwordResetRequest = mock(PasswordResetRequest.class);
        String token = "token";

        ReflectionTestUtils.setField(passwordResetRequestService, "crudDao", this.passwordResetRequestDao);
        ReflectionTestUtils.setField(passwordResetRequestService, "passwordResetRequestDao", this.passwordResetRequestDao);

        when(passwordResetRequest.isValid()).thenReturn(true);
        when(this.passwordResetRequestDao.findByToken(token)).thenReturn(passwordResetRequest);

        passwordResetRequestService.isTokenValid(token);
    }

    @Test
    public void testIsTokenValidReturningNull() {
        PasswordResetRequestServiceImpl passwordResetRequestService = new PasswordResetRequestServiceImpl();
        String token = "token";

        ReflectionTestUtils.setField(passwordResetRequestService, "crudDao", this.passwordResetRequestDao);
        ReflectionTestUtils.setField(passwordResetRequestService, "passwordResetRequestDao", this.passwordResetRequestDao);

        when(this.passwordResetRequestDao.findByToken(token)).thenReturn(null);

        passwordResetRequestService.isTokenValid(token);
    }

    @Test
    public void testResetPasswordWithValidPassword() {
        PasswordResetRequestServiceImpl passwordResetRequestService = new PasswordResetRequestServiceImpl();
        PasswordResetRequest passwordResetRequest = mock(PasswordResetRequest.class);
        TechnicalResource technicalResource = mock(TechnicalResource.class);
        String token = "token";
        String newPassword = "Talent.123";

        ReflectionTestUtils.setField(passwordResetRequestService, "passwordEncoder", this.passwordEncoder);
        ReflectionTestUtils.setField(passwordResetRequestService, "crudDao", this.passwordResetRequestDao);
        ReflectionTestUtils.setField(passwordResetRequestService, "passwordResetRequestDao", this.passwordResetRequestDao);

        when(this.passwordResetRequestDao.findByToken(token)).thenReturn(passwordResetRequest);
        when(passwordResetRequest.getTechnicalResource()).thenReturn(technicalResource);
        when(this.passwordEncoder.encode(newPassword)).thenReturn("newPassword");
        when(passwordResetRequest.isValid()).thenReturn(true);

        passwordResetRequestService.resetPassword(token, newPassword);
    }
}
