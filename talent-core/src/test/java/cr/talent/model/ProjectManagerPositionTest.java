package cr.talent.model;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

/**
 * Class that allows to test the projectManagerPosition methods to know all the different paths they could take.
 *  This class contains the test of the inherited methods ans attributes
 *  from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Josue Cubero
 */
public class ProjectManagerPositionTest{

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final Project PROJECT = mock(Project.class);
    private static final ProjectManager PROJECT_MANAGER = mock(ProjectManager.class);
    private static final Project PROJECT2 = mock(Project.class);
    private static final ProjectManager PROJECT_MANAGER2 = mock(ProjectManager.class);

    @Test
    public void coreTest() {

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        Date startDate = new Date();
        Date endDate = new Date();

        // Verify Constructor
        ProjectManagerPosition projectManagerPosition = new ProjectManagerPosition();

        // Verify the sets
        projectManagerPosition.setId(ID);
        projectManagerPosition.setEntityCreationTimestamp(entityCreationTimestamp);
        projectManagerPosition.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        projectManagerPosition.setEntityVersion(entityVersion);
        projectManagerPosition.setEndDate(endDate);
        projectManagerPosition.setStartDate(startDate);
        projectManagerPosition.setProject(PROJECT);
        projectManagerPosition.setProjectManager(PROJECT_MANAGER);

        // Verify the gets
        assertEquals(ID, projectManagerPosition.getId());
        assertEquals(entityCreationTimestamp, projectManagerPosition.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, projectManagerPosition.getLastUpdatedTimestamp());
        assertEquals(entityVersion, projectManagerPosition.getEntityVersion());
        assertEquals(endDate, projectManagerPosition.getEndDate());
        assertEquals(startDate, projectManagerPosition.getStartDate());
        assertEquals(PROJECT, projectManagerPosition.getProject());
        assertEquals(PROJECT_MANAGER, projectManagerPosition.getProjectManager());
    }

    //ON EQUALS TESTS.

    @Test
    public void testEqualForSameObject() {
        ProjectManagerPosition projectManagerPosition1 = new ProjectManagerPosition();

        assertTrue(projectManagerPosition1.equals(projectManagerPosition1));
    }

    @Test
    public void testEqualForDifferentClass() {
        ProjectManagerPosition projectManagerPosition1 = new ProjectManagerPosition();

        assertFalse(projectManagerPosition1.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentProjectManagerPosition() {
        ProjectManagerPosition projectManagerPosition1 = new ProjectManagerPosition();
        projectManagerPosition1.setId(ID);

        ProjectManagerPosition projectManagerPosition2 = new ProjectManagerPosition();
        projectManagerPosition2.setId(ID);

        assertTrue(projectManagerPosition1.equals(projectManagerPosition2));
    }

    @Test
    public void testNonEqualForPersistentProjectManagerPosition() {
        ProjectManagerPosition projectManagerPosition1 = new ProjectManagerPosition();
        projectManagerPosition1.setId(ID);

        ProjectManagerPosition projectManagerPosition2 = new ProjectManagerPosition();
        projectManagerPosition2.setId(ID2);

        assertFalse(projectManagerPosition1.equals(projectManagerPosition2));
    }

    @Test
    public void testEqualForNonPersistentProjectManagerPosition() {
        ProjectManagerPosition projectManagerPosition1 = new ProjectManagerPosition();
        projectManagerPosition1.setProject(PROJECT);
        projectManagerPosition1.setProjectManager(PROJECT_MANAGER);

        ProjectManagerPosition projectManagerPosition2 = new ProjectManagerPosition();
        projectManagerPosition2.setProject(PROJECT);
        projectManagerPosition2.setProjectManager(PROJECT_MANAGER);

        assertTrue(projectManagerPosition1.equals(projectManagerPosition2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectManagerPosition() {
        ProjectManagerPosition projectManagerPosition1 = new ProjectManagerPosition();
        projectManagerPosition1.setProject(PROJECT);
        projectManagerPosition1.setProjectManager(PROJECT_MANAGER);

        ProjectManagerPosition projectManagerPosition2 = new ProjectManagerPosition();
        projectManagerPosition2.setProject(PROJECT2);
        projectManagerPosition2.setProjectManager(PROJECT_MANAGER2);

        assertFalse(projectManagerPosition1.equals(projectManagerPosition2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentProjectManagerPosition() {
        ProjectManagerPosition projectManagerPosition1 = new ProjectManagerPosition();
        projectManagerPosition1.setId(ID);

        ProjectManagerPosition projectManagerPosition2 = new ProjectManagerPosition();
        projectManagerPosition2.setId(ID);

        assertTrue(projectManagerPosition1.hashCode() == projectManagerPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentProjectManagerPosition() {
        ProjectManagerPosition projectManagerPosition1 = new ProjectManagerPosition();
        projectManagerPosition1.setId(ID);

        ProjectManagerPosition projectManagerPosition2 = new ProjectManagerPosition();
        projectManagerPosition2.setId(ID2);

        assertFalse(projectManagerPosition1.hashCode() == projectManagerPosition2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentProjectManagerPosition() {
        ProjectManagerPosition projectManagerPosition1 = new ProjectManagerPosition();
        projectManagerPosition1.setProject(PROJECT);
        projectManagerPosition1.setProjectManager(PROJECT_MANAGER);

        ProjectManagerPosition projectManagerPosition2 = new ProjectManagerPosition();
        projectManagerPosition2.setProject(PROJECT);
        projectManagerPosition2.setProjectManager(PROJECT_MANAGER);

        assertTrue(projectManagerPosition1.hashCode() == projectManagerPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentProjectManagerPosition() {
        ProjectManagerPosition projectManagerPosition1 = new ProjectManagerPosition();
        projectManagerPosition1.setProject(PROJECT);
        projectManagerPosition1.setProjectManager(PROJECT_MANAGER);

        ProjectManagerPosition projectManagerPosition2 = new ProjectManagerPosition();
        projectManagerPosition2.setProject(PROJECT2);
        projectManagerPosition2.setProjectManager(PROJECT_MANAGER2);

        assertFalse(projectManagerPosition1.hashCode() == projectManagerPosition2.hashCode());
    }
}
