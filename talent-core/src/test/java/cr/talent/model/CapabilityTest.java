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
 * Class that allows to test the capability methods, to know all the different paths they could take.
 * This class contains the test of the inherited methods ans attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
public class CapabilityTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final Organization ORGANIZATION = mock(Organization.class);
    private static final Organization ORGANIZATION2 = mock(Organization.class);
    private static final String NAME = "Capability";
    private static final String NAME2 = "Capability 2";

    @Test
    public void coreTest(){

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Verify Constructor
        Capability capability = new Capability();

        // Verify the sets
        capability.setId(ID);
        capability.setEntityCreationTimestamp(entityCreationTimestamp);
        capability.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        capability.setEntityVersion(entityVersion);
        capability.setLevelHierarchy(new HashSet<>());
        capability.setAssociatedTechnologies(new HashSet<>());
        capability.setOrganization(ORGANIZATION);
        capability.setName(NAME);

        //Verify the gets
        assertEquals(ID, capability.getId());
        assertEquals(entityCreationTimestamp, capability.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, capability.getLastUpdatedTimestamp());
        assertEquals(entityVersion, capability.getEntityVersion());
        assertNotNull(capability.getLevelHierarchy());
        assertNotNull(capability.getAssociatedTechnologies());
        assertEquals(ORGANIZATION, capability.getOrganization());
        assertEquals(NAME, capability.getName());

    }

    //ON EQUALS TESTS.

    @Test
    public void testEqualForSameObject() {
        Capability capability = new Capability();

        assertTrue(capability.equals(capability));
    }

    @Test
    public void testEqualForDifferentClass() {
        Capability capability = new Capability();

        assertFalse(capability.equals(new Image()));
    }

    @Test
    public void testEqualForPersistentCapability() {
        Capability capability = new Capability();
        capability.setId(ID);

        Capability capability2 = new Capability();
        capability2.setId(ID);

        assertTrue(capability.equals(capability2));
    }

    @Test
    public void testNonEqualForPersistentCapability() {
        Capability capability = new Capability();
        capability.setId(ID);

        Capability capability2 = new Capability();
        capability2.setId(ID2);

        assertFalse(capability.equals(capability2));
    }

    @Test
    public void testEqualForNonPersistentCapability() {
        Capability capability = new Capability();
        capability.setOrganization(ORGANIZATION);
        capability.setName(NAME);

        Capability capability2 = new Capability();
        capability2.setOrganization(ORGANIZATION);
        capability2.setName(NAME);

        assertTrue(capability.equals(capability2));
    }

    @Test
    public void testNonEqualForNonPersistentCapability() {
        Capability capability = new Capability();
        capability.setOrganization(ORGANIZATION);
        capability.setName(NAME);

        Capability capability2 = new Capability();
        capability2.setOrganization(ORGANIZATION2);
        capability2.setName(NAME2);

        assertFalse(capability.equals(capability2));
    }

    @Test
    public void testEqualForNonPersistentCapabilityNullValues() {
        Capability capability = new Capability();

        Capability capability2 = new Capability();

        assertTrue(capability.equals(capability2));
    }

    @Test
    public void testNonEqualForNonPersistentCapabilityNullName() {
        Capability capability = new Capability();

        Capability capability2 = new Capability();
        capability2.setName(NAME2);

        assertFalse(capability.equals(capability2));
    }

    @Test
    public void testNonEqualForNonPersistentCapabilityNullOrganization() {
        Capability capability = new Capability();

        Capability capability2 = new Capability();
        capability2.setOrganization(ORGANIZATION2);

        assertFalse(capability.equals(capability2));
    }

    @Test
    public void testNonEqualForNonPersistentCapabilityNullOrganizationFirstCapability() {
        Capability capability = new Capability();
        capability.setOrganization(ORGANIZATION);

        Capability capability2 = new Capability();

        assertFalse(capability.equals(capability2));
    }

    @Test
    public void testEqualHashCodeForPersistentCapability() {
        Capability capability = new Capability();
        capability.setId(ID);

        Capability capability2 = new Capability();
        capability2.setId(ID);

        assertTrue(capability.hashCode() == capability2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentCapability() {
        Capability capability = new Capability();
        capability.setId(ID);

        Capability capability2 = new Capability();
        capability2.setId(ID2);

        assertFalse(capability.hashCode() == capability2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentCapability() {
        Capability capability = new Capability();
        capability.setOrganization(ORGANIZATION);
        capability.setName(NAME);

        Capability capability2 = new Capability();
        capability2.setOrganization(ORGANIZATION);
        capability2.setName(NAME);

        assertTrue(capability.hashCode() == capability2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentCapability() {
        Capability capability = new Capability();
        capability.setOrganization(ORGANIZATION);
        capability.setName(NAME);

        Capability capability2 = new Capability();
        capability2.setOrganization(ORGANIZATION2);
        capability2.setName(NAME2);

        assertFalse(capability.hashCode() == capability2.hashCode());
    }

}
