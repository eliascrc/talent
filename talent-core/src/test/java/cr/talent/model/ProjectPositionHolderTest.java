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
        Boolean reviewed = false;
        int assignedHours = 2;
        Boolean active = true;

        // Test Constructor
        ProjectPositionHolder projectPositionHolder = new ProjectPositionHolder();

        projectPositionHolder.setEntityVersion(entityVersion);
        projectPositionHolder.setEntityCreationTimestamp(entityCreationTimestamp);
        projectPositionHolder.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        projectPositionHolder.setId(ID);
        projectPositionHolder.setEndDate(endDate);
        projectPositionHolder.setReviewed(reviewed);
        projectPositionHolder.setAssignedHours(assignedHours);
        projectPositionHolder.setActive(active);
        projectPositionHolder.setProjectPosition(PROJECT_POSITION);
        projectPositionHolder.setResource(TECHNICAL_RESOURCE);
        projectPositionHolder.setStartDate(START_DATE);

        assertEquals(entityCreationTimestamp, projectPositionHolder.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, projectPositionHolder.getLastUpdatedTimestamp());
        assertEquals(entityVersion, projectPositionHolder.getEntityVersion());
        assertEquals(ID, projectPositionHolder.getId());
        assertEquals(projectPositionStatus, projectPositionHolder.getProjectPositionStatus());
        assertEquals(totalHours, projectPositionHolder.getTotalHours());
        assertNotNull(projectPositionHolder.getHolderHistory());
        assertEquals(PROJECT_POSITION, projectPositionHolder.getProject());
        assertEquals(TECHNICAL_RESOURCE, projectPositionHolder.getCapability());
    }


    @Test
    public void testEqualForSameObject() {
        ProjectPosition projectPosition = new ProjectPosition();

        assertTrue(projectPosition.equals(projectPosition));
    }

    @Test
    public void testEqualForDifferentClass() {
        ProjectPosition projectPosition = new ProjectPosition();

        assertFalse(projectPosition.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentSkillToApprove() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID);

        assertTrue(projectPosition.equals(projectPosition2));
    }

    @Test
    public void testNonEqualForPersistentSkillToApprove() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID2);

        assertFalse(projectPosition.equals(projectPosition2));
    }

    @Test
    public void testEqualForNonPersistentSkillToApprove() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setProject(PROJECT_POSITION);
        projectPosition.setCapability(TECHNICAL_RESOURCE);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setProject(PROJECT_POSITION);
        projectPosition2.setCapability(TECHNICAL_RESOURCE);

        assertTrue(projectPosition.equals(projectPosition2));
    }

    @Test
    public void testNonEqualForNonPersistentSkillToApprove() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setProject(PROJECT_POSITION);
        projectPosition.setCapability(TECHNICAL_RESOURCE);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setProject(PROJECT_POSITION2);
        projectPosition2.setCapability(TECHNICAL_RESOURCE2);

        assertFalse(projectPosition.equals(projectPosition2));
    }



    @Test
    public void testEqualHashCodeForPersistentSkillToApprove() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID);

        assertTrue(projectPosition.hashCode() == projectPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentSkillToApprove() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setId(ID);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setId(ID2);

        assertFalse(projectPosition.hashCode() == projectPosition2.hashCode());
    }



    @Test
    public void testEqualHashCodeForNonPersistentSkillToApprove() {

        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setProject(PROJECT_POSITION);
        projectPosition.setCapability(TECHNICAL_RESOURCE);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setProject(PROJECT_POSITION);
        projectPosition2.setCapability(TECHNICAL_RESOURCE);

        assertTrue(projectPosition.hashCode() == projectPosition2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentSkillToApprove() {
        ProjectPosition projectPosition = new ProjectPosition();
        projectPosition.setProject(PROJECT_POSITION);
        projectPosition.setCapability(TECHNICAL_RESOURCE);

        ProjectPosition projectPosition2 = new ProjectPosition();
        projectPosition2.setProject(PROJECT_POSITION2);
        projectPosition2.setCapability(TECHNICAL_RESOURCE2);

        assertFalse(projectPosition.hashCode() == projectPosition2.hashCode());
    }

}
