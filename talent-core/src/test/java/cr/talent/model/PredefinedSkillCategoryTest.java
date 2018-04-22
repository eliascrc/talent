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

import javax.naming.Name;

/**
 * Class that allows to test the emergency contact methods to know all the different paths they could take.
 *
 * @author Otto Mena Kikut
 */
public class PredefinedSkillCategoryTest {

    private static final String NAME1 = "name1";
    private static final String NAME2 = "name2";
    private static final String ID1 = "987";
    private static final String ID2 = "654";

    @Test
    public void coreTest() {


        PredefinedSkillCategory predefinedSkillCategory = new PredefinedSkillCategory();

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        predefinedSkillCategory.setName(NAME1);
        predefinedSkillCategory.setPredefinedSkills(new HashSet<>());
        predefinedSkillCategory.setEntityVersion(entityVersion);
        predefinedSkillCategory.setEntityCreationTimestamp(entityCreationTimestamp);
        predefinedSkillCategory.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        predefinedSkillCategory.setId(ID1);

        assertEquals(NAME1, predefinedSkillCategory.getName());
        assertEquals(entityCreationTimestamp, predefinedSkillCategory.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, predefinedSkillCategory.getLastUpdatedTimestamp());
        assertEquals(entityVersion, predefinedSkillCategory.getEntityVersion());
        assertEquals(ID1, predefinedSkillCategory.getId());
        assertNotNull(predefinedSkillCategory.getName());


    }


    @Test
    public void testEqualForSameObject() {
        PredefinedSkillCategory predefinedSkillCategory1 = new PredefinedSkillCategory();

        assertTrue(predefinedSkillCategory1.equals(predefinedSkillCategory1));
    }

    @Test
    public void testEqualForDifferentClass() {
        PredefinedSkillCategory predefinedSkillCategory1 = new PredefinedSkillCategory();

        assertFalse(predefinedSkillCategory1.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentPredefinedSkillCategory() {
        PredefinedSkillCategory predefinedSkillCategory1 = new PredefinedSkillCategory();
        predefinedSkillCategory1.setId(ID1);

        PredefinedSkillCategory predefinedSkillCategory2 = new PredefinedSkillCategory();
        predefinedSkillCategory2.setId(ID1);

        assertTrue(predefinedSkillCategory1.equals(predefinedSkillCategory2));
    }

    @Test
    public void testNonEqualForPersistentPredefinedSkillCategory() {
        PredefinedSkillCategory predefinedSkillCategory1 = new PredefinedSkillCategory();
        predefinedSkillCategory1.setId(ID1);

        PredefinedSkillCategory predefinedSkillCategory2 = new PredefinedSkillCategory();
        predefinedSkillCategory2.setId(ID2);

        assertFalse(predefinedSkillCategory1.equals(predefinedSkillCategory2));
    }

    @Test
    public void testEqualForNonPersistentPredefinedSkillCategory() {
        PredefinedSkillCategory predefinedSkillCategory1 = new PredefinedSkillCategory();
        predefinedSkillCategory1.setName(NAME1);

        PredefinedSkillCategory predefinedSkillCategory2 = new PredefinedSkillCategory();
        predefinedSkillCategory2.setName(NAME1);

        assertTrue(predefinedSkillCategory1.equals(predefinedSkillCategory2));
    }

    @Test
    public void testNonEqualForNonPersistentPredefinedSkillCategory() {
        PredefinedSkillCategory predefinedSkillCategory1 = new PredefinedSkillCategory();
        predefinedSkillCategory1.setName(NAME1);

        PredefinedSkillCategory predefinedSkillCategory2 = new PredefinedSkillCategory();
        predefinedSkillCategory2.setName(NAME2);

        assertFalse(predefinedSkillCategory1.equals(predefinedSkillCategory2));
    }



    @Test
    public void testEqualHashCodeForPersistentPredefinedSkillCategory() {
        PredefinedSkillCategory predefinedSkillCategory1 = new PredefinedSkillCategory();
        predefinedSkillCategory1.setId(ID1);

        PredefinedSkillCategory predefinedSkillCategory2 = new PredefinedSkillCategory();
        predefinedSkillCategory2.setId(ID1);

        assertTrue(predefinedSkillCategory1.hashCode() == predefinedSkillCategory2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentPredefinedSkillCategory() {
        PredefinedSkillCategory predefinedSkillCategory1 = new PredefinedSkillCategory();
        predefinedSkillCategory1.setId(ID1);

        PredefinedSkillCategory predefinedSkillCategory2 = new PredefinedSkillCategory();
        predefinedSkillCategory2.setId(ID2);

        assertFalse(predefinedSkillCategory1.hashCode() == predefinedSkillCategory2.hashCode());
    }



    @Test
    public void testEqualHashCodeForNonPersistentPredefinedSkillCategory() {

        PredefinedSkillCategory predefinedSkillCategory1 = new PredefinedSkillCategory();
        predefinedSkillCategory1.setName(NAME1);

        PredefinedSkillCategory predefinedSkillCategory2 = new PredefinedSkillCategory();
        predefinedSkillCategory2.setName(NAME1);

        assertTrue(predefinedSkillCategory1.hashCode() == predefinedSkillCategory2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentPredefinedSkillCategory() {
        PredefinedSkillCategory predefinedSkillCategory1 = new PredefinedSkillCategory();
        predefinedSkillCategory1.setName(NAME1);

        PredefinedSkillCategory predefinedSkillCategory2 = new PredefinedSkillCategory();
        predefinedSkillCategory2.setName(NAME2);

        assertFalse(predefinedSkillCategory1.hashCode() == predefinedSkillCategory2.hashCode());
    }
}

