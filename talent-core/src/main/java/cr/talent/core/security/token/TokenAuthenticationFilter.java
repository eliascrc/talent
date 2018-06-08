package cr.talent.core.security.token;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Filter that is triggered after the form login authentication provider in the spring security chain. It obtains
 * the token parameter from the header and tries to authenticate the user with the specified token, by first checking
 * if there's a user associated with it.
 *
 * @author Josué León Sarkis
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public TokenAuthenticationFilter() {
        super("/ws/login/token");
    }

    /**
     * Retrieves the token parameter from the request's header and tries to find a user associated with it. It then
     * tries to authenticate the respective user.
     *
     * @param httpServletRequest  the http request to the web service
     * @param httpServletResponse the http response which will eventually be returned
     * @return an Authentication instance of the attempted authentication request
     * @throws AuthenticationException if authentication fails
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException {
        Authentication authentication;
        String token = httpServletRequest.getParameter("token");

        if (token == null) {
            token = "";
        }
        token = token.trim();

        UsernamePasswordAuthenticationToken authenticationRequest = new UsernamePasswordAuthenticationToken(token, token);
        authentication = this.getAuthenticationManager().authenticate(authenticationRequest);

        if (authentication.isAuthenticated()) {
            httpServletRequest.getSession().setAttribute("token", token);
        }

        return authentication;
    }

}
