package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the Kudo methods to know all the different paths they could take.
 *
 * @author Josue Leon Sarkis
 */
public class KudoTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final Project RELATED_PROJECT = mock(Project.class);
    private static final Project RELATED_PROJECT2 = mock(Project.class);

    @Test
    public void coreTest(){

        // Inherited from BasicEntity
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Inherited from Observation
        TechnicalResource observee = mock(TechnicalResource.class);
        String description = "Great work!";

        // Declared in Kudo
        TechnicalResource author = mock(TechnicalResource.class);

        //Verify the constructor
        Kudo kudo = new Kudo();

        // Verify the sets
        kudo.setId(ID);
        kudo.setEntityCreationTimestamp(entityCreationTimestamp);
        kudo.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        kudo.setEntityVersion(entityVersion);
        kudo.setDescription(description);
        kudo.setObservee(observee);
        kudo.setRelatedProject(RELATED_PROJECT);
        kudo.setAuthor(author);

        // Verify the gets
        assertEquals(ID, kudo.getId());
        assertEquals(entityCreationTimestamp, kudo.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, kudo.getLastUpdatedTimestamp());
        assertEquals(entityVersion, kudo.getEntityVersion());
        assertEquals(observee, kudo.getObservee());
        assertEquals(description, kudo.getDescription());
        assertEquals(RELATED_PROJECT, kudo.getRelatedProject());
        assertEquals(author, kudo.getAuthor());

    }

    //ON EQUALS TESTS.
    @Test
    public void testEqualForSameObject() {
        Kudo kudo1 = new Kudo();

        assertTrue(kudo1.equals(kudo1));
    }

    @Test
    public void testEqualForDifferentClass() {
        Kudo kudo1 = new Kudo();

        Date date = new Date();

        assertFalse(kudo1.equals(date));
    }

    @Test
    public void testEqualForPersistentKudo() {
        Kudo kudo1 = new Kudo();
        kudo1.setId(ID);

        Kudo kudo2 = new Kudo();
        kudo2.setId(ID);

        assertTrue(kudo1.equals(kudo2));
    }

    @Test
    public void testNonEqualForPersistentKudo() {
        Kudo kudo1 = new Kudo();
        kudo1.setId(ID);

        Kudo kudo2 = new Kudo();
        kudo2.setId(ID2);

        assertFalse(kudo1.equals(kudo2));
    }

    @Test
    public void testEqualForNonPersistentKudo() {
        Kudo kudo1 = new Kudo();
        kudo1.setRelatedProject(RELATED_PROJECT);

        Kudo kudo2 = new Kudo();
        kudo2.setRelatedProject(RELATED_PROJECT);

        assertTrue(kudo1.equals(kudo2));
    }

    @Test
    public void testEqualForNonPersistentKudoNullProject() {
        Kudo kudo1 = new Kudo();

        Kudo kudo2 = new Kudo();

        assertTrue(kudo1.equals(kudo2));
    }

    @Test
    public void testNonEqualForNonPersistentKudo() {
        Kudo kudo1 = new Kudo();
        kudo1.setRelatedProject(RELATED_PROJECT);

        Kudo kudo2 = new Kudo();
        kudo2.setRelatedProject(RELATED_PROJECT2);

        assertFalse(kudo1.equals(kudo2));
    }

    @Test
    public void testNonEqualForNonPersistentKudoNullProject() {
        Kudo kudo1 = new Kudo();

        Kudo kudo2 = new Kudo();
        kudo2.setRelatedProject(RELATED_PROJECT2);

        assertFalse(kudo1.equals(kudo2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentKudo() {
        Kudo kudo1 = new Kudo();
        kudo1.setId(ID);

        Kudo kudo2 = new Kudo();
        kudo2.setId(ID);

        assertTrue(kudo1.hashCode() == kudo2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentKudo() {
        Kudo kudo1 = new Kudo();
        kudo1.setId(ID);

        Kudo kudo2 = new Kudo();
        kudo2.setId(ID2);

        assertFalse(kudo1.hashCode() == kudo2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentKudo() {
        Kudo kudo1 = new Kudo();
        kudo1.setRelatedProject(RELATED_PROJECT);

        Kudo kudo2 = new Kudo();
        kudo2.setRelatedProject(RELATED_PROJECT);

        assertTrue(kudo1.hashCode() == kudo2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentKudo() {
        Kudo kudo1 = new Kudo();
        kudo1.setRelatedProject(RELATED_PROJECT);

        Kudo kudo2 = new Kudo();
        kudo2.setRelatedProject(RELATED_PROJECT2);

        assertFalse(kudo1.hashCode() == kudo2.hashCode());
    }

}
