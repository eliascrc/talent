package cr.talent.core.project.service.impl;

import cr.talent.core.project.dao.ProjectDao;
import cr.talent.core.project.service.ProjectService;
import cr.talent.model.*;
import cr.talent.support.exceptions.NotProjectLeadException;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.support.service.impl.CrudServiceImpl;
import cr.talent.core.projectEvent.service.ProjectEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Default implementation of the {@link cr.talent.core.project.service.ProjectService}.
 *
 * @author Elías Calderón, Otto Mena
 */
@Service("projectService")
@Transactional
public class ProjectServiceImpl extends CrudServiceImpl<Project, String> implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    @Autowired
    private ProjectEventService projectEventService;

    public void init() {
        setCrudDao(this.projectDao);
    }

    /**
     * @see cr.talent.core.project.service.impl.ProjectServiceImpl#changeProjectState(Project, String, TechnicalResource)-
     */
    public void changeProjectState(Project project, String status, TechnicalResource lead){

        TechnicalResource projectLead = null;

        for (LeadPosition leadPosition : project.getLeadHistory()) { //finds the project lead
            if (leadPosition.getActive())
                projectLead = leadPosition.getLead();
        }

        if (projectLead == null)
            throw new ProjectWithoutLeadException();

        if (!lead.equals(projectLead))
            throw new NotProjectLeadException();

        project.getcurrentState().setEndDate(new Date());
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setStartDate(new Date());
        projectEvent.setEventType(ProjectEventType.valueOf(status));
        projectEvent.setProject(project);
        Set<ProjectEvent> projectTimeline = project.getTimeline();
        projectTimeline.add(projectEvent);
        project.setTimeline(projectTimeline);
        project.setcurrentState(projectEvent);

        projectEventService.create(projectEvent);
        super.update(project);
    }

}
