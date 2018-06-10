package cr.talent.model;


import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the Project Position methods to know all the different paths they could take.
 * This class contains the test of the inherited methods ans attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */

public class ProjectPositionTest {

    private static final Project PROJECT = mock(Project.class);
    private static final Project PROJECT2 = mock(Project.class);
    private static final CapabilityLevel CAPABILITY = mock(CapabilityLevel.class);
    private static final CapabilityLevel CAPABILITY2 = mock(CapabilityLevel.class);
    private static final String ID = "987";
    private static final String ID2 = "654";

    @Test
    public void coreTest() {
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        ProjectPositionStatus projectPositionStatus =  ProjectPositionStatus.AVAILABLE;
        int totalHours = 5;

        // Test Constructor
        ProjectPosition projectPosition = new ProjectPosition();

        projectPosition.setEntityVersion(entityVersion);
        projectPosition.setEntityCreationTimestamp(entityCreationTimestamp);
        projectPosition.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        projectPosition.setId(ID);
        projectPosition.setProjectPositionStatus(projectPositionStatus);
        projectPosition.setTotalHours(totalHours);
        projectPosition.setHolderHistory(new HashSet<>());
        projectPosition.setProject(PROJECT);
        projectPosition.setCapability(CAPABILITY);

        assertEquals(entityCreationTimestamp, projectPosition.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, projectPosition.getLastUpdatedTimestamp());
        assertEquals(entityVersion, projectPosition.getEntityVersion());
        assertEquals(ID, projectPosition.getId());
        assertEquals(projectPositionStatus, projectPosition.getProjectPositionStatus());
        assertEquals(totalHours, projectPosition.getTotalHours());
        assertNotNull(projectPosition.getHolderHistory());
        assertEquals(PROJECT, projectPosition.getProject());
        assertEquals(CAPABILITY, projectPosition.getCapability());
    }


    @Test
    public void testEqualForSameObject() {
        ProjectPosition projectPosition = new ProjectPosition();

        assertTrue(projectPosition.equals(projectPosition));
    }

    @Test
    public void testEqualForDifferentClass() {
        ProjectPosition projectPosition = new ProjectPosition();

        assertFalse(projectPosition.equals(new Image()));
    }

    @Test
    public void testEqualForPersistentProjectPosition() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID);

        assertTrue(projectPosition.equals(projectPosition2));
    }

    @Test
    public void testNonEqualForPersistentProjectPosition() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID2);

        assertFalse(projectPosition.equals(projectPosition2));
    }

    @Test
    public void testEqualForNonPersistentProjectPosition() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setProject(PROJECT);
        projectPosition.setCapability(CAPABILITY);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setProject(PROJECT);
        projectPosition2.setCapability(CAPABILITY);

        assertTrue(projectPosition.equals(projectPosition2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectPosition() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setProject(PROJECT);
        projectPosition.setCapability(CAPABILITY);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setProject(PROJECT2);
        projectPosition2.setCapability(CAPABILITY2);

        assertFalse(projectPosition.equals(projectPosition2));
    }

    @Test
    public void testEqualForNonPersistentProjectPositionNullValues() {
        ProjectPosition projectPosition = new ProjectPosition();

        ProjectPosition projectPosition2 = new ProjectPosition();

        assertTrue(projectPosition.equals(projectPosition2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectPositionNullProject() {
        ProjectPosition projectPosition = new ProjectPosition();

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setProject(PROJECT2);

        assertFalse(projectPosition.equals(projectPosition2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectPositionNullCapability() {
        ProjectPosition projectPosition = new ProjectPosition();

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setCapability(CAPABILITY2);

        assertFalse(projectPosition.equals(projectPosition2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectPositionNullOrganizationFirstProjectPosition() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setCapability(CAPABILITY);

        ProjectPosition projectPosition2 = new ProjectPosition();

        assertFalse(projectPosition.equals(projectPosition2));
    }

    @Test
    public void testEqualHashCodeForPersistentProjectPosition() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID);

        assertTrue(projectPosition.hashCode() == projectPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentProjectPosition() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID2);

        assertFalse(projectPosition.hashCode() == projectPosition2.hashCode());
    }



    @Test
    public void testEqualHashCodeForNonPersistentProjectPosition() {

        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setProject(PROJECT);
        projectPosition.setCapability(CAPABILITY);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setProject(PROJECT);
        projectPosition2.setCapability(CAPABILITY);

        assertTrue(projectPosition.hashCode() == projectPosition2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentProjectPosition() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setProject(PROJECT);
        projectPosition.setCapability(CAPABILITY);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setProject(PROJECT2);
        projectPosition2.setCapability(CAPABILITY2);

        assertFalse(projectPosition.hashCode() == projectPosition2.hashCode());
    }

}
