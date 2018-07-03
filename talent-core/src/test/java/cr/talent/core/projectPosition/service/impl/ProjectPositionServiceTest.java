package cr.talent.core.projectPosition.service.impl;

import cr.talent.core.projectPosition.dao.ProjectPositionDao;
import cr.talent.core.projectPosition.service.ProjectPositionService;
import cr.talent.model.*;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class ProjectPositionServiceTest {
    
    @Test
    public void testCreateProjectPositionCall() {

        ProjectPositionDao projectPositionDao = mock(ProjectPositionDao.class);
        TechnicalResource assigner = mock(TechnicalResource.class);
        Organization organization = mock(Organization.class);
        LeadPosition leadPosition = mock(LeadPosition.class);
        CapabilityLevel capabilityLevel = mock(CapabilityLevel.class);
        Project project = mock(Project.class);
        Set leadHistory = new HashSet();
        leadHistory.add(leadPosition);
        int totalHours = 1;
        boolean active = true;

        when(assigner.getOrganization()).thenReturn(organization);
        when(project.getLeadHistory()).thenReturn(leadHistory);
        when(project.getOrganization()).thenReturn(organization);
        when(capabilityLevel.getOrganization()).thenReturn(organization);
        when(leadPosition.getLead()).thenReturn(assigner);
        when(leadPosition.getActive()).thenReturn(true);


        ProjectPositionService projectPositionService = new ProjectPositionServiceImpl();
        ReflectionTestUtils.setField(projectPositionService, "crudDao", projectPositionDao);
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setTotalHours(totalHours);
        projectPosition.setProject(project);
        projectPosition.setCapabilityLevel(capabilityLevel);
        ReflectionTestUtils.setField(projectPositionService, "crudDao", projectPositionDao);
        projectPositionService.createProjectPosition(assigner, project, capabilityLevel, totalHours);
        verify(projectPositionDao, times(1)).create(projectPosition);
    }
}
