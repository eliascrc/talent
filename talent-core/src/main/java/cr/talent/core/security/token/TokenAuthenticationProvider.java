package cr.talent.core.security.token;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Custom authentication provider for the Token Authentication mechanism, in order to authenticate the user against
 * the token specified.
 */
public class TokenAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private UserDetailsService userDetailsService;

    /**
     * Checks if the token provided is null.
     *
     * @param userDetails                         the user details of the user being authenticated
     * @param usernamePasswordAuthenticationToken token authentication request
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        String token = usernamePasswordAuthenticationToken.getCredentials().toString();
        if (token.equals("") || !isTokenUUID(token)) {
            throw new BadCredentialsException("Token authentication failed: invalid token");
        }
    }

    /**
     * Retrieves the user to be authenticated by querying it from the database with the authentication token
     * provided.
     *
     * @param token                               authentication token to find
     * @param usernamePasswordAuthenticationToken token authentication request
     * @return UserDetails of the user who the authentication token matched
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(String token, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        UserDetails user;

        try {
            user = this.userDetailsService.loadUserByUsername(token);
        } catch (UsernameNotFoundException e) {
            throw e;
        }

        return user;
    }

    /**
     * Checks if the authentication token is a valid UUID
     *
     * @param token
     * @return true if it is valid, otherwise false
     */
    private boolean isTokenUUID(String token) {
        return token.matches("[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}");
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

}
