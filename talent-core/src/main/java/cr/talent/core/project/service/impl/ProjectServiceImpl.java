package cr.talent.core.project.service.impl;

import cr.talent.core.project.dao.ProjectDao;
import cr.talent.core.project.service.ProjectService;
import cr.talent.model.Project;
import cr.talent.model.ProjectEvent;
import cr.talent.model.TechnicalResource;
import cr.talent.support.service.impl.CrudServiceImpl;
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
 * @author Elías Calderón
 */
@Service("projectService")
@Transactional
public class ProjectServiceImpl extends CrudServiceImpl<Project, String> implements ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public void init() {
        setCrudDao(this.projectDao);
    }

    public void changeProjectState(Project project, String status, TechnicalResource lead){

        project.getcurrentState().setEndDate(new Date());
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setStartDate(new Date());
        Set<ProjectEvent> projectTimeline = project.getTimeline();
        projectTimeline.add(projectEvent);
        project.setTimeline(projectTimeline);
        project.setcurrentState(projectEvent);

        super.update(project);
    }

}
