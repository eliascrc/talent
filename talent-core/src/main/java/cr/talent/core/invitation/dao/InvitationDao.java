package cr.talent.core.invitation.dao;

import cr.talent.model.Invitation;
import cr.talent.support.dao.CrudDao;

import java.util.Set;

/**
 * Data access object for all the {@link cr.talent.model.Invitation} related operations.
 *
 * @author Elías Calderón
 */
public interface InvitationDao extends CrudDao<Invitation, String> {

    /**
     * Retrieves the invitation that corresponds to a email.
     * @return the invitation if found, null if not.
     */
    Invitation findInvitationByEmail(String email);

}