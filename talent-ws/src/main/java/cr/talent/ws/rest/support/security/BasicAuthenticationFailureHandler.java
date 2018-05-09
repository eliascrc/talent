package cr.talent.ws.rest.support.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A custom authentication failure handler that returns a HTTP code of 401 when the authentication fails.
 *
 * @author Rodrigo A. Bartels
 * @version 1.0
 * @since 5/28/12
 */
@Component("loginFailureHandler")
public class BasicAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        if(request.getHeader("Origin") != null){
            response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, Origin, Authorization");
        }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed: " + exception.getMessage());
    }
}
