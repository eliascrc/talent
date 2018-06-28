package cr.talent.core.organizationSkill.service.impl;

import cr.talent.core.organizationSkill.dao.OrganizationSkillDao;
import cr.talent.core.organizationSkill.service.OrganizationSkillService;
import cr.talent.model.Organization;
import cr.talent.model.Skill;
import cr.talent.model.SkillCategory;
import cr.talent.model.TechnicalResource;
import cr.talent.support.exceptions.AlreadyAssignedSkillException;
import cr.talent.support.exceptions.EmptySkillException;
import cr.talent.support.exceptions.NonExistentSkillException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of the {@link cr.talent.core.organizationSkill.service.OrganizationSkillService}.
 *
 * @author Elías Calderón, Josue Cubero
 */
@Service("organizationSkillService")
@Transactional
public class OrganizationSkillServiceImpl extends CrudServiceImpl<Skill, String> implements OrganizationSkillService {

    @Autowired
    private OrganizationSkillDao organizationSkillDao;

    public void init() {
        setCrudDao(this.organizationSkillDao);
    }

    /**
     * @see cr.talent.core.organizationSkill.service.OrganizationSkillService#assignSkillToTechnicalResource(List, Organization, TechnicalResource)
     */
    @Override
    public void assignSkillToTechnicalResource(List<String> skills, Organization organization, TechnicalResource technicalResource) {
        final String nonExistentSkillMsg = "One or more skills are non existent.";
        final String alreadyAssignedSkillMsg = "One or more skills are already assigned to "+ technicalResource.getFirstName() + " " +
             technicalResource.getLastName() + ".";
        final String emptySkillMsg = "One or more skills are empty.";

        List<OrganizationSkill> skillsToAssign = new ArrayList<>();

        Set<OrganizationSkill> technicalResourceSkills = technicalResource.getSkills();
        Set<OrganizationSkillCategory> organizationSkillCategories = organization.getSkillCategories();

        OrganizationSkillCategory organizationSkillCategory;
        Set<OrganizationSkill> organizationSkills;
        boolean keepSearching;
        OrganizationSkill skill = null;

        for (String skillName : skills) {

            keepSearching = true;

            if (StringUtils.isEmpty(skillName))
                throw new EmptySkillException(emptySkillMsg);

            Iterator<OrganizationSkillCategory> iterator = organizationSkillCategories.iterator();

            while(iterator.hasNext() && keepSearching) {

                organizationSkillCategory = iterator.next();
                organizationSkills = organizationSkillCategory.getOrganizationSkills();

                Iterator<OrganizationSkill> iterator1 = organizationSkills.iterator();

                while(iterator1.hasNext() && keepSearching) {

                    skill = iterator1.next();

                    if(skill.getName().equals(skillName)){
                        keepSearching = false;
                    }

                }

            }

            if (keepSearching) {

                throw new NonExistentSkillException(nonExistentSkillMsg);

            } else {

                if(technicalResourceSkills.contains(skill))
                    throw new AlreadyAssignedSkillException(alreadyAssignedSkillMsg);

                skillsToAssign.add(skill);
            }

        }

        for(OrganizationSkill organizationSkill: skillsToAssign){
            organizationSkill.getResources().add(technicalResource);
            this.organizationSkillDao.update(organizationSkill);
        }

    }

}
