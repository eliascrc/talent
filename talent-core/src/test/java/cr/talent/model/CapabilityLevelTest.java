package cr.talent.model;

import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the capability level methods, to know all the different paths they could take.
 * This class contains the test of the inherited methods ans attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
public class CapabilityLevelTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final Organization ORGANIZATION = mock(Organization.class);
    private static final Organization ORGANIZATION2 = mock(Organization.class);
    private static final String NAME = "Capability";
    private static final String NAME2 = "Capability 2";
    private static final Capability CAPABILITY = mock(Capability.class);
    private static final Capability CAPABILITY2 = mock(Capability.class);

    @Test
    public void coreTest(){

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Verify Constructor
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        int hierarchyPosition = 1;

        // Verify the sets
        capabilityLevel.setId(ID);
        capabilityLevel.setEntityCreationTimestamp(entityCreationTimestamp);
        capabilityLevel.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        capabilityLevel.setEntityVersion(entityVersion);
        capabilityLevel.setHierarchyPosition(hierarchyPosition);
        capabilityLevel.setProject(new HashSet<>());
        capabilityLevel.setRequiredSkills(new HashSet<>());
        capabilityLevel.setOrganization(ORGANIZATION);
        capabilityLevel.setName(NAME);
        capabilityLevel.setCapability(CAPABILITY);

        //Verify the gets
        assertEquals(ID, capabilityLevel.getId());
        assertEquals(entityCreationTimestamp, capabilityLevel.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, capabilityLevel.getLastUpdatedTimestamp());
        assertEquals(entityVersion, capabilityLevel.getEntityVersion());
        assertEquals(hierarchyPosition, capabilityLevel.getHierarchyPosition());
        assertNotNull(capabilityLevel.getProject());
        assertNotNull(capabilityLevel.getRequiredSkills());
        assertEquals(ORGANIZATION, capabilityLevel.getOrganization());
        assertEquals(NAME, capabilityLevel.getName());
        assertEquals(CAPABILITY, capabilityLevel.getCapability());

    }

    //ON EQUALS TESTS.

    @Test
    public void testEqualForSameObject() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();

        assertTrue(capabilityLevel.equals(capabilityLevel));
    }

    @Test
    public void testEqualForDifferentClass() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();

        assertFalse(capabilityLevel.equals(new Image()));
    }

    @Test
    public void testEqualForPersistentCapabilityLevel() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setId(ID);

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();
        capabilityLevel2.setId(ID);

        assertTrue(capabilityLevel.equals(capabilityLevel2));
    }

    @Test
    public void testNonEqualForPersistentCapabilityLevel() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setId(ID);

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();
        capabilityLevel2.setId(ID2);

        assertFalse(capabilityLevel.equals(capabilityLevel2));
    }

    @Test
    public void testEqualForNonPersistentCapabilityLevel() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setOrganization(ORGANIZATION);
        capabilityLevel.setName(NAME);
        capabilityLevel.setCapability(CAPABILITY);

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();
        capabilityLevel2.setOrganization(ORGANIZATION);
        capabilityLevel2.setName(NAME);
        capabilityLevel2.setCapability(CAPABILITY);

        assertTrue(capabilityLevel.equals(capabilityLevel2));
    }

    @Test
    public void testNonEqualForNonPersistentCapabilityLevel() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setOrganization(ORGANIZATION);
        capabilityLevel.setName(NAME);
        capabilityLevel.setCapability(CAPABILITY);

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();
        capabilityLevel2.setOrganization(ORGANIZATION2);
        capabilityLevel2.setName(NAME2);
        capabilityLevel2.setCapability(CAPABILITY2);

        assertFalse(capabilityLevel.equals(capabilityLevel2));
    }

    @Test
    public void testEqualForNonPersistentCapabilityLevelNullValues() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();

        assertTrue(capabilityLevel.equals(capabilityLevel2));
    }

    @Test
    public void testNonEqualForNonPersistentCapabilityLevelNullName() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();
        capabilityLevel2.setName(NAME2);

        assertFalse(capabilityLevel.equals(capabilityLevel2));
    }

    @Test
    public void testNonEqualForNonPersistentCapabilityLevelNullOrganization() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();
        capabilityLevel2.setOrganization(ORGANIZATION2);

        assertFalse(capabilityLevel.equals(capabilityLevel2));
    }

    @Test
    public void testNonEqualForNonPersistentCapabilityLevelNullOrganizationFirstCapabilityLevel() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setOrganization(ORGANIZATION);

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();

        assertFalse(capabilityLevel.equals(capabilityLevel2));
    }

    @Test
    public void testNonEqualForNonPersistentCapabilityLevelNullCapability() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();
        capabilityLevel2.setCapability(CAPABILITY2);

        assertFalse(capabilityLevel.equals(capabilityLevel2));
    }

    @Test
    public void testNonEqualForNonPersistentCapabilityLevelNullCapabilityFirstCapabilityLevel() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setCapability(CAPABILITY);

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();

        assertFalse(capabilityLevel.equals(capabilityLevel2));
    }

    @Test
    public void testEqualHashCodeForPersistentCapabilityLevel() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setId(ID);

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();
        capabilityLevel2.setId(ID);

        assertTrue(capabilityLevel.hashCode() == capabilityLevel2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentCapabilityLevel() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setId(ID);

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();
        capabilityLevel2.setId(ID2);

        assertFalse(capabilityLevel.hashCode() == capabilityLevel2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentCapabilityLevel() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setOrganization(ORGANIZATION);
        capabilityLevel.setName(NAME);
        capabilityLevel.setCapability(CAPABILITY);

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();
        capabilityLevel2.setOrganization(ORGANIZATION);
        capabilityLevel2.setName(NAME);
        capabilityLevel2.setCapability(CAPABILITY);

        assertTrue(capabilityLevel.hashCode() == capabilityLevel2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentCapabilityLevel() {
        CapabilityLevel capabilityLevel = new CapabilityLevel();
        capabilityLevel.setOrganization(ORGANIZATION);
        capabilityLevel.setName(NAME);
        capabilityLevel.setCapability(CAPABILITY);

        CapabilityLevel capabilityLevel2 = new CapabilityLevel();
        capabilityLevel2.setOrganization(ORGANIZATION2);
        capabilityLevel2.setName(NAME2);
        capabilityLevel2.setCapability(CAPABILITY2);

        assertFalse(capabilityLevel.hashCode() == capabilityLevel2.hashCode());
    }

}
