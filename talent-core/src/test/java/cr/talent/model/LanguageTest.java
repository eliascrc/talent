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

/**
 * Class that allows to test the language methods to know all the different paths they could take.
 * @author Daniel Montes de Oca
 */
public class LanguageTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String LANGUAGENAME = "English";
    private static final String LANGUAGENAME2 = "Spanish";

    @Test
    public void coreTest() {
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Verify Constructor
        Language language = new Language();

        //Verify the sets
        language.setId(ID);
        language.setEntityCreationTimestamp(entityCreationTimestamp);
        language.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        language.setEntityVersion(entityVersion);
        language.setLanguageName(LANGUAGENAME);
        language.setTechnicalResources(new HashSet<>());

        //Verify the gets
        assertEquals(ID, language.getId());
        assertEquals(entityCreationTimestamp, language.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, language.getLastUpdatedTimestamp());
        assertEquals(entityVersion, language.getEntityVersion());
        assertEquals(LANGUAGENAME, language.getLanguageName());
        assertNotNull(language.getTechnicalResources());
    }

    @Test
    public void testEqualForSameObject() {
        Language language1 = new Language();

        assertTrue(language1.equals(language1));
    }

    @Test
    public void testEqualForDifferentClass() {
        Project language1 = new Project();

        assertFalse(language1.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentLanguage() {
        Language language1 = new Language();
        language1.setId(ID);

        Language language2 = new Language();
        language2.setId(ID);

        assertTrue(language1.equals(language2));
    }

    @Test
    public void testNonEqualForPersistentLanguage() {
        Language language1 = new Language();
        language1.setId(ID);

        Language language2 = new Language();
        language2.setId(ID2);

        assertFalse(language1.equals(language2));
    }

    @Test
    public void testEqualForNonPersistentLanguage() {
        Language language1 = new Language();
        language1.setLanguageName(LANGUAGENAME);

        Language language2 = new Language();
        language2.setLanguageName(LANGUAGENAME);

        assertTrue(language1.equals(language2));
    }

    @Test
    public void testNonEqualForNonPersistentLanguage() {
        Language language1 = new Language();
        language1.setLanguageName(LANGUAGENAME);

        Language language2 = new Language();
        language2.setLanguageName(LANGUAGENAME2);

        assertFalse(language1.equals(language2));
    }

    @Test
    public void testEqualHashCodeForPersistentLanguage() {
        Language language1 = new Language();
        language1.setId(ID);

        Language language2 = new Language();
        language2.setId(ID);

        assertTrue(language1.hashCode() == language2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentLanguage() {
        Language language1 = new Language();
        language1.setId(ID);

        Language language2 = new Language();
        language2.setId(ID2);

        assertFalse(language1.hashCode() == language2.hashCode());
    }

}
