package cr.talent.core.security.admin.service;

import cr.talent.model.SystemAdministrator;
import cr.talent.support.service.CrudService;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Provides business logic services related to {@link SystemAdministrator} entities.
 *
 * @author Josué León Sarkis
 */
public interface SystemAdministratorService extends CrudService<SystemAdministrator, String>, UserDetailsService{

    SystemAdministrator getSystemAdminByUsername(String username);

}
