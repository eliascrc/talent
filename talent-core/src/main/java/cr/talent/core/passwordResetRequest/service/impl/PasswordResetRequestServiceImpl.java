package cr.talent.core.passwordResetRequest.service.impl;

import cr.talent.core.email.passwordResetEmail.service.PasswordResetEmailService;
import cr.talent.core.passwordResetRequest.dao.PasswordResetRequestDao;
import cr.talent.core.passwordResetRequest.service.PasswordResetRequestService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.PasswordResetRequest;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

/**
 * Default implementation of the {@link cr.talent.core.passwordResetRequest.service.PasswordResetRequestService}
 *
 * @author María José Cubero
 */

@Service("passwordResetRequestService")
@Transactional
public class PasswordResetRequestServiceImpl extends CrudServiceImpl<PasswordResetRequest, String> implements PasswordResetRequestService {

    @Autowired
    private PasswordResetRequestDao passwordResetRequestDao;

    @Autowired
    private TechnicalResourceService technicalResourceService;

    @Autowired
    private PasswordResetEmailService passwordResetEmailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void init() {
        setCrudDao(this.passwordResetRequestDao);
    }

    @Override
    public void createPasswordRequestReset(String email, String organizationIdentifier) {
        PasswordResetRequest passwordResetReq = this.passwordResetRequestDao.findByEmailAndOrganizationIdentifier(email,organizationIdentifier);
        if (passwordResetReq != null)
            passwordResetReq.setValid(false);

        TechnicalResource technicalResource = this.technicalResourceService.
                getTechnicalResourceByUsernameAndOrganizationIdentifier(email, organizationIdentifier);
        if (technicalResource != null) {
            PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
            passwordResetRequest.setTechnicalResource(technicalResource);
            passwordResetRequest.setEmail(technicalResource.getUsername());
            String token = UUID.randomUUID().toString();
            passwordResetRequest.setToken(token);
            passwordResetRequest.setValid(true);

            this.create(passwordResetRequest);

            this.passwordResetEmailService.sendPasswordResetMail(email, passwordResetRequest);
        }
    }

    @Override
    public boolean isTokenValid(String token) {
        PasswordResetRequest passwordResetRequest = this.passwordResetRequestDao.findByToken(token);
        return passwordResetRequest != null ? passwordResetRequest.isValid() : false;
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        SecurityUtils.validatePassword(newPassword);
        PasswordResetRequest passwordResetRequest = this.passwordResetRequestDao.findByToken(token);
        TechnicalResource technicalResource = passwordResetRequest.getTechnicalResource();
        technicalResource.setPassword(this.passwordEncoder.encode(newPassword));
        passwordResetRequest.setValid(false);

        Authentication authentication = new UsernamePasswordAuthenticationToken(technicalResource, null, technicalResource.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
