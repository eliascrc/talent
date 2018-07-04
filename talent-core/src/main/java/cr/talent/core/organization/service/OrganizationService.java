package cr.talent.core.organization.service;

import cr.talent.model.Invitation;
import cr.talent.model.Organization;
import cr.talent.model.SkillCategory;
import cr.talent.support.exceptions.AlreadyCreatedOrganizationException;
import cr.talent.support.service.CrudService;

import java.util.Set;

/**
 * Provides business logic services related to {@link cr.talent.model.Organization} entities.
 *
 * @author Elías Calderón
 */
public interface OrganizationService extends CrudService<Organization, String> {

    /**
     * Gets an organization with a given id.
     * @param uniqueIdentifier the organization's unique identifier
     * @return The organization if found, null if not found.
     */
    Organization getOrganizationByUniqueIdentifier(String uniqueIdentifier);

    /**
     * Persists an organization with the respective business logic.
     * @param username
     * @param uniqueIdentifier
     * @param name
     */
    void createOrganization(String username, String uniqueIdentifier, String name);

    /**
     * Creates a new invite link for an organization.
     * @param organization the organization to create an invite link for.
     * @return the invite link of the organization.
     */
    String createInviteLink(Organization organization);

    /**
     * Gets a Set of valid invitations of the organization
     * @param organization the organization to search for.
     * @return a set of invitations, or an empty set if no invitations were found.
     */
    Set<Invitation> getValidInvitations(Organization organization);

    /**
     * Creates a new skill category for an organization.
     * @param skillCategoryName the new skill name.
     * @param organization the organization to create an invite link for.
     * @return the invite link of the organization.
     */
    SkillCategory createSkillCategory(String skillCategoryName, Organization organization);
}