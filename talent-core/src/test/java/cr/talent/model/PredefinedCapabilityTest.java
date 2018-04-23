package cr.talent.model;

import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Class that allows to test the PredefinedCapability methods to know all the different paths they could take.
 *
 * @author Daniel Montes de Oca
 */
public class PredefinedCapabilityTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String NAME = "PredefinedCapability";
    private static final String NAME2 = "PredefinedCapability2";

    @Test
    public void coreTest() {

        // Inherited from BasicEntity
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Verify the construtor
        PredefinedCapability predefinedCapability = new PredefinedCapability();

        // Verify the setters
        // Inherited from BasicEntity
        predefinedCapability.setId(ID);
        predefinedCapability.setEntityCreationTimestamp(entityCreationTimestamp);
        predefinedCapability.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        predefinedCapability.setEntityVersion(entityVersion);

        // Inherited from Capability
        predefinedCapability.setName(NAME);
        predefinedCapability.setLevelHierarchy(new HashSet<>());


        // Verify the setters
        assertEquals(ID, predefinedCapability.getId());
        assertEquals(entityCreationTimestamp, predefinedCapability.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, predefinedCapability.getLastUpdatedTimestamp());
        assertEquals(entityVersion, predefinedCapability.getEntityVersion());
        assertEquals(NAME, predefinedCapability.getName());
        assertNotNull(predefinedCapability.getLevelHierarchy());
    }

    @Test
    public void testEqualForSameObject() {
        PredefinedCapability predefinedCapability = new PredefinedCapability();
        assertTrue(predefinedCapability.equals(predefinedCapability));
    }

    @Test
    public void testEqualForDifferentClass() {
        PredefinedCapability predefinedCapability = new PredefinedCapability();

        assertFalse(predefinedCapability.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentPredefinedCapability() {
        PredefinedCapability predefinedCapability = new PredefinedCapability();
        predefinedCapability.setId(ID);

        PredefinedCapability predefinedCapability2 = new PredefinedCapability();
        predefinedCapability2.setId(ID);

        assertTrue(predefinedCapability.equals(predefinedCapability2));
    }

    @Test
    public void testNonEqualForPersistentPredefinedCapability() {
        PredefinedCapability predefinedCapability = new PredefinedCapability();
        predefinedCapability.setId(ID);

        PredefinedCapability predefinedCapability2 = new PredefinedCapability();
        predefinedCapability2.setId(ID2);

        assertFalse(predefinedCapability.equals(predefinedCapability2));
    }

    @Test
    public void testEqualForNonPersistentPredefinedCapability() {
        PredefinedCapability predefinedCapability = new PredefinedCapability();
        predefinedCapability.setName(NAME);

        PredefinedCapability predefinedCapability2 = new PredefinedCapability();
        predefinedCapability2.setName(NAME);

        assertTrue(predefinedCapability.equals(predefinedCapability2));
    }

    @Test
    public void testNonEqualForNonPersistentPredefinedCapability() {
        PredefinedCapability predefinedCapability = new PredefinedCapability();
        predefinedCapability.setName(NAME);

        PredefinedCapability predefinedCapability2 = new PredefinedCapability();
        predefinedCapability2.setName(NAME2);

        assertFalse(predefinedCapability.equals(predefinedCapability2));
    }

    @Test
    public void testEqualHashCodeForPersistentPredefinedCapability() {
        PredefinedCapability predefinedCapability = new PredefinedCapability();
        predefinedCapability.setId(ID);

        PredefinedCapability predefinedCapability2 = new PredefinedCapability();
        predefinedCapability2.setId(ID);

        assertTrue(predefinedCapability.hashCode() == predefinedCapability2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentPredefinedCapability() {
        PredefinedCapability predefinedCapability = new PredefinedCapability();
        predefinedCapability.setId(ID);

        PredefinedCapability predefinedCapability2 = new PredefinedCapability();
        predefinedCapability2.setId(ID2);

        assertFalse(predefinedCapability.hashCode() == predefinedCapability2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentPredefinedCapability() {
        PredefinedCapability predefinedCapability = new PredefinedCapability();
        predefinedCapability.setName(NAME);

        PredefinedCapability predefinedCapability2 = new PredefinedCapability();
        predefinedCapability2.setName(NAME);

        assertTrue(predefinedCapability.hashCode() == predefinedCapability2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentPredefinedCapability() {
        PredefinedCapability predefinedCapability = new PredefinedCapability();
        predefinedCapability.setName(NAME);

        PredefinedCapability predefinedCapability2 = new PredefinedCapability();
        predefinedCapability2.setName(NAME2);

        assertFalse(predefinedCapability.hashCode() == predefinedCapability2.hashCode());
    }

}
