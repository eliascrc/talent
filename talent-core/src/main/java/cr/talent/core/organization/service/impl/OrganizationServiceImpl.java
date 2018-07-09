package cr.talent.core.organization.service.impl;

import cr.talent.core.image.organizationLogo.service.OrganizationLogoService;
import cr.talent.core.organization.dao.OrganizationDao;
import cr.talent.core.organization.service.OrganizationService;
import cr.talent.core.skill.dao.SkillDao;
import cr.talent.core.skillCategory.dao.SkillCategoryDao;
import cr.talent.model.*;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;
import cr.talent.support.exceptions.*;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.*;

/**
 * Default implementation of the {@link cr.talent.core.organization.service.OrganizationService}
 *
 * @author Elías Calderón, Josue Cubero
 */
@Service("organizationService")
@Transactional
public class OrganizationServiceImpl extends CrudServiceImpl<Organization, String> implements OrganizationService {

    private static final String BASE_LINK = ".talent.cr/#/join?token=";
    private static final String HTTP_PREFIX = "http://";

    @Autowired
    private OrganizationDao organizationDao;

    @Autowired
    private TechnicalResourceService technicalResourceService;

    @Autowired
    private SkillDao skillDao;

    @Autowired
    private SkillCategoryDao skillCategoryDao;

    private OrganizationLogoService organizationLogoService;

    public void init() {
        setCrudDao(this.organizationDao);
    }

    /**
     * @see cr.talent.core.organization.service.OrganizationService#getOrganizationByUniqueIdentifier(String)
     */
    public Organization getOrganizationByUniqueIdentifier(String uniqueIdentifier) {
        return this.organizationDao.getOrganizationByUniqueIdentifier(uniqueIdentifier);
    }

    /**
     * @see cr.talent.core.organization.service.OrganizationService#createOrganization(String, String, String)
     */
    public void createOrganization(String username, String uniqueIdentifier, String name) {
        final String alreadyCreatedOrganizationExceptionMsg = "The organization with unique identifier \"" +
                uniqueIdentifier + "\" has already been created within the system.";

        if (this.organizationDao.getOrganizationByUniqueIdentifier(uniqueIdentifier) != null)
            throw new AlreadyCreatedOrganizationException(alreadyCreatedOrganizationExceptionMsg);

        TechnicalResource technicalResource = this.technicalResourceService.getTechnicalResourceByUsernameWithNullOrganization(username);

        final String nonExistentUserWithNullOrganizationMsg = "The user " + username +
                " does not have a null organization, and the organization he has can not be overwritten.";

        if (technicalResource == null){
            throw  new NonExistentUserWithNullOrganization(nonExistentUserWithNullOrganizationMsg);
        }

        if (technicalResource.getStatus().equals(User.Status.ACTIVE)){
            //Create the new organization
            Organization organization = new Organization();
            organization.setUniqueIdentifier(uniqueIdentifier);
            organization.setName(name);
            organization.setState(OrganizationState.ENABLED);
            organization.setTotalUsers(1);

            //Assign the organization to a user
            technicalResource.setAdministrator(true);
            technicalResource.setOrganization(organization);

            this.technicalResourceService.update(technicalResource);
            this.organizationDao.create(organization);

            // Logs the user
            Authentication authentication = new UsernamePasswordAuthenticationToken(technicalResource, null, technicalResource.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }

    /**
     * @see cr.talent.core.organization.service.OrganizationService#createInviteLink(Organization)
     */
    @Override
    public String createInviteLink(Organization organization) {

        final String notNullInviteLinkInOrganizationExceptionMsg = "The invite link to create in the organization is not null";

        if (organization.getInviteLink() != null) {
            throw new NotNullInviteLinkInOrganizationException(notNullInviteLinkInOrganizationExceptionMsg);
        }

        String invitationToken = UUID.randomUUID().toString();
        String inviteLink = HTTP_PREFIX + organization.getUniqueIdentifier() + BASE_LINK + invitationToken;
        organization.setInviteLink(inviteLink);

        this.organizationDao.update(organization);

        return inviteLink;
    }

    /**
     * @see cr.talent.core.organization.service.OrganizationService#getValidInvitations(Organization)
     */
    @Override
    public Set<Invitation> getValidInvitations(Organization organization) {
        List<Invitation> invitationList = this.organizationDao.findValidInvitations(organization.getUniqueIdentifier());
        return new HashSet<>(invitationList);
    }

    /**
     * @see cr.talent.core.organization.service.OrganizationService#createSkill(SkillCategory, String, SkillType, Organization)
     */
    @Override
    public Skill createSkill(SkillCategory skillCategory, String skillName, SkillType skillType, Organization organization) {
        final String alreadyCreatedSkillMsg = "The given skill name is already created within the given skill category.";

        Set<Skill> skills = skillCategory.getSkills();

        for (Skill skill1 : skills) {
            if (skill1.getName().equals(skillName))
                throw new AlreadyCreatedSkillException(alreadyCreatedSkillMsg);
        }

        Skill skill = new Skill();
        skill.setName(skillName);
        skill.setCategory(skillCategory);
        skill.setSkillType(skillType);
        skill.setOrganization(organization);
        this.skillDao.create(skill);

        skills.add(skill);
        skillCategory.setSkills(skills);
        this.skillCategoryDao.update(skillCategory);

        return skill;
    }

    /**
     * Used to create an organization, sets the organization's logo as the default logo
     *
     * @param organization the organization that is being created
     * @return the created organization
     */
    @Override
    public String create(Organization organization) {
        this.organizationLogoService.setDefaultLogo(organization);
        return super.create(organization);
    }

    /**
     * @see cr.talent.core.organization.service.OrganizationService#createSkillCategory(String, Organization)
     */
    @Override
    public SkillCategory createSkillCategory(String skillCategoryName, Organization organization) {
        String alreadyCreatedSkillCategoryExceptionMsg = "That skill category is already created within the organization";

        Set<SkillCategory> skillCategories = organization.getSkillCategories();

        for (SkillCategory skillCategory1 : skillCategories) {
            if (skillCategory1.getName().equals(skillCategoryName))
                throw new AlreadyCreatedSkillCategoryException(alreadyCreatedSkillCategoryExceptionMsg);
        }

        SkillCategory skillCategory = new SkillCategory();
        skillCategory.setName(skillCategoryName);
        skillCategory.setOrganization(organization);
        this.skillCategoryDao.create(skillCategory);

        skillCategories.add(skillCategory);
        organization.setSkillCategories(skillCategories);
        this.organizationDao.update(organization);

        return skillCategory;
    }

}