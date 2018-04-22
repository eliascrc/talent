package cr.talent.model;

import static org.mockito.Mockito.mock;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * Class that allows to test the warning methods to know all the different paths they could take.
 *
 * @author Fabian Roberto Leandro
 */
public class WarningTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final Project RELATED_PROJECT = mock(Project.class);
    private static final Project RELATED_PROJECT2 = mock(Project.class);

    @Test
    public void coreTest() {
        // Inheritted from BasicEntity
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

         // Inheritted from Observation
         String description = "description";
         TechnicalResource observee = mock(TechnicalResource.class);

         // Declared in Warning
         TechnicalResourceManager author = mock(TechnicalResourceManager.class);

         // Verify the constructor
         Warning warning = new Warning();

         // Verify the setters
         warning.setId(ID);
         warning.setEntityCreationTimestamp(entityCreationTimestamp);
         warning.setLastUpdatedTimestamp(lastUpdatedTimestamp);
         warning.setEntityVersion(entityVersion);
         warning.setDescription(description);
		 warning.setObservee(observee);
		 warning.setRelatedProject(RELATED_PROJECT);
		 warning.setAuthor(author);

		 // Verify the getters
         assertEquals(ID, warning.getId());
         assertEquals(entityCreationTimestamp, warning.getEntityCreationTimestamp());
         assertEquals(lastUpdatedTimestamp, warning.getLastUpdatedTimestamp());
         assertEquals(warning.getDescription(),description);
         assertEquals(warning.getObservee(),observee);
         assertEquals(warning.getRelatedProject(),RELATED_PROJECT);
         assertEquals(warning.getAuthor(),author);
     }

     @Test
    public void testEqualForSameObject() {
        Warning warning = new Warning();

        assertTrue(warning.equals(warning));
    }

    @Test
    public void testEqualForDifferentClass() {
        Warning warning = new Warning();

        assertFalse(warning.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentWarning() {
        Warning warning = new Warning();
        warning.setId(ID);

        Warning warning2 = new Warning();
        warning2.setId(ID);

        assertTrue(warning.equals(warning2));
    }

    @Test
    public void testNonEqualForPersistentWarning() {
        Warning warning = new Warning();
        warning.setId(ID);

        Warning warning2 = new Warning();
        warning2.setId(ID2);

        assertFalse(warning.equals(warning2));
    }

    @Test
    public void testEqualForNonPersistentWarning() {
        Warning warning1 = new Warning();
        warning1.setRelatedProject(RELATED_PROJECT);

        Warning warning2 = new Warning();
        warning2.setRelatedProject(RELATED_PROJECT);

        Assert.assertTrue(warning1.equals(warning2));
    }

    @Test
    public void testNonEqualForNonPersistentWarning() {
        Warning warning1 = new Warning();
        warning1.setRelatedProject(RELATED_PROJECT);

        Warning warning2 = new Warning();
        warning2.setRelatedProject(RELATED_PROJECT2);

        assertFalse(warning1.equals(warning2));
    }

    @Test
    public void testEqualHashCodeForPersistentWarning() {
        Warning warning = new Warning();
        warning.setId(ID);

        Warning warning2 = new Warning();
        warning2.setId(ID);

        assertTrue(warning.hashCode() == warning2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentWarning() {
        Warning warning = new Warning();
        warning.setId(ID);

        Warning warning2 = new Warning();
        warning2.setId(ID2);

        assertFalse(warning.hashCode() == warning2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentWarning() {
        Warning warning1 = new Warning();
        warning1.setRelatedProject(RELATED_PROJECT);

        Warning warning2 = new Warning();
        warning2.setRelatedProject(RELATED_PROJECT);

        Assert.assertTrue(warning1.hashCode() == warning2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentWarning() {
        Warning warning1 = new Warning();
        warning1.setRelatedProject(RELATED_PROJECT);

        Warning warning2 = new Warning();
        warning2.setRelatedProject(RELATED_PROJECT2);

        assertFalse(warning1.hashCode() == warning2.hashCode());
    }
}
