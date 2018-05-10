package cr.talent.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

/**
 * Class that allows to test the project methods, to know all the different paths they could take.
 * This class contains the test of the inherited methods and attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Maria Jose Cubero
 */
public class ProjectTest{

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String NAME = "talent";
    private static final Organization ORGANIZATION = mock(Organization.class);
    private static final String NAME2 = "talent2";
    private static final Organization ORGANIZATION2 = mock(Organization.class);

    private static final String STRING_BUILDER_OUTPUT = "[class name = %s, id = %s]";

    @Test
    public void coreTest() {

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        String description = "A project";
        Date startDate = new Date();
        Date endDate = new Date();
        String jiraLink = "www.jira.com";
        String confluenceLink = "www.confluence.com";
        String versionLink = "www.git.com";
        ProjectEvent state = mock(ProjectEvent.class);

        // Verify Constructor
        Project project = new Project();

        // Verify the sets
        project.setId(ID);
        project.setEntityCreationTimestamp(entityCreationTimestamp);
        project.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        project.setEntityVersion(entityVersion);
        project.setName(NAME);
        project.setDescription(description);
        project.setStartDate(startDate);
        project.setEndDate(endDate);
        project.setJiraLink(jiraLink);
        project.setConfluenceLink(confluenceLink);
        project.setVersionControlLink(versionLink);
        project.setTimeline(new HashSet<>());
        project.setState(state);
        project.setLeadHistory(new HashSet<>());
        project.setProjectCapabilities(new HashSet<>());
        project.setProjectPositions(new HashSet<>());
        project.setOrganization(ORGANIZATION);
        project.setObservations(new HashSet<>());

        // Verify the gets
        assertEquals(ID, project.getId());
        assertEquals(entityCreationTimestamp, project.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, project.getLastUpdatedTimestamp());
        assertEquals(entityVersion, project.getEntityVersion());
        assertEquals(NAME, project.getName());
        assertEquals(description, project.getDescription());
        assertEquals(startDate, project.getStartDate());
        assertEquals(endDate, project.getEndDate());
        assertEquals(jiraLink, project.getJiraLink());
        assertEquals(confluenceLink, project.getConfluenceLink());
        assertEquals(versionLink, project.getVersionControlLink());
        assertNotNull(project.getTimeline());
        assertEquals(state, project.getState());
        assertNotNull(project.getLeadHistory());
        assertNotNull(project.getProjectCapabilities());
        assertNotNull(project.getProjectPositions());
        assertEquals(ORGANIZATION, project.getOrganization());
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

        assertFalse(project1.equals(new Image()));
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
        project1.setOrganization(ORGANIZATION);

        Project project2 = new Project();
        project2.setName(NAME);
        project2.setOrganization(ORGANIZATION);

        assertTrue(project1.equals(project2));
    }

    @Test
    public void testEqualForNonPersistentProjectNullName() {
        Project project1 = new Project();

        Project project2 = new Project();

        assertTrue(project1.equals(project2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectNullName() {
        Project project1 = new Project();

        Project project2 = new Project();
        project2.setName(NAME);

        assertFalse(project1.equals(project2));
    }

    @Test
    public void testNonEqualForNonPersistentProject() {
        Project project1 = new Project();
        project1.setName(NAME);
        project1.setOrganization(ORGANIZATION);

        Project project2 = new Project();
        project2.setName(NAME2);
        project2.setOrganization(ORGANIZATION2);

        assertFalse(project1.equals(project2));
    }

    @Test
    public void testEqualForNonPersistentProjectNullValues() {
        Project project = new Project();

        Project project2 = new Project();

        assertTrue(project.equals(project2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectNullOrganization() {
        Project project = new Project();

        Project project2 = new Project();
        project2.setOrganization(ORGANIZATION2);

        assertFalse(project.equals(project2));
    }

    @Test
    public void testNonEqualForNonPersistentProjectNullOrganizationFirstProject() {
        Project project = new Project();
        project.setOrganization(ORGANIZATION);

        Project project2 = new Project();

        assertFalse(project.equals(project2));
    }

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

    @Test
    public void testEqualHashCodeForNonPersistentProject() {
        Project project1 = new Project();
        project1.setName(NAME);
        project1.setOrganization(ORGANIZATION);

        Project project2 = new Project();
        project2.setName(NAME);
        project2.setOrganization(ORGANIZATION);

        assertTrue(project1.hashCode() == project2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentProject() {
        Project project1 = new Project();
        project1.setName(NAME);
        project1.setOrganization(ORGANIZATION);

        Project project2 = new Project();
        project2.setName(NAME2);
        project2.setOrganization(ORGANIZATION2);

        assertFalse(project1.hashCode() == project2.hashCode());
    }

    /**
     *  Only on this class. Test inherited method from {@link cr.talent.model.BasicEntity} class.
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

        } catch (Exception exception){
            //Do nothing
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
