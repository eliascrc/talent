package cr.talent.core.passwordResetRequest.service.impl;

import cr.talent.core.passwordResetRequest.dao.PasswordResetRequestDao;
import cr.talent.core.passwordResetRequest.service.PasswordResetRequestService;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.model.PasswordResetRequest;
import cr.talent.model.TechnicalResource;
import cr.talent.support.SecurityUtils;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class PasswordResetRequestImpl  extends CrudServiceImpl<PasswordResetRequest, String> implements PasswordResetRequestService {

    @Autowired
    private PasswordResetRequestDao passwordResetRequestDao;

    @Autowired
    private TechnicalResourceService technicalResourceService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void init() {
        setCrudDao(this.passwordResetRequestDao);
    }

    @Override
    public boolean isTokenValid(String token){
        PasswordResetRequest passwordResetRequest = passwordResetRequestDao.findByToken(token);
        if (passwordResetRequest == null ){
            return false;
        }
        return passwordResetRequest.isValid();
    }

    @Override
    public void createPasswordRequestReset(String email) {
        PasswordResetRequest passwordResetReq = this.passwordResetRequestDao.findByEmail(email);
        if (passwordResetReq != null){
            passwordResetReq.setValid(false);
        }
        TechnicalResource technicalResource = this.technicalResourceService.getTechnicalResourceByUsername(email);
        if(technicalResource != null){
            PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
            passwordResetRequest.setTechnicalResource(technicalResource);
            passwordResetRequest.setEmail(technicalResource.getUsername());
            passwordResetRequest.setToken(UUID.randomUUID().toString());
            passwordResetRequest.setValid(true);

            this.create(passwordResetRequest);

            // send the email.
        }
    }

    @Override
    public boolean resetPassword(String token , String newPassword){
        boolean resetPassword = false;
        try{
            SecurityUtils.validatePassword(newPassword);
            if (this.isTokenValid(token)){
                PasswordResetRequest passwordResetRequest = passwordResetRequestDao.findByToken(token);
                TechnicalResource technicalResource =  passwordResetRequest.getTechnicalResource();
                technicalResource.setPassword(this.passwordEncoder.encode(newPassword));
                passwordResetRequest.setValid(false);
                resetPassword= true;
            }
        }catch (Exception e){
        }
        return resetPassword;
    }
}
