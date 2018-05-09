package cr.talent.model;


import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the Project Position Holder methods to know all the different paths they could take.
 * This class contains the test of the inherited methods ans attributes
 * from {@link BasicEntity} class.
 *
 * @author Elías Calderón
 */

public class ProjectPositionHolderTest {

    private static final ProjectPosition PROJECT_POSITION = mock(ProjectPosition.class);
    private static final ProjectPosition PROJECT_POSITION2 = mock(ProjectPosition.class);
    private static final TechnicalResource TECHNICAL_RESOURCE = mock(TechnicalResource.class);
    private static final TechnicalResource TECHNICAL_RESOURCE2 = mock(TechnicalResource.class);
    private static final Date START_DATE = new Date();
    private static final Date START_DATE2 = new Date();
    private static final String ID = "987";
    private static final String ID2 = "654";

    @Test
    public void coreTest() {
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        Date endDate = new Date();
        int assignedHours = 2;

        // Test Constructor
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();

        projectPositionHolder.setEntityVersion(entityVersion);
        projectPositionHolder.setEntityCreationTimestamp(entityCreationTimestamp);
        projectPositionHolder.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        projectPositionHolder.setId(ID);
        projectPositionHolder.setEndDate(endDate);
        projectPositionHolder.setReviewed(false);
        projectPositionHolder.setAssignedHours(assignedHours);
        projectPositionHolder.setActive(true);
        projectPositionHolder.setProjectPosition(PROJECT_POSITION);
        projectPositionHolder.setResource(TECHNICAL_RESOURCE);
        projectPositionHolder.setStartDate(START_DATE);

        assertEquals(entityCreationTimestamp, projectPositionHolder.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, projectPositionHolder.getLastUpdatedTimestamp());
        assertEquals(entityVersion, projectPositionHolder.getEntityVersion());
        assertEquals(ID, projectPositionHolder.getId());
        assertEquals(endDate, projectPositionHolder.getEndDate());
        assertFalse(projectPositionHolder.isReviewed());
        assertEquals(assignedHours, projectPositionHolder.getAssignedHours());
        assertTrue(projectPositionHolder.isActive());
        assertEquals(PROJECT_POSITION, projectPositionHolder.getProjectPosition());
        assertEquals(TECHNICAL_RESOURCE, projectPositionHolder.getResource());
        assertEquals(START_DATE, projectPositionHolder.getStartDate());
    }


    @Test
    public void testEqualForSameObject() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();

        assertTrue(projectPositionHolder.equals(projectPositionHolder));
    }

    @Test
    public void testEqualForDifferentClass() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();

        assertFalse(projectPositionHolder.equals(new Image()));
    }

    @Test
    public void testEqualForPersistentProjectPositionHolder() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setId(ID);

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();
        projectPositionHolder2.setId(ID);

        assertTrue(projectPositionHolder.equals(projectPositionHolder2));
    }

    @Test
    public void testNonEqualForPersistentProjectPositionHolder() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setId(ID);

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();
        projectPositionHolder2.setId(ID2);

        assertFalse(projectPositionHolder.equals(projectPositionHolder2));
    }

    @Test
    public void testEqualForNonPersistentProjectPositionHolder() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setProjectPosition(PROJECT_POSITION);
        projectPositionHolder.setResource(TECHNICAL_RESOURCE);
        projectPositionHolder.setStartDate(START_DATE);

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();
        projectPositionHolder2.setProjectPosition(PROJECT_POSITION);
        projectPositionHolder2.setResource(TECHNICAL_RESOURCE);
        projectPositionHolder2.setResource(TECHNICAL_RESOURCE);
        projectPositionHolder2.setStartDate(START_DATE);

        assertTrue(projectPositionHolder.equals(projectPositionHolder2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectPositionHolder() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setProjectPosition(PROJECT_POSITION);
        projectPositionHolder.setResource(TECHNICAL_RESOURCE);
        projectPositionHolder.setStartDate(START_DATE);

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();
        projectPositionHolder2.setProjectPosition(PROJECT_POSITION2);
        projectPositionHolder2.setResource(TECHNICAL_RESOURCE2);
        projectPositionHolder.setStartDate(START_DATE2);

        assertFalse(projectPositionHolder.equals(projectPositionHolder2));
    }

    @Test
    public void testEqualForNonPersistentProjectPositionHolderNullValues() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();

        assertTrue(projectPositionHolder.equals(projectPositionHolder2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectPositionHolderNullProjectPosition() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();
        projectPositionHolder2.setProjectPosition(PROJECT_POSITION2);

        assertFalse(projectPositionHolder.equals(projectPositionHolder2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectPositionHolderNullResourceFirstProjectPositionHolder() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setResource(TECHNICAL_RESOURCE);

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();

        assertFalse(projectPositionHolder.equals(projectPositionHolder2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectPositionHolderNullResource() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();
        projectPositionHolder2.setResource(TECHNICAL_RESOURCE2);

        assertFalse(projectPositionHolder.equals(projectPositionHolder2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectPositionHolderNullStartDate() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();
        projectPositionHolder2.setStartDate(START_DATE2);

        assertFalse(projectPositionHolder.equals(projectPositionHolder2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectPositionHolderNullStartDateFirstProjectPositionHolder() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setStartDate(START_DATE);

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();

        assertFalse(projectPositionHolder.equals(projectPositionHolder2));
    }

    @Test
    public void testEqualHashCodeForPersistentProjectPositionHolder() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setId(ID);

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();
        projectPositionHolder2.setId(ID);

        assertTrue(projectPositionHolder.hashCode() == projectPositionHolder2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentProjectPositionHolder() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setId(ID);

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();
        projectPositionHolder2.setId(ID2);

        assertFalse(projectPositionHolder.hashCode() == projectPositionHolder2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentProjectPositionHolder() {

        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setProjectPosition(PROJECT_POSITION);
        projectPositionHolder.setResource(TECHNICAL_RESOURCE);
        projectPositionHolder.setStartDate(START_DATE);

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();
        projectPositionHolder2.setProjectPosition(PROJECT_POSITION);
        projectPositionHolder2.setResource(TECHNICAL_RESOURCE);
        projectPositionHolder2.setStartDate(START_DATE);

        assertTrue(projectPositionHolder.hashCode() == projectPositionHolder2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentProjectPositionHolder() {
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();
        projectPositionHolder.setProjectPosition(PROJECT_POSITION);
        projectPositionHolder.setResource(TECHNICAL_RESOURCE);
        projectPositionHolder.setStartDate(START_DATE);

        ProjectPositionHolder projectPositionHolder2 = new ProjectPositionHolder();
        projectPositionHolder2.setProjectPosition(PROJECT_POSITION2);
        projectPositionHolder2.setResource(TECHNICAL_RESOURCE2);
        projectPositionHolder2.setStartDate(START_DATE2);

        assertFalse(projectPositionHolder.hashCode() == projectPositionHolder2.hashCode());
    }

}
