package cr.talent.core.security.technicalResource.service.impl;

import cr.talent.core.security.technicalResource.dao.TechnicalResourceDao;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.support.SecurityUtils;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
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
public class TechnicalResourceServiceImpl extends CrudServiceImpl<TechnicalResource, String> implements  TechnicalResourceService{

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
    public void init(){
        setCrudDao(this.technicalResourceDao);
    }

    /**
     * @see cr.talent.core.security.technicalResource.service.TechnicalResourceService#getTechnicalResourceByUsername(String)
     */
    public TechnicalResource getTechnicalResourceByUsername(String username) {
        return this.technicalResourceDao.findTechnicalResourceByUsername(username);
    }

    /**
     * Method that loads the UserDetails according to the username specified.
     * @param username String which specifies the user's username to search for.
     * @return The UserDetails of the user found or null if no user with that username was found.
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = this.technicalResourceDao.findTechnicalResourceByUsername((username.toLowerCase()));
        if(user == null) {
            throw new UsernameNotFoundException("The user " + username + " was not found.");
        }
        return user;
    }

    /**
     * Method that creates a System Administrator user by supplying the correct and necessary information
     * to the data access object.
     * @param technicalResource The instance of System Administrator that must be created in the data base.
     * @return The created user.
     */
    @Override
    public String create(TechnicalResource technicalResource) {
        TechnicalResource foundSystemAdministrator = this.technicalResourceDao.findTechnicalResourceByUsername((technicalResource.getUsername().toLowerCase()));
        if(foundSystemAdministrator != null) {
            throw new IllegalArgumentException("The administrator with name: " + technicalResource.getUsername() + " already exists.");
        }

        technicalResource.setUsername(technicalResource.getUsername().toLowerCase());
        SecurityUtils.validatePassword(technicalResource.getPassword());
        technicalResource.setPassword(passwordEncoder.encode(technicalResource.getPassword()));
        technicalResource.setEnabled(true);
        technicalResource.setToken(UUID.randomUUID().toString());

        return super.create(technicalResource);
    }

    /**
     * Method that creates a System Administrator user by supplying the correct and necessary information
     * to the data access object.
     * @param technicalResource The instance of System Administrator that must be created in the data base.
     * @return The updated user.
     */
    @Override
    public void update(TechnicalResource technicalResource) {

        technicalResource.setUsername(technicalResource.getUsername().toLowerCase());
        SecurityUtils.validatePassword(technicalResource.getPassword());
        technicalResource.setPassword(passwordEncoder.encode(technicalResource.getPassword()));
        technicalResource.setEnabled(true);

        super.update(technicalResource);
    }
}
