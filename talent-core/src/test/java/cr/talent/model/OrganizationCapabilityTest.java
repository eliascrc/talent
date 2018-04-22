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
 * Class that allows to test the OrganizationCapabilityTest methods to know all the different paths they could take.
 *
 * @author Fabian Roberto Leandro
 */
public class OrganizationCapabilityTest {
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

        //Inheritted from Capability

        // Declared in OrganizationCapability
        Organization organization = mock(Organization.class);

        // Verify the constructor
        OrganizationCapability organizationCapability = new OrganizationCapability();

        // Verify the setters
        organizationCapability.setId(ID);
        organizationCapability.setEntityCreationTimestamp(entityCreationTimestamp);
        organizationCapability.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        organizationCapability.setEntityVersion(entityVersion);
        organizationCapability.setName(NAME);
        organizationCapability.setLevelHierarchy(new HashSet<>());
        organizationCapability.setOrganization(organization);

        // Verify the getters
        assertEquals(ID, organizationCapability.getId());
        assertEquals(entityCreationTimestamp, organizationCapability.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, organizationCapability.getLastUpdatedTimestamp());
        assertEquals(organizationCapability.getName(),NAME);
        assertNotNull(organizationCapability.getLevelHierarchy());
        assertEquals(organizationCapability.getOrganization(),organization);
    }
    
    @Test
    public void testEqualForSameObject() {
        OrganizationCapability organizationCapability = new OrganizationCapability();

        assertTrue(organizationCapability.equals(organizationCapability));
    }

    @Test
    public void testEqualForDifferentClass() {
        OrganizationCapability organizationCapability = new OrganizationCapability();

        assertFalse(organizationCapability.equals(new Object()));
    }

    // En los persistance se compara el ID.
    @Test
    public void testEqualForPersistentOrganizationCapability() {
        OrganizationCapability organizationCapability = new OrganizationCapability();
        organizationCapability.setId(ID);

        OrganizationCapability organizationCapability2 = new OrganizationCapability();
        organizationCapability2.setId(ID);

        assertTrue(organizationCapability.equals(organizationCapability2));
    }

    @Test
    public void testNonEqualForPersistentOrganizationCapability() {
        OrganizationCapability organizationCapability = new OrganizationCapability();
        organizationCapability.setId(ID);

        OrganizationCapability organizationCapability2 = new OrganizationCapability();
        organizationCapability2.setId(ID2);

        assertFalse(organizationCapability.equals(organizationCapability2));
    }

    @Test
    public void testEqualForNonPersistentOrganizationCapability() {
        OrganizationCapability organizationCapability = new OrganizationCapability();
        organizationCapability.setName(NAME);

        OrganizationCapability organizationCapability2 = new OrganizationCapability();
        organizationCapability2.setName(NAME);

        assertTrue(organizationCapability.equals(organizationCapability2));
    }

    @Test
    public void testNonEqualForNonPersistentOrganizationCapability() {
        OrganizationCapability organizationCapability = new OrganizationCapability();
        organizationCapability.setName(NAME);

        OrganizationCapability organizationCapability2 = new OrganizationCapability();
        organizationCapability2.setName(NAME2);

        assertFalse(organizationCapability.equals(organizationCapability2));
    }

    //ON HASH TESTS.

    //Se hace con el id heredado de basic entity.

    @Test
    public void testEqualHashCodeForPersistentOrganizationCapability() {
        OrganizationCapability organizationCapability = new OrganizationCapability();
        organizationCapability.setId(ID);

        OrganizationCapability organizationCapability2 = new OrganizationCapability();
        organizationCapability2.setId(ID);

        assertTrue(organizationCapability.hashCode() == organizationCapability2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentOrganizationCapability() {
        OrganizationCapability organizationCapability = new OrganizationCapability();
        organizationCapability.setId(ID);

        OrganizationCapability organizationCapability2 = new OrganizationCapability();
        organizationCapability2.setId(ID2);

        assertFalse(organizationCapability.hashCode() == organizationCapability2.hashCode());
    }


     //el non persistant se hace con los atributos del on equals.

    @Test
    public void testEqualHashCodeForNonPersistentOrganizationCapability() {
        OrganizationCapability organizationCapability = new OrganizationCapability();
        organizationCapability.setName(NAME);

        OrganizationCapability organizationCapability2 = new OrganizationCapability();
        organizationCapability2.setName(NAME);

        assertTrue(organizationCapability.hashCode() == organizationCapability2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentOrganizationCapability() {
        OrganizationCapability organizationCapability = new OrganizationCapability();
        organizationCapability.setName(NAME);

        OrganizationCapability organizationCapability2 = new OrganizationCapability();
        organizationCapability2.setName(NAME2);

        assertFalse(organizationCapability.hashCode() == organizationCapability2.hashCode());
    }
}
