package cr.talent.ws.rest.support.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CORS Enabled Success AuthenticationHandler. This handler will return a 200 OK HTTP Status code if a CORS
 * request is detected (by checking the existence of the Origin Header in the request). If not then a 302
 * redirect is send.
 *
 * @author Rodrigo A. Bartels
 * @version 1.0
 * @since 5/29/12
 */
@Component("successLoginHandler")
public class SuccessAuthenticationHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final transient Logger logger = LoggerFactory.getLogger(SuccessAuthenticationHandler.class);

    private void init(){
        this.setDefaultTargetUrl("/ws/authenticated");
    }

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        if(request.getHeader("Origin") != null){
            response.setStatus(HttpServletResponse.SC_OK);
            response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT, OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type, Origin, Authorization");
        }else
            super.onAuthenticationSuccess(request, response, authentication);
    }
}