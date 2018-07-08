package cr.talent.core.security.technicalResource.service;


import cr.talent.model.Feedback;
import cr.talent.model.Organization;
import cr.talent.support.exceptions.AlreadyAssignedTechnicalPositionException;
import cr.talent.support.exceptions.NonExistentCapabilityException;
import cr.talent.support.exceptions.NonExistentCapabilityLevelException;
import cr.talent.support.exceptions.UserDoesNotHaveRequiredSkillsException;
import cr.talent.support.service.CrudService;
import cr.talent.model.TechnicalResource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.Set;

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
     * @param username               String that specifies the user's username to find.
     * @param organizationIdentifier String that specifies the user's organization identifier.
     * @return A TechnicalResource representation of the User..
     */
    TechnicalResource getTechnicalResourceByUsernameAndOrganizationIdentifier(String username,
                                                                              String organizationIdentifier);

    /**
     * Method that finds a User by its username via the data access object of the service.
     *
     * @param username String which specifies the user's username to find.
     * @return The result of the username search in the data access object.
     */
    TechnicalResource getTechnicalResourceByUsernameWithNullOrganization(String username);

    /**
     * Method that finds a User by its username via the data access object of the service.
     *
     * @param username String which specifies the user's username to find.
     * @return The result of the username search in the data access object.
     */
    TechnicalResource getTechnicalResourceByUsername(String username);

    /**
     * Method that finds a TechnicalResource by its username and organization identifier via the data access object of
     * the service.
     *
     * @param username               String that specifies the user's username to find.
     * @param organizationIdentifier String that specifies the user's organization identifier.
     * @return A UserDetails representation of the User.
     */
    UserDetails loadByUsernameAndOrganizationIdentifier(String username, String organizationIdentifier)
            throws UsernameNotFoundException;

    /**
     * Persists a {@link cr.talent.model.TechnicalPosition} with the respective business logic..
     * @param capability        the capability asociated with the technical position that will try to be assigned to the technical resource.
     * @param capabilityLevel   the capability level within the specified capability
     * @param organization      the technical resource's organization.
     * @param technicalResource the technical resource.
     * @param startDate         the date that the technical resource was/will be assigned the technical position
     */
    void assignTechnicalPositionToTechnicalResource(String capability, String capabilityLevel,
                                                    Organization organization, TechnicalResource technicalResource, Date startDate);

    /**
     * Used to update the basic information of a technical resource
     * @param technicalResource the technical resource that will have their information edited
     * @param firstName the new first name for the technical resource
     * @param lastName the new last name for the technical resource
     * @param nickname the new nickname for the technical resource
     */
    void editBasicInformation(TechnicalResource technicalResource, String firstName, String lastName, String nickname);

    /**
     * Provides the business logic to obtain a user's feedback (warnings/kudos) when a technical resource looks at a
     * technical resource's profile.
     * @param observer the technical resource obtaining the information
     * @param observee the technical resource whose received feedback is being obtained
     * @return a Set with all the obsewrvee's feedback if the observer is equal to observee or their lead in any project
     *         a Set with only the observee's kudos otherwise.
     */
    Set<Feedback> getFeedback(TechnicalResource observer, TechnicalResource observee);
}
