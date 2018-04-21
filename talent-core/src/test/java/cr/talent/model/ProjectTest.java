package cr.talent.model;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;


public class ProjectTest{
    private static final String ID = "1234";
    private static final Date ENTITY_CREATION_TIMESTAMP = new Date();
    private static final Date LAST_UPDATED_TIMESTAMP = new Date();
    private static final long ENTITY_VERSION = 1l;
    private static final String NAME = "talent";
    private static final String NAME2 = "talent2";
    private static final Date STARTDATE = new Date();
    private static final Date END_DATE = new Date();

    private static final String STRING_BUILDER_OUTPUT = "[class name = %s, id = %s]";

    @Test
    public void coreTest() {

        Organization organization = mock(Organization.class);

        // Verify Constructor
        Project project = new Project();

        // Verify the sets
        project.setId(ID);
        project.setEntityCreationTimestamp(ENTITY_CREATION_TIMESTAMP);
        project.setLastUpdatedTimestamp(LAST_UPDATED_TIMESTAMP);
        project.setEntityVersion(ENTITY_VERSION);
        project.setName(NAME);
        project.setStartDate(STARTDATE);
        project.setEndDate(END_DATE);
        project.setProjectCapabilities(new HashSet<>());
        project.setProjectManagerHistory(new HashSet<>());
        project.setState(ProjectState.UPCOMING);
        project.setOrganization(organization);
        project.setObservations(new HashSet<>());

        // Verify the gets
        assertEquals(ID, project.getId());
        assertEquals(ENTITY_CREATION_TIMESTAMP, project.getEntityCreationTimestamp());
        assertEquals(LAST_UPDATED_TIMESTAMP, project.getLastUpdatedTimestamp());
        assertEquals(ENTITY_VERSION, project.getEntityVersion());
        assertEquals(NAME, project.getName());
        assertEquals(STARTDATE, project.getStartDate());
        assertEquals(END_DATE, project.getEndDate());
        assertNotNull(project.getProjectCapabilities());
        assertNotNull(project.getProjectManagerHistory());
        assertEquals(ProjectState.UPCOMING, project.getState());
        assertEquals(organization, project.getOrganization());
        assertNotNull(project.getObservations());
    }

    @Test
    public void testEqualForPersistentProject() {
        Project project1 = new Project();
        project1.setId(ID);

        Project project2 = new Project();
        project2.setId(ID);

        assertTrue(project1.equals(project2));
    }

    @Test
    public void testEqualForSameObject() {
        Project project1 = new Project();

        assertTrue(project1.equals(project1));
    }

    @Test
    public void testEqualForDifferentClass() {
        Project project1 = new Project();

        assertFalse(project1.equals(new Object()));
    }

    @Test
    public void testEqualForNonPersistentNotification() {
        Project project1 = new Project();
        project1.setName(NAME);

        Project project2 = new Project();
        project1.setName(NAME);

        assertFalse(project1.equals(project2));
    }


    @Test
    public void testNonEqualForNonPersistentNotification() {
        Project project1 = new Project();
        project1.setName(NAME);

        Project project2 = new Project();
        project1.setName(NAME2);

        assertFalse(project1.equals(project2));
    }

    @Test
    public void testHashCodeForPersistentProject() {
        Project project1 = new Project();
        project1.setId(ID);

        Project project2 = new Project();
        project2.setId(ID);

        assertTrue(project1.hashCode() == project2.hashCode());
    }


    @Test
    public void testClone() {
        Project project1 = new Project();

        try {
            Object cloned = project1.clone();
            assertNotNull(cloned);
            assertThat(cloned, instanceOf(Project.class));

            BasicEntity basicEntity = (BasicEntity) cloned;
            assertNull(basicEntity.getId());
            assertNull(basicEntity.getEntityCreationTimestamp());
            assertNull(basicEntity.getLastUpdatedTimestamp());
            assertEquals(basicEntity.getEntityVersion(), -1);

        }catch (Exception exception){

        }
    }

    @Test
    public void testStringBuilder() {
        Project project1 = new Project();
        project1.setId(ID);

        String expectedOutput = String.format(STRING_BUILDER_OUTPUT, project1.getClass().getName(), project1.getId());

        assertEquals(project1.toString(), expectedOutput);

    }

    @Test
    public void testCompareTo() {
        Project project1 = new Project();
        Date date = new Date();
        project1.setEntityCreationTimestamp(date);

        Project project2 = new Project();
        project2.setEntityCreationTimestamp(date);

        assertEquals(project1.compareTo(project2), 0);

    }
}
