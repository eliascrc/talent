package cr.talent.core.security.admin.service.impl;

import cr.talent.core.security.admin.dao.SystemAdministratorDao;
import cr.talent.core.security.admin.service.SystemAdministratorService;
import cr.talent.support.SecurityUtils;
import cr.talent.support.service.impl.CrudServiceImpl;
import cr.talent.model.SystemAdministrator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Default implementation of the {@link SystemAdministratorService}
 *
 * @author Josué León Sarkis
 */
@Service("systemAdministratorService")
@Transactional
public class SystemAdministratorServiceImpl extends CrudServiceImpl<SystemAdministrator, String> implements  SystemAdministratorService{

    /**
     * Data access object reference to perform SystemAdministrator operations.
     */
    @Autowired
    private SystemAdministratorDao systemAdministratorDao;

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
        setCrudDao(this.systemAdministratorDao);
    }

    /**
     * Method that finds a User by its username via the data access object of the service.
     * @param username String which specifies the user's username to find.
     * @return The result of the username search in the data access object.
     */
    public SystemAdministrator getSystemAdminByUsername(String username) {
        return this.systemAdministratorDao.findSysAdminByUsername(username);
    }

    /**
     * Method that loads the UserDetails according to the username specified.
     * @param username String which specifies the user's username to search for.
     * @return The UserDetails of the user found or null if no user with that username was found.
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = this.systemAdministratorDao.findSysAdminByUsername((username.toLowerCase()));
        if(user == null) {
            throw new UsernameNotFoundException("The user " + username + " was not found.");
        }
        return user;
    }

    /**
     * Method that creates a System Administrator user by supplying the correct and necessary information
     * to the data access object.
     * @param systemAdministrator The instance of System Administrator that must be created in the data base.
     * @return The created user.
     */
    @Override
    public String create(SystemAdministrator systemAdministrator) {
        SystemAdministrator foundSystemAdministrator = this.systemAdministratorDao.findSysAdminByUsername((systemAdministrator.getUsername().toLowerCase()));
        if(foundSystemAdministrator != null) {
            throw new IllegalArgumentException("The administrator with name: " + systemAdministrator.getUsername() + " already exists.");
        }

        systemAdministrator.setUsername(systemAdministrator.getUsername().toLowerCase());
        SecurityUtils.validatePassword(systemAdministrator.getPassword());
        systemAdministrator.setPassword(passwordEncoder.encode(systemAdministrator.getPassword()));
        systemAdministrator.setEnabled(true);

        return super.create(systemAdministrator);
    }

}
