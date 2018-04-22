package cr.talent.model;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;



/**
 * Class that allows to test the organization capability level methods to know all the different paths they could take.
 *
 * @author Otto Mena Kikut
 */
public class OrganizationCapabilityLevelTest {

    private static final String NAME1 = "name1";
    private static final String NAME2 = "name2";
    private static final String ID1 = "987";
    private static final String ID2 = "654";

    @Test
    public void coreTest() {
        OrganizationCapabilityLevel organizationCapabilityLevel = new OrganizationCapabilityLevel();

        int hierarchyPosition = 1;
        OrganizationCapability organizationCapability = mock(OrganizationCapability.class);

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        organizationCapabilityLevel.setName(NAME1);
        organizationCapabilityLevel.setHierarchyPosition(hierarchyPosition);
        organizationCapabilityLevel.setCapability(organizationCapability);
        organizationCapabilityLevel.setOrganizationCapability(organizationCapability);
        organizationCapabilityLevel.setProjectCapability(new HashSet<>());
        organizationCapabilityLevel.setRequiredSkills(new HashSet<>());
        organizationCapabilityLevel.setResources(new HashSet<>());
        organizationCapabilityLevel.setEntityVersion(entityVersion);
        organizationCapabilityLevel.setEntityCreationTimestamp(entityCreationTimestamp);
        organizationCapabilityLevel.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        organizationCapabilityLevel.setId(ID1);

        assertEquals(hierarchyPosition, organizationCapabilityLevel.getHierarchyPosition());
        assertEquals(organizationCapability, organizationCapabilityLevel.getCapability());
        assertEquals(organizationCapability, organizationCapabilityLevel.getOrganizationCapability());
        assertEquals(entityCreationTimestamp, organizationCapabilityLevel.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, organizationCapabilityLevel.getLastUpdatedTimestamp());
        assertEquals(entityVersion, organizationCapabilityLevel.getEntityVersion());
        assertEquals(ID1, organizationCapabilityLevel.getId());
        assertNotNull(organizationCapabilityLevel.getName());
        assertNotNull(organizationCapabilityLevel.getHierarchyPosition());
        assertNotNull(organizationCapabilityLevel.getCapability());
        assertNotNull(organizationCapability);
    }


    @Test
    public void testEqualForSameObject() {
        OrganizationCapabilityLevel organizationCapabilityLevel1 = new OrganizationCapabilityLevel();

        assertTrue(organizationCapabilityLevel1.equals(organizationCapabilityLevel1));
    }

    @Test
    public void testEqualForDifferentClass() {
        OrganizationCapabilityLevel organizationCapabilityLevel1 = new OrganizationCapabilityLevel();

        assertFalse(organizationCapabilityLevel1.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentOrganizationCapabilityLevel() {
        OrganizationCapabilityLevel organizationCapabilityLevel1 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel1.setId(ID1);

        OrganizationCapabilityLevel organizationCapabilityLevel2 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel2.setId(ID1);

        assertTrue(organizationCapabilityLevel1.equals(organizationCapabilityLevel2));
    }

    @Test
    public void testNonEqualForPersistentOrganizationCapabilityLevel() {
        OrganizationCapabilityLevel organizationCapabilityLevel1 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel1.setId(ID1);

        OrganizationCapabilityLevel organizationCapabilityLevel2 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel2.setId(ID2);

        assertFalse(organizationCapabilityLevel1.equals(organizationCapabilityLevel2));
    }

    @Test
    public void testEqualForNonPersistentOrganizationCapabilityLevel() {
        OrganizationCapabilityLevel organizationCapabilityLevel1 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel1.setName(NAME1);

        OrganizationCapabilityLevel organizationCapabilityLevel2 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel2.setName(NAME1);

        assertTrue(organizationCapabilityLevel1.equals(organizationCapabilityLevel2));
    }

    @Test
    public void testNonEqualForNonPersistentOrganizationCapabilityLevel() {
        OrganizationCapabilityLevel organizationCapabilityLevel1 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel1.setName(NAME1);

        OrganizationCapabilityLevel organizationCapabilityLevel2 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel2.setName(NAME2);

        assertFalse(organizationCapabilityLevel1.equals(organizationCapabilityLevel2));
    }



    @Test
    public void testEqualHashCodeForPersistentOrganizationCapabilityLevel() {
        OrganizationCapabilityLevel organizationCapabilityLevel1 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel1.setId(ID1);

        OrganizationCapabilityLevel organizationCapabilityLevel2 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel2.setId(ID1);

        assertTrue(organizationCapabilityLevel1.hashCode() == organizationCapabilityLevel2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentOrganizationCapabilityLevel() {
        OrganizationCapabilityLevel organizationCapabilityLevel1 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel1.setId(ID1);

        OrganizationCapabilityLevel organizationCapabilityLevel2 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel2.setId(ID2);

        assertFalse(organizationCapabilityLevel1.hashCode() == organizationCapabilityLevel2.hashCode());
    }



    @Test
    public void testEqualHashCodeForNonPersistentOrganizationCapabilityLevel() {

        OrganizationCapabilityLevel organizationCapabilityLevel1 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel1.setName(NAME1);

        OrganizationCapabilityLevel organizationCapabilityLevel2 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel2.setName(NAME1);

        assertTrue(organizationCapabilityLevel1.hashCode() == organizationCapabilityLevel2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentOrganizationCapabilityLevel() {
        OrganizationCapabilityLevel organizationCapabilityLevel1 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel1.setName(NAME1);

        OrganizationCapabilityLevel organizationCapabilityLevel2 = new OrganizationCapabilityLevel();
        organizationCapabilityLevel2.setName(NAME2);

        assertFalse(organizationCapabilityLevel1.hashCode() == organizationCapabilityLevel2.hashCode());
    }
}
