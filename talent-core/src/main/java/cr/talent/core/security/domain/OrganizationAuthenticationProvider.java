package cr.talent.core.security.domain;

import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

public class OrganizationAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    TechnicalResourceService technicalResourceService;

    /**
     * Checks if the credentials (username, password) match.
     *
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(
            UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}

		String presentedPassword = authentication.getCredentials().toString();

		if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
			logger.debug("Authentication failed: password does not match stored value");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}
    }

    /**
     * Search for a technical resource using the username, password and organization identifier received in a sign in
     * request.
     *
     * @param username                            The user's username.
     * @param usernamePasswordAuthenticationToken Contains the user's credentials, our custom organization filter sets
     *                                            the user's organization identifier.
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
        String organizationIdentifier =(String)usernamePasswordAuthenticationToken.getDetails();
        if(StringUtils.isEmpty(organizationIdentifier) || StringUtils.isEmpty(username))
            throw new UsernameNotFoundException("Received null organization identifier.");

        UserDetails user;
        try {
            user = this.technicalResourceService.loadByUsernameAndOrganizationIdentifier(username,
                    (String)usernamePasswordAuthenticationToken.getDetails());
        } catch (UsernameNotFoundException e) {
            throw e;
        }
        return user;
    }

}
