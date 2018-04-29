package cr.talent.model;

import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the Project Capability methods to know all the different paths they could take.
 * This class contains the test of the inherited methods ans attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
public class ProjectCapabilityTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final Project PROJECT = mock(Project.class);
    private static final Project PROJECT2 = mock(Project.class);
    private static final OrganizationCapabilityLevel CAPABILITY = mock(OrganizationCapabilityLevel.class);
    private static final OrganizationCapabilityLevel CAPABILITY2 = mock(OrganizationCapabilityLevel.class);

    @Test
    public void coreTest () {

        // Verify the constructor
        ProjectCapability projectCapability = new ProjectCapability();
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        ProjectCapabilityStatus projectCapabilityStatus = ProjectCapabilityStatus.TAKEN;
        Set<ProjectPosition> projectPositions = new HashSet<>();

        // Verify the sets
        projectCapability.setId(ID);
        projectCapability.setEntityCreationTimestamp(entityCreationTimestamp);
        projectCapability.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        projectCapability.setEntityVersion(entityVersion);
        projectCapability.setStatus(projectCapabilityStatus);
        projectCapability.setProjectPositionHistory(projectPositions);
        projectCapability.setProject(PROJECT);
        projectCapability.setCapability(CAPABILITY);

        // Verify the gets
        assertEquals(ID, projectCapability.getId());
        assertEquals(entityCreationTimestamp, projectCapability.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, projectCapability.getLastUpdatedTimestamp());
        assertEquals(entityVersion, projectCapability.getEntityVersion());
        assertEquals(projectCapabilityStatus, projectCapability.getStatus());
        assertEquals(projectPositions, projectCapability.getProjectPositionHistory());
        assertEquals(PROJECT, projectCapability.getProject());
        assertEquals(CAPABILITY, projectCapability.getCapability());

    }

    @Test
    public void testEqualForSameObject() {
        ProjectCapability projectCapability = new ProjectCapability();

        assertTrue(projectCapability.equals(projectCapability));
    }

    @Test
    public void testEqualForDifferentClass() {
        ProjectCapability projectCapability = new ProjectCapability();

        Kudo kudo = new Kudo();

        assertFalse(projectCapability.equals(kudo));
    }

    @Test
    public void testEqualForPersistentProjectCapability() {
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setId(ID);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setId(ID);

        assertTrue(projectCapability1.equals(projectCapability2));
    }

    @Test
    public void testNonEqualForPersistentProjectCapability() {
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setId(ID);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setId(ID2);

        assertFalse(projectCapability1.equals(projectCapability2));
    }

    @Test
    public void testEqualForNonPersistentProjectCapability() {
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setProject(PROJECT);
        projectCapability1.setCapability(CAPABILITY);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setProject(PROJECT);
        projectCapability2.setCapability(CAPABILITY);

        assertTrue(projectCapability1.equals(projectCapability2));
    }

    @Test
    public void testEqualForNonPersistentProjectCapabilityNullProjectNullCapability(){
        ProjectCapability projectCapability1 = new ProjectCapability();

        ProjectCapability projectCapability2 = new ProjectCapability();

        assertTrue(projectCapability1.equals(projectCapability2));
    }

    @Test
    public void testEqualForNonPersistentProjectCapabilityNullProject() {
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setCapability(CAPABILITY);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setCapability(CAPABILITY);

        assertTrue(projectCapability1.equals(projectCapability2));
    }

    @Test
    public void testEqualForNonPersistentProjectCapabilityNullCapability() {
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setProject(PROJECT);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setProject(PROJECT);

        assertTrue(projectCapability1.equals(projectCapability2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectCapability() {
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setProject(PROJECT);
        projectCapability1.setCapability(CAPABILITY);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setProject(PROJECT2);
        projectCapability2.setCapability(CAPABILITY2);

        assertFalse(projectCapability1.equals(projectCapability2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectCapabilityNullProjectNullCapability(){
        ProjectCapability projectCapability1 = new ProjectCapability();

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setProject(PROJECT2);
        projectCapability2.setCapability(CAPABILITY2);

        assertFalse(projectCapability1.equals(projectCapability2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectCapabilityNullProject(){
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setCapability(CAPABILITY);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setProject(PROJECT2);
        projectCapability2.setCapability(CAPABILITY2);

        assertFalse(projectCapability1.equals(projectCapability2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectCapabilityNullCapability() {
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setProject(PROJECT);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setProject(PROJECT);
        projectCapability2.setCapability(CAPABILITY2);

        assertFalse(projectCapability1.equals(projectCapability2));
    }

    @Test
    public void testEqualHashCodeForPersistentProjectCapability(){
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setId(ID);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setId(ID);

        assertTrue(projectCapability1.hashCode() == projectCapability2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentProjectCapability() {
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setId(ID);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setId(ID2);

        assertFalse(projectCapability1.hashCode() == projectCapability2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentProjectCapability() {
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setProject(PROJECT);
        projectCapability1.setCapability(CAPABILITY);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setProject(PROJECT);
        projectCapability2.setCapability(CAPABILITY);

        assertTrue(projectCapability1.hashCode() == projectCapability2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentProjectCapability() {
        ProjectCapability projectCapability1 = new ProjectCapability();
        projectCapability1.setProject(PROJECT);
        projectCapability1.setCapability(CAPABILITY);

        ProjectCapability projectCapability2 = new ProjectCapability();
        projectCapability2.setProject(PROJECT2);
        projectCapability2.setCapability(CAPABILITY2);

        assertFalse(projectCapability1.hashCode() == projectCapability2.hashCode());
    }

}
