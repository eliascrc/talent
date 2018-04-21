package cr.talent.model.test;

import cr.talent.model.*;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

public class ProjectTest {
    private static final String id = "1234";
    private static final Date entityCreationTimestamp = new Date();
    private static final Date lastUpdatedTimestamp = new Date();
    private static final long entityVersion = 1l;
    private static final String name = "talent";
    private static final String name2 = "talent2";
    private static final Date startdate = new Date();
    private static final Date endDate = new Date();

    @Test
    public void coreTest() {

        Organization organization = mock(Organization.class);

        // Verify Constructor
        Project project = new Project();

        // Verify the sets
        project.setId(id);
        project.setEntityCreationTimestamp(entityCreationTimestamp);
        project.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        project.setEntityVersion(entityVersion);
        project.setName(name);
        project.setStartDate(startdate);
        project.setEndDate(endDate);
        project.setProjectCapabilities(new HashSet<ProjectCapability>());
        project.setProjectManagerHistory(new HashSet<ProjectManagerPosition>());
        project.setState(ProjectState.UPCOMING);
        project.setOrganization(organization);
        project.setObservations(new HashSet<Observation>());

        // Verify the gets
        assertEquals(id, project.getId());
        assertEquals(entityCreationTimestamp, project.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, project.getLastUpdatedTimestamp());
        assertEquals(entityVersion, project.getEntityVersion());
        assertEquals(name, project.getName());
        assertEquals(startdate, project.getStartDate());
        assertEquals(endDate, project.getEndDate());
        assertNotNull(project.getProjectCapabilities());
        assertNotNull(project.getProjectManagerHistory());
        assertEquals(ProjectState.UPCOMING, project.getState());
        assertEquals(organization, project.getOrganization());
        assertNotNull(project.getObservations());
    }

    @Test
    public void testEqualForPersistentProject() {
        Project project1 = new Project();
        project1.setId(id);

        Project project2 = new Project();
        project2.setId(id);

        assertTrue(project1.equals(project2));
    }

    @Test
    public void testEqualForNonPersistentNotification() {
        Project project1 = new Project();
        project1.setName(name);

        Project project2 = new Project();
        project1.setName(name);

        assertFalse(project1.equals(project2));
    }


    @Test
    public void testNonEqualForNonPersistentNotification() {
        Project project1 = new Project();
        project1.setName(name);

        Project project2 = new Project();
        project1.setName(name2);

        assertFalse(project1.equals(project2));
    }

    @Test
    public void testHashCodeForPersistentProject() {
        Project project1 = new Project();
        project1.setId(id);

        Project project2 = new Project();
        project2.setId(id);

        assertTrue(project1.hashCode() == project2.hashCode());
    }


}
