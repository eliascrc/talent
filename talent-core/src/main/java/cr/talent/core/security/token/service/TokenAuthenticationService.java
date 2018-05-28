package cr.talent.core.security.token.service;

import cr.talent.model.TechnicalResource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Provides business logic services related to {@link TechnicalResource} entities.
 *
 * @author Josué León Sarkis
 */
public interface TokenAuthenticationService extends UserDetailsService {

    /**
     * Method that loads the user according to the authentication token specified.
     *
     * @param token String which specifies the authentication token to search for.
     * @return The TechnicalResource found or null if no resource with that token was found.
     * @throws UsernameNotFoundException
     */
    TechnicalResource loadUserByToken(String token);
}
