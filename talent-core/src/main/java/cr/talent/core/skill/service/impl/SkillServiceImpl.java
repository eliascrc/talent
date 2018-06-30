package cr.talent.core.skill.service.impl;

import cr.talent.core.skill.dao.SkillDao;
import cr.talent.core.skill.service.SkillService;
import cr.talent.model.*;
import cr.talent.model.Skill;
import cr.talent.support.exceptions.AlreadyAssignedSkillException;
import cr.talent.support.exceptions.AlreadyCreatedPredefinedSkillException;
import cr.talent.support.exceptions.EmptySkillException;
import cr.talent.support.exceptions.NonExistentSkillException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of the {@link cr.talent.core.skill.service.SkillService}.
 *
 * @author Elías Calderón
 */
@Service("skillService")
@Transactional
public class SkillServiceImpl extends CrudServiceImpl<Skill, String> implements SkillService {

    @Autowired
    private SkillDao skillDao;

    public void init() {
        setCrudDao(this.skillDao);
    }

    /**
     * @see cr.talent.core.skill.service.SkillService#createPredefinedSkill(Skill)
     */
    @Override
    public String createPredefinedSkill(Skill predefinedSkill) {

        final String alreadyCreatedPredefinedSkillExceptionMsg = "The predefined skill with name " +
                predefinedSkill.getName() + " has already been created within the system.";

        if (this.skillDao.getPredefinedSkillByName(predefinedSkill.getName()) != null)
            throw new AlreadyCreatedPredefinedSkillException(alreadyCreatedPredefinedSkillExceptionMsg);

        return this.skillDao.create(predefinedSkill);
    }

    /**
     * @see  cr.talent.core.skill.service.SkillService#assignSkillToTechnicalResource(List, Organization, TechnicalResource)
     */
    @Override
    public void assignSkillToTechnicalResource(List<String> skillNames, Organization organization, TechnicalResource technicalResource) {
        final String nonExistentSkillMsg = "One or more skills are non existent.";
        final String alreadyAssignedSkillMsg = "One or more skills are already assigned to "+ technicalResource.getFirstName() + " " +
                technicalResource.getLastName() + ".";
        final String emptySkillMsg = "One or more skills are empty.";

        List<Skill> skillsToAssign = new ArrayList<>();


        Set<Skill> technicalResourceSkills = technicalResource.getSkills();
        Set<SkillCategory> skillCategories = organization.getSkillCategories();

        SkillCategory skillCategory;
        Set<Skill> skills;
        boolean keepSearching;
        Skill skill = null;

        for (String skillName : skillNames) {

            keepSearching = true;

            if (StringUtils.isEmpty(skillName))
                throw new EmptySkillException(emptySkillMsg);

            Iterator<SkillCategory> iterator = skillCategories.iterator();

            while(iterator.hasNext() && keepSearching) {

                skillCategory = iterator.next();
                skills = skillCategory.getSkills();

                Iterator<Skill> iterator1 = skills.iterator();

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


            for(Skill skillIterator: skillsToAssign){
                skillIterator.getResources().add(technicalResource);
                this.skillDao.update(skillIterator);
            }

        }
}
