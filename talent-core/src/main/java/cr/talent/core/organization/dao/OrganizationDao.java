package cr.talent.core.organization.dao;

import cr.talent.model.Invitation;
import cr.talent.model.Organization;
import cr.talent.support.dao.CrudDao;

import java.util.List;
import java.util.Set;

/**
 * Data access object for all the {@link cr.talent.model.Organization} related operations.
 *
 * @author Elías Calderón
 */
public interface OrganizationDao extends CrudDao<Organization, String> {

    /**
     * Gets an organization with a given id.
     * @param uniqueIdentifier the organization's unique identifier.
     * @return The organization if found, null if not found.
     */
    Organization getOrganizationByUniqueIdentifier(String uniqueIdentifier);

    /**
     * Retrieves the valid invitations related to an organization
     * @param uniqueIdentifier the orgnization's unique identifier
     * @return the valid invitation set, or empty set if there weren't any.
     */
    List<Invitation> findValidInvitations(String uniqueIdentifier);

}