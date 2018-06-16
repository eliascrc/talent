package cr.talent.core.security.technicalResource.service;


import cr.talent.support.service.CrudService;
import cr.talent.model.TechnicalResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Provides business logic services related to {@link TechnicalResource} entities.
 *
 * @author Josué León Sarkis, Fabian Roberto Leandro
 */
public interface TechnicalResourceService extends CrudService<TechnicalResource, String>, UserDetailsService {

    /**
     * Method that finds a TechnicalResource by its username and organization identifier via the data access object of
     * the service.
     *
     * @param username                String that specifies the user's username to find.
     * @param organizationIdentifier  String that specifies the user's organization identifier.
     * @return A TechnicalResource representation of the User..
     */
    TechnicalResource getTechnicalResourceByUsernameAndOrganizationIdentifier(String username,
                                                                              String organizationIdentifier);

        /**
     * Method that finds a User by its username via the data access object of the service.
     * @param username String which specifies the user's username to find.
     * @return The result of the username search in the data access object.
     */
    TechnicalResource getTechnicalResourceByUsername(String username);

    /**
     * Method that finds a TechnicalResource by its username and organization identifier via the data access object of
     * the service.
     *
     * @param username                String that specifies the user's username to find.
     * @param organizationIdentifier  String that specifies the user's organization identifier.
     * @return A UserDetails representation of the User.
     */
    UserDetails loadByUsernameAndOrganizationIdentifier(String username, String organizationIdentifier)
            throws UsernameNotFoundException;

}
