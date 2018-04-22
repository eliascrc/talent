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
    private static final String ID2 = "12345";
    private static final String NAME = "talent";
    private static final String NAME2 = "talent2";

    private static final String STRING_BUILDER_OUTPUT = "[class name = %s, id = %s]";

    @Test
    public void coreTest() {

        Organization organization = mock(Organization.class);
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        Date startDate = new Date();
        Date endDate = new Date();

        // Verify Constructor
        Project project = new Project();

        // Verify the sets
        project.setId(ID);
        project.setEntityCreationTimestamp(entityCreationTimestamp);
        project.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        project.setEntityVersion(entityVersion);
        project.setName(NAME);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setProjectCapabilities(new HashSet<>());
        project.setProjectManagerHistory(new HashSet<>());
        project.setState(ProjectState.UPCOMING);
        project.setOrganization(organization);
        project.setObservations(new HashSet<>());

        // Verify the gets
        assertEquals(ID, project.getId());
        assertEquals(entityCreationTimestamp, project.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, project.getLastUpdatedTimestamp());
        assertEquals(entityVersion, project.getEntityVersion());
        assertEquals(NAME, project.getName());
        assertEquals(startDate, project.getStartDate());
        assertEquals(endDate, project.getEndDate());
        assertNotNull(project.getProjectCapabilities());
        assertNotNull(project.getProjectManagerHistory());
        assertEquals(ProjectState.UPCOMING, project.getState());
        assertEquals(organization, project.getOrganization());
        assertNotNull(project.getObservations());
    }

    //ON EQUALS TESTS.

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

    // En los persistance se compara el ID.
    @Test
    public void testEqualForPersistentProject() {
        Project project1 = new Project();
        project1.setId(ID);

        Project project2 = new Project();
        project2.setId(ID);

        assertTrue(project1.equals(project2));
    }

    @Test
    public void testNonEqualForPersistentProject() {
        Project project1 = new Project();
        project1.setId(ID);

        Project project2 = new Project();
        project2.setId(ID2);

        assertFalse(project1.equals(project2));
    }

    @Test
    public void testEqualForNonPersistentProject() {
        Project project1 = new Project();
        project1.setName(NAME);

        Project project2 = new Project();
        project1.setName(NAME);

        assertTrue(project1.equals(project2));
    }

    @Test
    public void testNonEqualForNonPersistentProject() {
        Project project1 = new Project();
        project1.setName(NAME);

        Project project2 = new Project();
        project1.setName(NAME2);

        assertFalse(project1.equals(project2));
    }

    //ON HASH TESTS.

    //Se hace con el id heredado de basic entity.

    @Test
    public void testEqualHashCodeForPersistentProject() {
        Project project1 = new Project();
        project1.setId(ID);

        Project project2 = new Project();
        project2.setId(ID);

        assertTrue(project1.hashCode() == project2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentProject() {
        Project project1 = new Project();
        project1.setId(ID);

        Project project2 = new Project();
        project2.setId(ID2);

        assertFalse(project1.hashCode() == project2.hashCode());
    }


     //el non persistant se hace con los atributos del on equals.

    @Test
    public void testEqualHashCodeForNonPersistentProject() {
        Project project1 = new Project();
        project1.setName(NAME);

        Project project2 = new Project();
        project2.setName(NAME);

        assertTrue(project1.hashCode() == project2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentProject() {
        Project project1 = new Project();
        project1.setName(NAME);

        Project project2 = new Project();
        project2.setName(NAME2);

        assertFalse(project1.hashCode() == project2.hashCode());
    }

    /**
     *  Only on this class.
     */
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

    /**
     *  Only on this class.
     */
    @Test
    public void testStringBuilder() {
        Project project1 = new Project();
        project1.setId(ID);

        String expectedOutput = String.format(STRING_BUILDER_OUTPUT, project1.getClass().getName(), project1.getId());

        assertEquals(project1.toString(), expectedOutput);

    }

    /**
     *  Only on this class.
     */
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
