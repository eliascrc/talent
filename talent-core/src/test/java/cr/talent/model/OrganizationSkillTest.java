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
 * Class that allows to test the organizationSkill methods to know all the different paths they could take.
 * This class contains the test of the inherited methods
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Josue Cubero
 */
public class OrganizationSkillTest{

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String NAME = "Hive";
    private static final String NAME2 = "HiveQL";

    @Test
    public void coreTest() {

        OrganizationSkillCategory organizationSkillCategory = mock(OrganizationSkillCategory.class);
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Verify Constructor
       OrganizationSkill organizationSkill = new OrganizationSkill();

        // Verify the sets
        organizationSkill.setId(ID);
        organizationSkill.setEntityCreationTimestamp(entityCreationTimestamp);
        organizationSkill.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        organizationSkill.setEntityVersion(entityVersion);
        organizationSkill.setCategory(organizationSkillCategory);
        organizationSkill.setResources(new HashSet<>());
        organizationSkill.setInvitations(new HashSet<>());
        organizationSkill.setCapabilityLevels(new HashSet<>());
        organizationSkill.setName(NAME);

        // Verify the gets
        assertEquals(ID, organizationSkill.getId());
        assertEquals(entityCreationTimestamp, organizationSkill.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, organizationSkill.getLastUpdatedTimestamp());
        assertEquals(entityVersion, organizationSkill.getEntityVersion());
        assertEquals(organizationSkillCategory,organizationSkill.getCategory());
        assertNotNull(organizationSkill.getResources());
        assertNotNull(organizationSkill.getInvitations());
        assertNotNull(organizationSkill.getCapabilityLevels());
        assertEquals(NAME, organizationSkill.getName());
    }

    //ON EQUALS TESTS.

    @Test
    public void testEqualForSameObject() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();

        assertTrue(organizationSkill1.equals(organizationSkill1));
    }

    @Test
    public void testEqualForDifferentClass() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();

        Image image= new Image();

        assertFalse(organizationSkill1.equals(image));
    }

    @Test
    public void testEqualForPersistentOrganizationSkill() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();
        organizationSkill1.setId(ID);

        OrganizationSkill organizationSkill2 = new OrganizationSkill();
        organizationSkill2.setId(ID);

        assertTrue(organizationSkill1.equals(organizationSkill2));
    }

    @Test
    public void testNonEqualForPersistentOrganizationSkill() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();
        organizationSkill1.setId(ID);

        OrganizationSkill organizationSkill2 = new OrganizationSkill();
        organizationSkill2.setId(ID2);

        assertFalse(organizationSkill1.equals(organizationSkill2));
    }

    @Test
    public void testEqualForNonPersistentOrganizationSkill() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();
        organizationSkill1.setName(NAME);

        OrganizationSkill organizationSkill2 = new OrganizationSkill();
        organizationSkill2.setName(NAME);

        assertTrue(organizationSkill1.equals(organizationSkill2));
    }

    @Test
    public void testEqualForNonPersistentOrganizationSkillNullName() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();

        OrganizationSkill organizationSkill2 = new OrganizationSkill();

        assertTrue(organizationSkill1.equals(organizationSkill2));
    }

    @Test
    public void testNonEqualForNonPersistentOrganizationSkill() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();
        organizationSkill1.setName(NAME);

        OrganizationSkill organizationSkill2 = new OrganizationSkill();
        organizationSkill2.setName(NAME2);

        assertFalse(organizationSkill1.equals(organizationSkill2));
    }

    @Test
    public void testNonEqualForNonPersistentOrganizationSkillNullName() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();

        OrganizationSkill organizationSkill2 = new OrganizationSkill();
        organizationSkill2.setName(NAME2);

        assertFalse(organizationSkill1.equals(organizationSkill2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentOrganizationSkill() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();
        organizationSkill1.setId(ID);

        OrganizationSkill organizationSkill2 = new OrganizationSkill();
        organizationSkill2.setId(ID);

        assertTrue(organizationSkill1.hashCode() == organizationSkill2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentOrganizationSkill() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();
        organizationSkill1.setId(ID);

        OrganizationSkill organizationSkill2 = new OrganizationSkill();
        organizationSkill2.setId(ID2);

        assertFalse(organizationSkill1.hashCode() == organizationSkill2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentOrganizationSkill() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();
        organizationSkill1.setName(NAME);

        OrganizationSkill organizationSkill2 = new OrganizationSkill();
        organizationSkill2.setName(NAME);

        assertTrue(organizationSkill1.hashCode() == organizationSkill2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentOrganizationSkill() {
        OrganizationSkill organizationSkill1 = new OrganizationSkill();
        organizationSkill1.setName(NAME);

        OrganizationSkill organizationSkill2 = new OrganizationSkill();
        organizationSkill2.setName(NAME2);

        assertFalse(organizationSkill1.hashCode() == organizationSkill2.hashCode());
    }
}
