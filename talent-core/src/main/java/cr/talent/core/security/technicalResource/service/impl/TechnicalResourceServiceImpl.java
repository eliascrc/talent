package cr.talent.core.security.technicalResource.service.impl;

import cr.talent.core.careerPath.service.CareerPathService;
import cr.talent.core.image.profilePicture.service.ProfilePictureService;
import cr.talent.core.security.technicalResource.dao.TechnicalResourceDao;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.core.technicalPosition.service.TechnicalPositionService;
import cr.talent.model.*;
import cr.talent.support.SecurityUtils;
import cr.talent.support.exceptions.AlreadyAssignedTechnicalPositionException;
import cr.talent.support.exceptions.NonExistentCapabilityException;
import cr.talent.support.exceptions.NonExistentCapabilityLevelException;
import cr.talent.support.exceptions.UserDoesNotHaveRequiredSkillsException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Default implementation of the {@link TechnicalResourceService}
 *
 * @author Josué León Sarkis, Fabián Roberto Leandro
 */
@Service("technicalResourceService")
@Transactional
public class TechnicalResourceServiceImpl extends CrudServiceImpl<TechnicalResource, String> implements TechnicalResourceService {

    /**
     * Data access object reference to perform TechnicalResource operations.
     */
    @Autowired
    private TechnicalResourceDao technicalResourceDao;

    /**
     * Service to perform TechnicalPosition operations, including its business logic.
     */
    @Autowired
    private TechnicalPositionService technicalPositionService;

    /**
     * Service to perform CareerPath operations, including its business logic.
     */
    @Autowired
    private CareerPathService careerPathService;

    @Autowired
    private ProfilePictureService profilePictureService;

    /**
     * Password encoder provided by spring to cipher a user's password and store it in the database so that it can
     * be compared later when the user enters his password.
     */
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Initialization method that sets the respective fields during bean instantiation.
     */
    public void init() {
        setCrudDao(this.technicalResourceDao);
    }

    /**
     * @see cr.talent.core.security.technicalResource.service.TechnicalResourceService#getTechnicalResourceByUsernameAndOrganizationIdentifier(String, String)
     */
    public TechnicalResource getTechnicalResourceByUsernameAndOrganizationIdentifier(String username, String organizationIdentifier) {
        return this.technicalResourceDao.findTechnicalResourceByUsernameAndOrganizationIdentifier(username, organizationIdentifier);
    }

    /**
     * @see cr.talent.core.security.technicalResource.service.TechnicalResourceService#getTechnicalResourceByUsernameWithNullOrganization(String)
     */
    public TechnicalResource getTechnicalResourceByUsernameWithNullOrganization(String username) {
        return this.technicalResourceDao.findTechnicalResourceByUsernameWithNullOrganization(username);
    }

    /**
     * @see cr.talent.core.security.technicalResource.service.TechnicalResourceService#getTechnicalResourceByUsername(String)
     */
    public TechnicalResource getTechnicalResourceByUsername(String username) {
        return this.technicalResourceDao.findTechnicalResourceByUsername(username);
    }

    /**
     * @see cr.talent.core.security.technicalResource.service.TechnicalResourceService#loadByUsernameAndOrganizationIdentifier(String, String)
     */
    public UserDetails loadByUsernameAndOrganizationIdentifier(String username, String organizationIdentifier)
            throws UsernameNotFoundException {
        UserDetails user = this.technicalResourceDao.findTechnicalResourceByUsernameAndOrganizationIdentifier(username.toLowerCase(), organizationIdentifier.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("The user " + username + " was not found in the " + organizationIdentifier + " organization.");
        }
        return user;
    }

    /**
     * Implemented because this class must implement UserDetails, however it is impossible to find a user by username
     * alone, since a user may be in more than one organization.
     * Use cr.talent.core.security.technicalResource.service.impl.{@link TechnicalResourceServiceImpl#loadByUsernameAndOrganizationIdentifier(String, String)}
     * instead.
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        throw new UsernameNotFoundException("Cannot find user by username alone.");
    }

    /**
     * Method that creates a System Administrator user by supplying the correct and necessary information
     * to the data access object.
     *
     * @param technicalResource The instance of System Administrator that must be created in the data base.
     * @return The created user.
     */
    @Override
    public String create(TechnicalResource technicalResource) {

        TechnicalResource foundTechnicalResource;
        if (technicalResource.getOrganization() != null) {
            foundTechnicalResource = this.technicalResourceDao.
                    findTechnicalResourceByUsernameAndOrganizationIdentifier((technicalResource.getUsername().toLowerCase()),
                            technicalResource.getOrganization().getUniqueIdentifier().toLowerCase());
        } else {
            foundTechnicalResource = this.technicalResourceDao.
                    findTechnicalResourceByUsernameWithNullOrganization((technicalResource.getUsername().toLowerCase()));
        }
        if (foundTechnicalResource != null) {
            throw new IllegalArgumentException("The technical resource with name: " + technicalResource.getUsername() + " already exists.");
        }

        technicalResource.setUsername(technicalResource.getUsername().toLowerCase());
        SecurityUtils.validatePassword(technicalResource.getPassword());
        technicalResource.setPassword(passwordEncoder.encode(technicalResource.getPassword()));
        technicalResource.setEnabled(true);
        technicalResource.setToken(UUID.randomUUID().toString());
        this.profilePictureService.setDefaultProfilePicture(technicalResource);

        return super.create(technicalResource);
    }

    /**
     * Method that updates a Technical Resource user by supplying the correct and necessary information
     * to the data access object.
     *
     * @param technicalResource The instance of TechnicalResource that must be updated in the data base.
     */
    @Override
    public void update(TechnicalResource technicalResource) {

        technicalResource.setUsername(technicalResource.getUsername().toLowerCase());

        super.update(technicalResource);
    }

    /**
     * @see TechnicalResourceService#assignTechnicalPositionToTechnicalResource(String, String, Organization, TechnicalResource, Date)
     */
    @Override
    public void assignTechnicalPositionToTechnicalResource(String capability, String capabilityLevel, Organization organization, TechnicalResource technicalResource, Date startDate) {
        final String nonExistentCapabilityMessage = "The capability is nonexistent.";
        final String nonExistentcapabilityLevelMessage = "The capability level is nonexistent.";
        final String alreadyAssignedTechnicalPositionMessage = "The technical position is already assigned to " + technicalResource.getFirstName() + " " +
                technicalResource.getLastName() + ".";
        final String userDoesNotHaveRequiredSkillsMessage = technicalResource.getFirstName() + " " +
                technicalResource.getLastName() + " does not have the required skills to be assigned the " + capability + " capability.";

        // Verify that the organization has the specified capability
        Capability foundCapability = null;
        if (organization.getCapabilities() != null)
            for (Capability organizationCapability : organization.getCapabilities()) {
                if (organizationCapability.getName().equals(capability)) {
                    foundCapability = organizationCapability;
                    break;
                }
            }
        if (foundCapability == null)
            throw new NonExistentCapabilityException(nonExistentCapabilityMessage);

        // Verify that the found capability has the specified capability level
        CapabilityLevel foundCapabilityLevel = null;
        if (foundCapability.getLevelHierarchy() != null)
            for (CapabilityLevel organizationCapabilityLevel : foundCapability.getLevelHierarchy()) {
                if (organizationCapabilityLevel.getName().equals(capabilityLevel)) {
                    foundCapabilityLevel = organizationCapabilityLevel;
                    break;
                }
            }
        if (foundCapabilityLevel == null)
            throw new NonExistentCapabilityLevelException(nonExistentcapabilityLevelMessage);

        TechnicalPosition positionToBeAssigned = new TechnicalPosition();
        positionToBeAssigned.setCapabilityLevel(foundCapabilityLevel);

        // Verify that the user does not already hold the position to be assigned
        if (positionToBeAssigned.equals(technicalResource.getTechnicalPosition()))
            throw new AlreadyAssignedTechnicalPositionException(alreadyAssignedTechnicalPositionMessage);

        // Verify that the user has the skills required to hold the position to be assigned
        Set<Skill> technicalResourceSkills = technicalResource.getSkills();
        Set<Skill> requiredSkills = foundCapabilityLevel.getRequiredSkills();

        // If the required skills are null or empty, the user automatically has the required skills.
        // Otherwise, if the user's skills are null or do not contain the required skills, throw the exception.
        if (requiredSkills != null && !requiredSkills.isEmpty()
                && (technicalResourceSkills == null || !technicalResourceSkills.containsAll(requiredSkills)))
            throw new UserDoesNotHaveRequiredSkillsException(userDoesNotHaveRequiredSkillsMessage);

        // Persist the career path in case the user does not have one
        CareerPath careerPath = technicalResource.getCareerPath();
        if (careerPath == null) {
            // User has never had an assigned position, create their career path
            careerPath = new CareerPath();
            this.careerPathService.create(careerPath);

            // Update the resource with a reference to their newly created career path
            technicalResource.setCareerPath(careerPath);
            this.update(technicalResource);
        }

        // Persist the technical position
        positionToBeAssigned.setTechnicalResource(technicalResource);
        positionToBeAssigned.setCareerPath(careerPath);
        positionToBeAssigned.setStartDate(startDate);
        this.technicalPositionService.create(positionToBeAssigned);
    }

}
