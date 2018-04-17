package cr.talent.core.security.admin.dao;

import cr.talent.model.SystemAdministrator;
import cr.talent.support.dao.CrudDao;
import org.springframework.stereotype.Repository;

/**
 * Data access object for all the {@link SystemAdministrator} related operations.
 *
 * @author Josué León Sarkis
 */

public interface SystemAdministratorDao extends CrudDao<SystemAdministrator, String>{

    /**
     *  Finds a system administrator by its username.
     * @param username The system administrator's username to search for.
     * @return The SystemAdministrator with the respective username.
     */
    SystemAdministrator findSysAdminByUsername(String username);

}
