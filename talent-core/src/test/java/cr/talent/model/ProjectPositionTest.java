package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the ProjectPosition methods to know all the different paths they could take.
 * @author Daniel Montes de Oca
 */
public class ProjectPositionTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final OrganizationCapabilityLevel ORGANIZATION_CAPABILITY_LEVEL = mock(OrganizationCapabilityLevel.class);
    private static final OrganizationCapabilityLevel ORGANIZATION_CAPABILITY_LEVEL2 = mock(OrganizationCapabilityLevel.class);

    @Test
    public void coreTest() {

        // Inherited from BasicEntity
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Inherited from Position
        OrganizationCapabilityLevel level = ORGANIZATION_CAPABILITY_LEVEL;
        TechnicalResource resource = mock(TechnicalResource.class);

        // From ProjectPosition
        Date startDate = new Date();
        Date endDate = new Date();
        boolean reviewed = true;
        ProjectCapability projectCapability = new ProjectCapability();

        // Verify the Constructor
        ProjectPosition projectPosition = new ProjectPosition();

        // Verify the setters
        // Inheritted from BasicEntity
        projectPosition.setId(ID);
        projectPosition.setEntityCreationTimestamp(entityCreationTimestamp);
        projectPosition.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        projectPosition.setEntityVersion(entityVersion);

        // Inherited from Position
        projectPosition.setOrganizationCapabilityLevel(level);
        projectPosition.setTechnicalResource(resource);

        // From Project Position
        projectPosition.setStartDate(startDate);
        projectPosition.setEndDate(endDate);
        projectPosition.setReviewed(reviewed);
        projectPosition.setProjectCapability(projectCapability);

        // Verify the gets
        assertEquals(ID, projectPosition.getId());
        assertEquals(entityCreationTimestamp, projectPosition.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, projectPosition.getLastUpdatedTimestamp());
        assertEquals(entityVersion, projectPosition.getEntityVersion());
        assertEquals(startDate, projectPosition.getStartDate());
        assertEquals(endDate, projectPosition.getEndDate());
        assertEquals(reviewed, projectPosition.isReviewed());
        assertEquals(ORGANIZATION_CAPABILITY_LEVEL, projectPosition.getOrganizationCapabilityLevel());
        assertEquals(resource, projectPosition.getTechnicalResource());
        assertEquals(projectCapability, projectPosition.getProjectCapability());
    }

    @Test
    public void testEqualForSameObject() {
        ProjectPosition projectPosition1 = new ProjectPosition();

        assertTrue(projectPosition1.equals(projectPosition1));
    }

    @Test
    public void testEqualForDifferentClass() {
        ProjectPosition projectPosition1 = new ProjectPosition();

        assertFalse(projectPosition1.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentProjectPosition() {
        ProjectPosition projectPosition1 = new ProjectPosition();
        projectPosition1.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID);

        assertTrue(projectPosition1.equals(projectPosition2));
    }

    @Test
    public void testNonEqualForPersistentProjectPosition() {
        ProjectPosition projectPosition1 = new ProjectPosition();
        projectPosition1.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID2);

        assertFalse(projectPosition1.equals(projectPosition2));
    }

    @Test
    public void testEqualForNonPersistentProjectPosition() {
        ProjectPosition projectPosition1 = new ProjectPosition();
        projectPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        assertTrue(projectPosition1.equals(projectPosition2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectPosition() {
        ProjectPosition projectPosition1 = new ProjectPosition();
        projectPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL2);

        assertFalse(projectPosition1.equals(projectPosition2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentProjectPosition() {
        ProjectPosition projectPosition1 = new ProjectPosition();
        projectPosition1.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID);

        assertTrue(projectPosition1.hashCode() == projectPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentProjectPosition() {
        ProjectPosition projectPosition1 = new ProjectPosition();
        projectPosition1.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID2);

        assertFalse(projectPosition1.hashCode() == projectPosition2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentProjectPosition() {
        ProjectPosition projectPosition1 = new ProjectPosition();
        projectPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        assertTrue(projectPosition1.hashCode() == projectPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentProjectPosition() {
        ProjectPosition projectPosition1 = new ProjectPosition();
        projectPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL2);

        assertFalse(projectPosition1.hashCode() == projectPosition2.hashCode());
    }

}
