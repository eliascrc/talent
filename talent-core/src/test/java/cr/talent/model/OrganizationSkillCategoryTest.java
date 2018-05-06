package cr.talent.model;

import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the Organization skill category methods to know all the different paths they could take.
 * This class contains the test of the inherited methods
 * from {@link cr.talent.model.BasicEntity} and {@link cr.talent.model.SkillCategory} classes.
 *
 * @author Elías Calderón
 */
public class OrganizationSkillCategoryTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String NAME = "Category";
    private static final String NAME2 = "Skill";

    @Test
    public void coreTest () {

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        Organization organization = mock(Organization.class);
        Set<OrganizationSkill> organizationSkills = new HashSet<>();

        // Verify the constructor
        OrganizationSkillCategory organizationSkillCategory = new OrganizationSkillCategory();

        // Verify the sets
        organizationSkillCategory.setId(ID);
        organizationSkillCategory.setEntityCreationTimestamp(entityCreationTimestamp);
        organizationSkillCategory.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        organizationSkillCategory.setEntityVersion(entityVersion);
        organizationSkillCategory.setName(NAME);
        organizationSkillCategory.setOrganization(organization);
        organizationSkillCategory.setOrganizationSkills(organizationSkills);

        // Verify the gets
        assertEquals(ID, organizationSkillCategory.getId());
        assertEquals(entityCreationTimestamp, organizationSkillCategory.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, organizationSkillCategory.getLastUpdatedTimestamp());
        assertEquals(entityVersion, organizationSkillCategory.getEntityVersion());
        assertEquals(NAME, organizationSkillCategory.getName());
        assertEquals(organization, organizationSkillCategory.getOrganization());
        assertEquals(organizationSkills, organizationSkillCategory.getOrganizationSkills());

    }

    //ON EQUALS TESTS.

    @Test
    public void testEqualForSameObject() {
        OrganizationSkillCategory organizationSkillCategory = new OrganizationSkillCategory();

        assertTrue(organizationSkillCategory.equals(organizationSkillCategory));
    }

    @Test
    public void testEqualForDifferentClass() {
        OrganizationSkillCategory organizationSkillCategory = new OrganizationSkillCategory();

        Date date = new Date();

        assertFalse(organizationSkillCategory.equals(date));
    }

    @Test
    public void testEqualForPersistentOrgSkillCategory() {
        OrganizationSkillCategory organizationSkillCategory1 = new OrganizationSkillCategory();
        organizationSkillCategory1.setId(ID);

        OrganizationSkillCategory organizationSkillCategory2 = new OrganizationSkillCategory();
        organizationSkillCategory2.setId(ID);

        assertTrue(organizationSkillCategory1.equals(organizationSkillCategory2));
    }

    @Test
    public void testNonEqualForPersistentOrgSkillCategory() {
        OrganizationSkillCategory organizationSkillCategory1 = new OrganizationSkillCategory();
        organizationSkillCategory1.setId(ID);

        OrganizationSkillCategory organizationSkillCategory2 = new OrganizationSkillCategory();
        organizationSkillCategory2.setId(ID2);

        assertFalse(organizationSkillCategory1.equals(organizationSkillCategory2));
    }

    @Test
    public void testEqualForNonPersistentOrgSkillCategory() {
        OrganizationSkillCategory organizationSkillCategory1 = new OrganizationSkillCategory();
        organizationSkillCategory1.setName(NAME);

        OrganizationSkillCategory organizationSkillCategory2 = new OrganizationSkillCategory();
        organizationSkillCategory2.setName(NAME);

        assertTrue(organizationSkillCategory1.equals(organizationSkillCategory2));
    }

    @Test
    public void testEqualForNonPersistentOrgSkillCategoryNullName() {
        OrganizationSkillCategory organizationSkillCategory1 = new OrganizationSkillCategory();

        OrganizationSkillCategory organizationSkillCategory2 = new OrganizationSkillCategory();

        assertTrue(organizationSkillCategory1.equals(organizationSkillCategory2));
    }

    @Test
    public void testNonEqualForNonPersistentOrgSkillCategory() {
        OrganizationSkillCategory organizationSkillCategory1 = new OrganizationSkillCategory();
        organizationSkillCategory1.setName(NAME);

        OrganizationSkillCategory organizationSkillCategory2 = new OrganizationSkillCategory();
        organizationSkillCategory2.setName(NAME2);

        assertFalse(organizationSkillCategory1.equals(organizationSkillCategory2));
    }

    @Test
    public void testNonEqualForNonPersistentOrgSkillCategoryNullName() {
        OrganizationSkillCategory organizationSkillCategory1 = new OrganizationSkillCategory();

        OrganizationSkillCategory organizationSkillCategory2 = new OrganizationSkillCategory();
        organizationSkillCategory2.setName(NAME2);

        assertFalse(organizationSkillCategory1.equals(organizationSkillCategory2));
    }

    @Test
    public void testEqualHashCodeForPersistentOrgSkillCategory() {
        OrganizationSkillCategory organizationSkillCategory1 = new OrganizationSkillCategory();
        organizationSkillCategory1.setId(ID);

        OrganizationSkillCategory organizationSkillCategory2 = new OrganizationSkillCategory();
        organizationSkillCategory2.setId(ID);

        assertTrue(organizationSkillCategory1.hashCode() == organizationSkillCategory2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentOrgSkillCategory() {
        OrganizationSkillCategory organizationSkillCategory1 = new OrganizationSkillCategory();
        organizationSkillCategory1.setId(ID);

        OrganizationSkillCategory organizationSkillCategory2 = new OrganizationSkillCategory();
        organizationSkillCategory2.setId(ID2);

        assertFalse(organizationSkillCategory1.hashCode() == organizationSkillCategory2.hashCode());
    }

    //el non persistant se hace con los atributos del on equals.

    @Test
    public void testEqualHashCodeForNonPersistentOrgSkillCategory() {
        OrganizationSkillCategory organizationSkillCategory1 = new OrganizationSkillCategory();
        organizationSkillCategory1.setName(NAME);

        OrganizationSkillCategory organizationSkillCategory2 = new OrganizationSkillCategory();
        organizationSkillCategory2.setName(NAME);

        assertTrue(organizationSkillCategory1.hashCode() == organizationSkillCategory2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentOrgSkillCategory() {
        OrganizationSkillCategory organizationSkillCategory1 = new OrganizationSkillCategory();
        organizationSkillCategory1.setName(NAME);

        OrganizationSkillCategory organizationSkillCategory2 = new OrganizationSkillCategory();
        organizationSkillCategory2.setName(NAME2);

        assertFalse(organizationSkillCategory1.hashCode() == organizationSkillCategory2.hashCode());
    }

}
