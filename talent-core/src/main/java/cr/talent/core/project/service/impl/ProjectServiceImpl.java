package cr.talent.core.project.service.impl;

import cr.talent.core.leadPosition.dao.LeadPositionDao;
import cr.talent.core.project.dao.ProjectDao;
import cr.talent.core.project.service.ProjectService;
import cr.talent.core.projectEvent.dao.ProjectEventDao;
import cr.talent.model.*;
import cr.talent.support.exceptions.NotProjectLeadException;
import cr.talent.support.exceptions.ProjectWithoutLeadException;
import cr.talent.core.security.technicalResource.service.TechnicalResourceService;

import cr.talent.support.exceptions.NoActiveProjectException;
import cr.talent.support.exceptions.StartDateBeforeEndDateException;
import cr.talent.support.service.impl.CrudServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;


import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
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
    private ProjectEventDao projectEventDao;

    @Autowired
    private LeadPositionDao leadPositionDao;

    @Autowired
    private TechnicalResourceService technicalResourceService;


    public void init() {
        setCrudDao(this.projectDao);
    }

    /**
     * @see cr.talent.core.project.service.impl.ProjectServiceImpl#changeProjectState(Project, String, TechnicalResource, Date)-
     */
    @Override
    public void changeProjectState(Project project, String status, TechnicalResource lead, Date newStateDate) {

        TechnicalResource projectLead = null;

        for (LeadPosition leadPosition : project.getLeadHistory()) { //finds the project lead
            if (leadPosition.getActive())
                projectLead = leadPosition.getLead();
        }

        if (projectLead == null)
            throw new ProjectWithoutLeadException();

        if (!lead.equals(projectLead))
            throw new NotProjectLeadException();

        if(newStateDate.before(project.getcurrentState().getStartDate())){
            throw new StartDateBeforeEndDateException();
        }
        project.getcurrentState().setEndDate(newStateDate);
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setStartDate(newStateDate);
        projectEvent.setEventType(ProjectEventType.valueOf(status));
        projectEvent.setProject(project);
        Set<ProjectEvent> projectTimeline = project.getTimeline();
        projectTimeline.add(projectEvent);
        project.setTimeline(projectTimeline);
        project.setcurrentState(projectEvent);

        this.projectEventDao.create(projectEvent);
        projectDao.update(project);
    }

    @Override
    public Project createProject(String name, Date startDate, String projectLead, String description, TechnicalResource technicalResource) {

        Project project = new Project();
        project.setName(name);
        project.setOrganization(technicalResource.getOrganization());
        project.setStartDate(startDate);
        project.setDescription(description);
        projectDao.create(project);

        LeadPosition leadPosition = new LeadPosition();
        leadPosition.setStartDate(startDate);
        leadPosition.setActive(true);
        leadPosition.setProject(project);

        //for the moment, if no project lead was passed, the logged in user will be the leader.
        TechnicalResource projectLeader = this.technicalResourceService.getTechnicalResourceByUsernameAndOrganizationIdentifier(
                projectLead, technicalResource.getOrganization().getUniqueIdentifier());

        if (projectLeader == null) {
            leadPosition.setLead(technicalResource);
        } else {
            leadPosition.setLead(projectLeader);
        }

        this.leadPositionDao.create(leadPosition);

        Set<LeadPosition> leadPositions = new HashSet<>();
        leadPositions.add(leadPosition);

        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setStartDate(startDate);
        projectEvent.setEventType(ProjectEventType.ON_HOLD);
        projectEvent.setProject(project);
        this.projectEventDao.create(projectEvent);

        project.setLeadHistory(leadPositions);
        project.setcurrentState(projectEvent);
        projectDao.update(project);

        return project;
    }

    /**
     * @see cr.talent.core.project.service.ProjectService#getActiveProjects(Organization)
     */
    @Override
    public Set<Project> getActiveProjects(Organization organization) {
        final String noActiveProjectMsg = "The organization does not have any active projects";

        Set<Project> allProjects = organization.getProjects();
        Set<Project> activeProjects = new HashSet<>();

        //iterate project list
        Iterator<Project> projectIterator = allProjects.iterator();
        Project project;
        while(projectIterator.hasNext()){ // iterate projectPositionHolders for active ones
            project = projectIterator.next();
            if(project.getState().equals(ProjectEventType.START.toString())){
                // this to string is because its reusing the getState method from project, necessary for JSON serializer.
                activeProjects.add(project);
            }
        }

        if (activeProjects.isEmpty()) // if set is empty, throw exception that will make a 204 response on the ws.
            throw new NoActiveProjectException(noActiveProjectMsg);

        return activeProjects;
    }
}

