package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the predefined capability level methods to know all the different paths they could take.
 * This class contains the test of the inherited methods
 * from {@link cr.talent.model.BasicEntity} and {@link cr.talent.model.CapabilityLevel} classes.
 *
 * @author Elías Calderón
 */
public class PredefinedCapabilityLevelTest {
    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String NAME = "Capability";
    private static final String NAME2 = "Level";

    @Test
    public void coreTest () {

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        int hierarchyPosition = 0;
        PredefinedCapability predefinedCapability = mock(PredefinedCapability.class);

        // Verify the constructor
        PredefinedCapabilityLevel predefinedCapabilityLevel = new PredefinedCapabilityLevel();

        // Verify the sets
        predefinedCapabilityLevel.setId(ID);
        predefinedCapabilityLevel.setEntityCreationTimestamp(entityCreationTimestamp);
        predefinedCapabilityLevel.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        predefinedCapabilityLevel.setEntityVersion(entityVersion);
        predefinedCapabilityLevel.setName(NAME);
        predefinedCapabilityLevel.setHierarchyPosition(hierarchyPosition);
        predefinedCapabilityLevel.setCapability(predefinedCapability);

        // Verify the gets
        assertEquals(ID, predefinedCapabilityLevel.getId());
        assertEquals(entityCreationTimestamp, predefinedCapabilityLevel.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, predefinedCapabilityLevel.getLastUpdatedTimestamp());
        assertEquals(entityVersion, predefinedCapabilityLevel.getEntityVersion());
        assertEquals(NAME, predefinedCapabilityLevel.getName());
        assertEquals(hierarchyPosition, predefinedCapabilityLevel.getHierarchyPosition());
        assertEquals(predefinedCapability, predefinedCapabilityLevel.getCapability());

    }

    @Test
    public void testEqualForSameObject() {
        PredefinedCapabilityLevel predefinedCapabilityLevel = new PredefinedCapabilityLevel();

        assertTrue(predefinedCapabilityLevel.equals(predefinedCapabilityLevel));
    }

    @Test
    public void testEqualForDifferentClass() {
        PredefinedCapabilityLevel predefinedCapabilityLevel = new PredefinedCapabilityLevel();

        assertFalse(predefinedCapabilityLevel.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentPredefinedCapabilityLevel() {
        PredefinedCapabilityLevel predefinedCapabilityLevel1 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel1.setId(ID);

        PredefinedCapabilityLevel predefinedCapabilityLevel2 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel2.setId(ID);

        assertTrue(predefinedCapabilityLevel1.equals(predefinedCapabilityLevel2));
    }

    @Test
    public void testNonEqualForPersistentPredefinedCapabilityLevel() {
        PredefinedCapabilityLevel predefinedCapabilityLevel1 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel1.setId(ID);

        PredefinedCapabilityLevel predefinedCapabilityLevel2 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel2.setId(ID2);

        assertFalse(predefinedCapabilityLevel1.equals(predefinedCapabilityLevel2));
    }

    @Test
    public void testEqualForNonPersistentPredefinedCapabilityLevel() {
        PredefinedCapabilityLevel predefinedCapabilityLevel1 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel1.setName(NAME);

        PredefinedCapabilityLevel predefinedCapabilityLevel2 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel2.setName(NAME);

        assertTrue(predefinedCapabilityLevel1.equals(predefinedCapabilityLevel2));
    }

    @Test
    public void testNonEqualForNonPersistentPredefinedCapabilityLevel() {
        PredefinedCapabilityLevel predefinedCapabilityLevel1 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel1.setName(NAME);

        PredefinedCapabilityLevel predefinedCapabilityLevel2 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel2.setName(NAME2);

        assertFalse(predefinedCapabilityLevel1.equals(predefinedCapabilityLevel2));
    }

    @Test
    public void testEqualHashCodeForPersistentPredefinedCapabilityLevel() {
        PredefinedCapabilityLevel predefinedCapabilityLevel1 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel1.setId(ID);

        PredefinedCapabilityLevel predefinedCapabilityLevel2 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel2.setId(ID);

        assertTrue(predefinedCapabilityLevel1.hashCode() == predefinedCapabilityLevel2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentPredefinedCapabilityLevel() {
        PredefinedCapabilityLevel predefinedCapabilityLevel1 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel1.setId(ID);

        PredefinedCapabilityLevel predefinedCapabilityLevel2 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel2.setId(ID2);

        assertFalse(predefinedCapabilityLevel1.hashCode() == predefinedCapabilityLevel2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentPredefinedCapabilityLevel() {
        PredefinedCapabilityLevel predefinedCapabilityLevel1 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel1.setName(NAME);

        PredefinedCapabilityLevel predefinedCapabilityLevel2 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel2.setName(NAME);

        assertTrue(predefinedCapabilityLevel1.hashCode() == predefinedCapabilityLevel2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentPredefinedCapabilityLevel() {
        PredefinedCapabilityLevel predefinedCapabilityLevel1 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel1.setName(NAME);

        PredefinedCapabilityLevel predefinedCapabilityLevel2 = new PredefinedCapabilityLevel();
        predefinedCapabilityLevel2.setName(NAME2);

        assertFalse(predefinedCapabilityLevel1.hashCode() == predefinedCapabilityLevel2.hashCode());
    }

}
