package cr.talent.core.projectPositionHolder.service.impl;

import cr.talent.core.projectPositionHolder.dao.ProjectPositionHolderDao;
import cr.talent.core.projectPositionHolder.service.ProjectPositionHolderService;
import cr.talent.model.*;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

/**
 * Class that allows to test the {@link cr.talent.core.projectPositionHolder.service.impl.ProjectPositionHolderServiceImpl} methods
 *
 * @author Daniel Montes de Oca
 */
public class ProjectPositionHolderServiceTest {

    @Test
    public void testAssignProjectPositionCall() {
        ProjectPositionHolderDao projectPositionHolderDao = mock(ProjectPositionHolderDao.class);
        ProjectPosition projectPosition = mock(ProjectPosition.class);
        TechnicalResource assigner = mock(TechnicalResource.class);
        TechnicalResource assignee = mock(TechnicalResource.class);
        Date startDate = mock(Date.class);
        Organization organization = mock(Organization.class);
        LeadPosition leadPosition = mock(LeadPosition.class);
        Project project = mock(Project.class);
        Set leadHistory = new HashSet();
        leadHistory.add(leadPosition);
        int assignedHours = 1;
        boolean active = true;
        when(projectPosition.getProject()).thenReturn(project);
        when(projectPosition.getProject().getOrganization()).thenReturn(organization);
        when(assigner.getOrganization()).thenReturn(organization);
        when(project.getLeadHistory()).thenReturn(leadHistory);
        when(leadPosition.getLead()).thenReturn(assigner);
        when(leadPosition.getActive()).thenReturn(true);

        ProjectPositionHolderService projectPositionHolderService = new ProjectPositionHolderServiceImpl();

        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setAssignedHours(assignedHours);
        projectPositionHolder.setReviewed(false);
        projectPositionHolder.setActive(active);
        projectPositionHolder.setResource(assignee);
        projectPositionHolder.setStartDate(startDate);
        projectPositionHolder.setProjectPosition(projectPosition);

        ReflectionTestUtils.setField(projectPositionHolderService, "crudDao", projectPositionHolderDao);
        projectPositionHolderService.assignProjectPosition(assigner, assignee, projectPosition, startDate, assignedHours, active);
        verify(projectPositionHolderDao, times(1)).create(projectPositionHolder);
    }

}
