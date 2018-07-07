package cr.talent.core.organization.service;

import cr.talent.model.*;
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
     * Creates a new skill for an organization.
     * @param skillCategory the skill category to update.
     * @param skillName the new skill name.
     * @param skillType the skill type.
     * @param organization the organization of the skill category.
     *
     * @return the newly created skill
     */
    Skill createSkill(SkillCategory skillCategory, String skillName, SkillType skillType, Organization organization);
}