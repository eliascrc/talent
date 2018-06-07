package cr.talent.core.security.token.service.impl;

import cr.talent.core.security.technicalResource.dao.TechnicalResourceDao;
import cr.talent.core.security.token.service.TokenAuthenticationService;
import cr.talent.model.TechnicalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Default implementation of the {@link TokenAuthenticationService}. Provides
 * business logic services related to the Token Authentication mechanism for users.
 *
 * @author Josué León Sarkis
 */
@Service("tokenAuthenticationService")
@Transactional
public class TokenAuthenticationServiceImpl implements TokenAuthenticationService {

    @Autowired
    private TechnicalResourceDao technicalResourceDao;

    /**
     * @see cr.talent.core.security.token.service.TokenAuthenticationService#loadUserByUsername(String)
     */
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        UserDetails user = this.technicalResourceDao.findByAuthenticationToken((token));
        if (user == null) {
            throw new UsernameNotFoundException("The user was not found.");
        }
        return user;
    }

}
