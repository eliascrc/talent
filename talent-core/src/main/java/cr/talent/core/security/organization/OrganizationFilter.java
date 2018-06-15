package cr.talent.core.security.organization;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;

/**
 * Filter that replaces FORM_LOGIN_FILTER in the Spring Security filter chain, in order to add the organization identifier
 * to the authentication token before attempting to authenticate.
 *
 * @author Fabian Roberto Leandro
 */
public class OrganizationFilter extends UsernamePasswordAuthenticationFilter {

    /**
     * Overriding this method allows us to send an additional authentication attribute without changing the default
     * Spring Security implementation of {@org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication}
     * In this case we set the organization identifier on the UsernamePasswordAuthenticationToken instance sent to
     * out provider to be authenticated.
     *
     * @param request the http request to the web service
     * @param authRequest the UsernamePasswordAuthenticationToken to which we will add organization identifier as a detail
     */
    @Override
    protected void setDetails(HttpServletRequest request,
			UsernamePasswordAuthenticationToken authRequest) {
        String[] splitServerName = request.getServerName().split("\\.");
        if(splitServerName.length == 3) {
            authRequest.setDetails(splitServerName[0]);
        }
    }
}
