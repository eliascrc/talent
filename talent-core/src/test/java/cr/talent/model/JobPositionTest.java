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
 * Class that allows to test the jobPosition methods to know all the different paths they could take.
 * This class contains the test of the inherited methods
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Josue Cubero
 */
public class JobPositionTest{

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final OrganizationCapabilityLevel ORGANIZATION_CAPABILITY_LEVEL = mock(OrganizationCapabilityLevel.class);
    private static final OrganizationCapabilityLevel ORGANIZATION_CAPABILITY_LEVEL2 = mock(OrganizationCapabilityLevel.class);

    @Test
    public void coreTest() {

        Invitation invitation = mock(Invitation.class);
        TechnicalResource technicalResource = mock(TechnicalResource.class);
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        Date startDate = new Date();
        Date endDate = new Date();

        // Verify Constructor
        JobPosition jobPosition = new JobPosition();

        // Verify the sets
        jobPosition.setId(ID);
        jobPosition.setEntityCreationTimestamp(entityCreationTimestamp);
        jobPosition.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        jobPosition.setEntityVersion(entityVersion);
        jobPosition.setStartDate(startDate);
        jobPosition.setEndDate(endDate);
        jobPosition.setInvitation(invitation);
        jobPosition.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);
        jobPosition.setTechnicalResource(technicalResource);

        // Verify the gets
        assertEquals(ID, jobPosition.getId());
        assertEquals(entityCreationTimestamp, jobPosition.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, jobPosition.getLastUpdatedTimestamp());
        assertEquals(entityVersion, jobPosition.getEntityVersion());
        assertEquals(startDate, jobPosition.getStartDate());
        assertEquals(endDate, jobPosition.getEndDate());
        assertEquals(invitation, jobPosition.getInvitation());
        assertEquals(ORGANIZATION_CAPABILITY_LEVEL, jobPosition.getOrganizationCapabilityLevel());
        assertEquals(technicalResource, jobPosition.getTechnicalResource());
    }

    //ON EQUALS TESTS.

    @Test
    public void testEqualForSameObject() {
        JobPosition jobPosition1 = new JobPosition();

        assertTrue(jobPosition1.equals(jobPosition1));
    }

    @Test
    public void testEqualForDifferentClass() {
        JobPosition jobPosition1 = new JobPosition();

        Date date = new Date();

        assertFalse(jobPosition1.equals(date));
    }

    @Test
    public void testEqualForPersistentJobPosition() {
        JobPosition jobPosition1 = new JobPosition();
        jobPosition1.setId(ID);

        JobPosition jobPosition2 = new JobPosition();
        jobPosition2.setId(ID);

        assertTrue(jobPosition1.equals(jobPosition2));
    }

    @Test
    public void testNonEqualForPersistentJobPosition() {
        JobPosition jobPosition1 = new JobPosition();
        jobPosition1.setId(ID);

        JobPosition jobPosition2 = new JobPosition();
        jobPosition2.setId(ID2);

        assertFalse(jobPosition1.equals(jobPosition2));
    }

    @Test
    public void testEqualForNonPersistentJobPosition() {
        JobPosition jobPosition1 = new JobPosition();
        jobPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        JobPosition jobPosition2 = new JobPosition();
        jobPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        assertTrue(jobPosition1.equals(jobPosition2));
    }

    @Test
    public void testEqualForNonPersistentJobPositionNullCapabilityLevel() {
        JobPosition jobPosition1 = new JobPosition();

        JobPosition jobPosition2 = new JobPosition();

        assertTrue(jobPosition1.equals(jobPosition2));
    }

    @Test
    public void testNonEqualForNonPersistentJobPosition() {
        JobPosition jobPosition1 = new JobPosition();
        jobPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        JobPosition jobPosition2 = new JobPosition();
        jobPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL2);

        assertFalse(jobPosition1.equals(jobPosition2));
    }

    @Test
    public void testNonEqualForNonPersistentJobPositionNullCapabilityLevel() {
        JobPosition jobPosition1 = new JobPosition();

        JobPosition jobPosition2 = new JobPosition();
        jobPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL2);

        assertFalse(jobPosition1.equals(jobPosition2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentJobPosition() {
        JobPosition jobPosition1 = new JobPosition();
        jobPosition1.setId(ID);

        JobPosition jobPosition2 = new JobPosition();
        jobPosition2.setId(ID);

        assertTrue(jobPosition1.hashCode() == jobPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentJobPosition() {
        JobPosition jobPosition1 = new JobPosition();
        jobPosition1.setId(ID);

        JobPosition jobPosition2 = new JobPosition();
        jobPosition2.setId(ID2);

        assertFalse(jobPosition1.hashCode() == jobPosition2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentJobPosition() {
        JobPosition jobPosition1 = new JobPosition();
        jobPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        JobPosition jobPosition2 = new JobPosition();
        jobPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        assertTrue(jobPosition1.hashCode() == jobPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentJobPosition() {
        JobPosition jobPosition1 = new JobPosition();
        jobPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        JobPosition jobPosition2 = new JobPosition();
        jobPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL2);

        assertFalse(jobPosition1.hashCode() == jobPosition2.hashCode());
    }
}
