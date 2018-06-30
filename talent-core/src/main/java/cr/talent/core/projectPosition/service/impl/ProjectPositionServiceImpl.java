package cr.talent.core.projectPosition.service.impl;

import cr.talent.core.projectPosition.dao.ProjectPositionDao;
import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.model.ProjectPosition;
import cr.talent.model.*;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.support.service.impl.CrudServiceImpl;
import cr.talent.support.exceptions.NotProjectLeadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * Default implementation of the {@link cr.talent.core.projectPosition.service.ProjectPositionService}.
 *
 * @author Elías Calderón, Otto Mena
 */
@Service("projectPositionService")
@Transactional
public class ProjectPositionServiceImpl extends CrudServiceImpl<ProjectPosition, String> implements ProjectPositionService {

    @Autowired
    private ProjectPositionDao projectPositionDao;

    public void init() {
        setCrudDao(this.projectPositionDao);
    }

    /**
     *@see cr.talent.core.projectPosition.service.ProjectPositionService#deleteProjectPosition(TechnicalResource, ProjectPosition)-
     */
    public void deleteProjectPosition(TechnicalResource deleter, ProjectPosition projectPosition){
        TechnicalResource projectLead = null;

        for (LeadPosition leadPosition : projectPosition.getProject().getLeadHistory()) { //finds the project lead
            if (leadPosition.getActive())
                projectLead = leadPosition.getLead();
        }

        if (projectLead == null)
            throw new ProjectWithoutLeadException();

        if (!deleter.equals(projectLead))
            throw new NotProjectLeadException();

        super.remove(projectPosition);
    }
}
