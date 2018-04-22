package cr.talent.model;

import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the PredefinedSkillTest methods to know all the different paths they could take.
 *
 * @author Fabian Roberto Leandro
 */
public class PredefinedSkillTest {
    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String NAME = "NAME";
    private static final String NAME2 = "NAME2";

    @Test
    public void coreTest() {
        // Inheritted from BasicEntity
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        
        // Defined in PredefinedSkill
        PredefinedSkillCategory predefinedSkillCategory = mock(PredefinedSkillCategory.class);

        // Verify Constructor
        PredefinedSkill predefinedSkill = new PredefinedSkill();

        // Verify the sets
        predefinedSkill.setId(ID);
        predefinedSkill.setEntityCreationTimestamp(entityCreationTimestamp);
        predefinedSkill.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        predefinedSkill.setEntityVersion(entityVersion);
        predefinedSkill.setName(NAME);
        predefinedSkill.setCategory(predefinedSkillCategory);

        // Verify the gets
        assertEquals(ID, predefinedSkill.getId());
        assertEquals(entityCreationTimestamp, predefinedSkill.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, predefinedSkill.getLastUpdatedTimestamp());
        assertEquals(entityVersion, predefinedSkill.getEntityVersion());
        assertEquals(NAME, predefinedSkill.getName());
        assertNotNull(predefinedSkill.getCategory());
    }

    @Test
    public void testEqualForSameObject() {
        PredefinedSkill predefinedSkill = new PredefinedSkill();

        assertTrue(predefinedSkill.equals(predefinedSkill));
    }

    @Test
    public void testEqualForDifferentClass() {
        PredefinedSkill predefinedSkill = new PredefinedSkill();

        assertFalse(predefinedSkill.equals(new Object()));
    }

    // En los persistance se compara el ID.
    @Test
    public void testEqualForPersistentPredefinedSkill() {
        PredefinedSkill predefinedSkill = new PredefinedSkill();
        predefinedSkill.setId(ID);

        PredefinedSkill predefinedSkill2 = new PredefinedSkill();
        predefinedSkill2.setId(ID);

        assertTrue(predefinedSkill.equals(predefinedSkill2));
    }

    @Test
    public void testNonEqualForPersistentPredefinedSkill() {
        PredefinedSkill predefinedSkill = new PredefinedSkill();
        predefinedSkill.setId(ID);

        PredefinedSkill predefinedSkill2 = new PredefinedSkill();
        predefinedSkill2.setId(ID2);

        assertFalse(predefinedSkill.equals(predefinedSkill2));
    }

    @Test
    public void testEqualForNonPersistentPredefinedSkill() {
        PredefinedSkill predefinedSkill = new PredefinedSkill();
        predefinedSkill.setName(NAME);

        PredefinedSkill predefinedSkill2 = new PredefinedSkill();
        predefinedSkill2.setName(NAME);

        assertTrue(predefinedSkill.equals(predefinedSkill2));
    }

    @Test
    public void testNonEqualForNonPersistentPredefinedSkill() {
        PredefinedSkill predefinedSkill = new PredefinedSkill();
        predefinedSkill.setName(NAME);

        PredefinedSkill predefinedSkill2 = new PredefinedSkill();
        predefinedSkill2.setName(NAME2);

        assertFalse(predefinedSkill.equals(predefinedSkill2));
    }

    //ON HASH TESTS.

    //Se hace con el id heredado de basic entity.

    @Test
    public void testEqualHashCodeForPersistentPredefinedSkill() {
        PredefinedSkill predefinedSkill = new PredefinedSkill();
        predefinedSkill.setId(ID);

        PredefinedSkill predefinedSkill2 = new PredefinedSkill();
        predefinedSkill2.setId(ID);

        assertTrue(predefinedSkill.hashCode() == predefinedSkill2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentPredefinedSkill() {
        PredefinedSkill predefinedSkill = new PredefinedSkill();
        predefinedSkill.setId(ID);

        PredefinedSkill predefinedSkill2 = new PredefinedSkill();
        predefinedSkill2.setId(ID2);

        assertFalse(predefinedSkill.hashCode() == predefinedSkill2.hashCode());
    }


     //el non persistant se hace con los atributos del on equals.

    @Test
    public void testEqualHashCodeForNonPersistentPredefinedSkill() {
        PredefinedSkill predefinedSkill = new PredefinedSkill();
        predefinedSkill.setName(NAME);

        PredefinedSkill predefinedSkill2 = new PredefinedSkill();
        predefinedSkill2.setName(NAME);

        assertTrue(predefinedSkill.hashCode() == predefinedSkill2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentPredefinedSkill() {
        PredefinedSkill predefinedSkill = new PredefinedSkill();
        predefinedSkill.setName(NAME);

        PredefinedSkill predefinedSkill2 = new PredefinedSkill();
        predefinedSkill2.setName(NAME2);

        assertFalse(predefinedSkill.hashCode() == predefinedSkill2.hashCode());
    }
}
