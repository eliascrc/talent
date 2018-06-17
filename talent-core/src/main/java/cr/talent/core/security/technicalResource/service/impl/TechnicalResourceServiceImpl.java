package cr.talent.core.security.technicalResource.service.impl;

import cr.talent.core.security.technicalResource.dao.TechnicalResourceDao;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.support.SecurityUtils;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import cr.talent.model.TechnicalResource;

import java.util.UUID;

/**
 * Default implementation of the {@link TechnicalResourceService}
 *
 * @author Josué León Sarkis
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
            throw new UsernameNotFoundException("The user " + username + " was not found in the "+ organizationIdentifier +" organization.");
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
        if(technicalResource.getOrganization() != null) {
            foundTechnicalResource = this.technicalResourceDao.
                    findTechnicalResourceByUsernameAndOrganizationIdentifier((technicalResource.getUsername().toLowerCase()),
                            technicalResource.getOrganization().getUniqueIdentifier().toLowerCase());
        } else {
            foundTechnicalResource = this.technicalResourceDao.
                    findTechnicalResourceByUsername((technicalResource.getUsername().toLowerCase()));
        }
        if (foundTechnicalResource != null) {
            throw new IllegalArgumentException("The technical resource with name: " + technicalResource.getUsername() + " already exists.");
        }

        technicalResource.setUsername(technicalResource.getUsername().toLowerCase());
        SecurityUtils.validatePassword(technicalResource.getPassword());
        technicalResource.setPassword(passwordEncoder.encode(technicalResource.getPassword()));
        technicalResource.setEnabled(true);
        technicalResource.setToken(UUID.randomUUID().toString());

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

}
