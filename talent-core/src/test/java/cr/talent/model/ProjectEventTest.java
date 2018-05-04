package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the Project Event methods to know all the different paths they could take.
 * This class contains the test of the inherited methods ans attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
public class ProjectEventTest {

    private static final Date START_DATE = new Date();
    private static final Date START_DATE2 = new Date();
    private static final ProjectEventType EVENT_TYPE = ProjectEventType.START;
    private static final ProjectEventType EVENT_TYPE2 = ProjectEventType.ON_HOLD;
    private static final Project PROJECT = mock(Project.class);
    private static final Project PROJECT2 = mock(Project.class);
    private static final String ID = "987";
    private static final String ID2 = "654";

    @Test
    public void coreTest() {
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        Date endDate = new Date();

        // Test Constructor
        ProjectEvent projectEvent = new ProjectEvent();

        projectEvent.setEntityVersion(entityVersion);
        projectEvent.setEntityCreationTimestamp(entityCreationTimestamp);
        projectEvent.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        projectEvent.setId(ID);
        projectEvent.setStartDate(START_DATE);
        projectEvent.setEndDate(endDate);
        projectEvent.setEventType(EVENT_TYPE);
        projectEvent.setProject(PROJECT);

        assertEquals(entityCreationTimestamp, projectEvent.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, projectEvent.getLastUpdatedTimestamp());
        assertEquals(entityVersion, projectEvent.getEntityVersion());
        assertEquals(ID, projectEvent.getId());
        assertEquals(START_DATE, projectEvent.getStartDate());
        assertEquals(endDate, projectEvent.getEndDate());
        assertEquals(EVENT_TYPE, projectEvent.getEventType());
        assertEquals(PROJECT, projectEvent.getProject());
    }


    @Test
    public void testEqualForSameObject() {
        ProjectEvent projectEvent = new ProjectEvent();

        assertTrue(projectEvent.equals(projectEvent));
    }

    @Test
    public void testEqualForDifferentClass() {
        ProjectEvent projectEvent = new ProjectEvent();

        assertFalse(projectEvent.equals(new Image()));
    }

    @Test
    public void testEqualForPersistentProjectEvent() {
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setId(ID);

        ProjectEvent projectEvent2 = new ProjectEvent();
        projectEvent2.setId(ID);

        assertTrue(projectEvent.equals(projectEvent2));
    }

    @Test
    public void testNonEqualForPersistentProjectEvent() {
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setId(ID);

        ProjectEvent projectEvent2 = new ProjectEvent();
        projectEvent2.setId(ID2);

        assertFalse(projectEvent.equals(projectEvent2));
    }

    @Test
    public void testEqualForNonPersistentProjectEvent() {
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setStartDate(START_DATE);
        projectEvent.setEventType(EVENT_TYPE);
        projectEvent.setProject(PROJECT);

        ProjectEvent projectEvent2 = new ProjectEvent();
        projectEvent2.setStartDate(START_DATE);
        projectEvent2.setEventType(EVENT_TYPE);
        projectEvent2.setProject(PROJECT);

        assertTrue(projectEvent.equals(projectEvent2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectEvent() {
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setStartDate(START_DATE);
        projectEvent.setEventType(EVENT_TYPE);
        projectEvent.setProject(PROJECT);

        ProjectEvent projectEvent2 = new ProjectEvent();
        projectEvent2.setStartDate(START_DATE2);
        projectEvent2.setEventType(EVENT_TYPE2);
        projectEvent2.setProject(PROJECT2);

        assertFalse(projectEvent.equals(projectEvent2));
    }

    @Test
    public void testEqualForNonPersistentProjectEventNullValues() {
        ProjectEvent projectEvent = new ProjectEvent();

        ProjectEvent projectEvent2 = new ProjectEvent();

        assertTrue(projectEvent.equals(projectEvent2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectEventNullStartDate() {
        ProjectEvent projectEvent = new ProjectEvent();

        ProjectEvent projectEvent2 = new ProjectEvent();
        projectEvent2.setStartDate(START_DATE2);

        assertFalse(projectEvent.equals(projectEvent2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectEventNullStartDateFirstEvent() {
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setStartDate(START_DATE);

        ProjectEvent projectEvent2 = new ProjectEvent();

        assertFalse(projectEvent.equals(projectEvent2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectEventNullEventType() {
        ProjectEvent projectEvent = new ProjectEvent();

        ProjectEvent projectEvent2 = new ProjectEvent();
        projectEvent2.setEventType(EVENT_TYPE2);

        assertFalse(projectEvent.equals(projectEvent2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectEventNullProject() {
        ProjectEvent projectEvent = new ProjectEvent();

        ProjectEvent projectEvent2 = new ProjectEvent();
        projectEvent2.setProject(PROJECT2);

        assertFalse(projectEvent.equals(projectEvent2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectEventNullProjectFirstEvent() {
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setProject(PROJECT);

        ProjectEvent projectEvent2 = new ProjectEvent();

        assertFalse(projectEvent.equals(projectEvent2));
    }

    @Test
    public void testEqualHashCodeForPersistentProjectEvent() {
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setId(ID);

        ProjectEvent projectEvent2 = new ProjectEvent();
        projectEvent2.setId(ID);

        assertTrue(projectEvent.hashCode() == projectEvent2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentProjectEvent() {
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setId(ID);

        ProjectEvent projectEvent2 = new ProjectEvent();
        projectEvent2.setId(ID2);

        assertFalse(projectEvent.hashCode() == projectEvent2.hashCode());
    }



    @Test
    public void testEqualHashCodeForNonPersistentProjectEvent() {

        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setStartDate(START_DATE);
        projectEvent.setEventType(EVENT_TYPE);
        projectEvent.setProject(PROJECT);

        ProjectEvent projectEvent2 = new ProjectEvent();
        projectEvent2.setStartDate(START_DATE);
        projectEvent2.setEventType(EVENT_TYPE);
        projectEvent2.setProject(PROJECT);

        assertTrue(projectEvent.hashCode() == projectEvent2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentProjectEvent() {
        ProjectEvent projectEvent = new ProjectEvent();
        projectEvent.setStartDate(START_DATE);
        projectEvent.setEventType(EVENT_TYPE);
        projectEvent.setProject(PROJECT);

        ProjectEvent projectEvent2 = new ProjectEvent();
        projectEvent2.setStartDate(START_DATE2);
        projectEvent2.setEventType(EVENT_TYPE2);
        projectEvent2.setProject(PROJECT2);

        assertFalse(projectEvent.hashCode() == projectEvent2.hashCode());
    }

}
