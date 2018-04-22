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
 * Class that allows to test the technicalPosition methods to know all the different paths they could take.
 *
 * @author Josue Cubero
 */
public class TechnicalPositionTest{

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final OrganizationCapabilityLevel ORGANIZATION_CAPABILITY_LEVEL = mock(OrganizationCapabilityLevel.class);
    private static final OrganizationCapabilityLevel ORGANIZATION_CAPABILITY_LEVEL2 = mock(OrganizationCapabilityLevel.class);

    @Test
    public void coreTest() {

        CareerPath careerPath = mock(CareerPath.class);
        Invitation invitation = mock(Invitation.class);
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        Date startDate = new Date();
        Date endDate = new Date();

        // Verify Constructor
        TechnicalPosition technicalPosition = new TechnicalPosition();

        // Verify the sets
        technicalPosition.setId(ID);
        technicalPosition.setEntityCreationTimestamp(entityCreationTimestamp);
        technicalPosition.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        technicalPosition.setEntityVersion(entityVersion);
        technicalPosition.setCareerPath(careerPath);
        technicalPosition.setInvitation(invitation);
        technicalPosition.setStartDate(startDate);
        technicalPosition.setEndDate(endDate);
        technicalPosition.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        // Verify the gets
        assertEquals(ID, technicalPosition.getId());
        assertEquals(entityCreationTimestamp, technicalPosition.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, technicalPosition.getLastUpdatedTimestamp());
        assertEquals(entityVersion, technicalPosition.getEntityVersion());
        assertEquals(careerPath, technicalPosition.getCareerPath());
        assertEquals(invitation, technicalPosition.getInvitation());
        assertEquals(startDate, technicalPosition.getStartDate());
        assertEquals(endDate, technicalPosition.getEndDate());
        assertEquals(ORGANIZATION_CAPABILITY_LEVEL, technicalPosition.getOrganizationCapabilityLevel());
    }

    //ON EQUALS TESTS.

    @Test
    public void testEqualForSameObject() {
        TechnicalPosition technicalPosition1 = new TechnicalPosition();

        assertTrue(technicalPosition1.equals(technicalPosition1));
    }

    @Test
    public void testEqualForDifferentClass() {
        TechnicalPosition technicalPosition1 = new TechnicalPosition();

        assertFalse(technicalPosition1.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentTechnicalPosition() {
        TechnicalPosition technicalPosition1 = new TechnicalPosition();
        technicalPosition1.setId(ID);

        TechnicalPosition technicalPosition2 = new TechnicalPosition();
        technicalPosition2.setId(ID);

        assertTrue(technicalPosition1.equals(technicalPosition2));
    }

    @Test
    public void testNonEqualForPersistentTechnicalPosition() {
        TechnicalPosition technicalPosition1 = new TechnicalPosition();
        technicalPosition1.setId(ID);

        TechnicalPosition technicalPosition2 = new TechnicalPosition();
        technicalPosition2.setId(ID2);

        assertFalse(technicalPosition1.equals(technicalPosition2));
    }

    @Test
    public void testEqualForNonPersistentTechnicalPosition() {
        TechnicalPosition technicalPosition1 = new TechnicalPosition();
        technicalPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        TechnicalPosition technicalPosition2 = new TechnicalPosition();
        technicalPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        assertTrue(technicalPosition1.equals(technicalPosition2));
    }

    @Test
    public void testNonEqualForNonPersistentTechnicalPosition() {
        TechnicalPosition technicalPosition1 = new TechnicalPosition();
        technicalPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        TechnicalPosition technicalPosition2 = new TechnicalPosition();
        technicalPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL2);

        assertFalse(technicalPosition1.equals(technicalPosition2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentTechnicalPosition() {
        TechnicalPosition technicalPosition1 = new TechnicalPosition();
        technicalPosition1.setId(ID);

        TechnicalPosition technicalPosition2 = new TechnicalPosition();
        technicalPosition2.setId(ID);

        assertTrue(technicalPosition1.hashCode() == technicalPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentTechnicalPosition() {
        TechnicalPosition technicalPosition1 = new TechnicalPosition();
        technicalPosition1.setId(ID);

        TechnicalPosition technicalPosition2 = new TechnicalPosition();
        technicalPosition2.setId(ID2);

        assertFalse(technicalPosition1.hashCode() == technicalPosition2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentTechnicalPosition() {
        TechnicalPosition technicalPosition1 = new TechnicalPosition();
        technicalPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        TechnicalPosition technicalPosition2 = new TechnicalPosition();
        technicalPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        assertTrue(technicalPosition1.hashCode() == technicalPosition2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentTechnicalPosition() {
        TechnicalPosition technicalPosition1 = new TechnicalPosition();
        technicalPosition1.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL);

        TechnicalPosition technicalPosition2 = new TechnicalPosition();
        technicalPosition2.setOrganizationCapabilityLevel(ORGANIZATION_CAPABILITY_LEVEL2);

        assertFalse(technicalPosition1.hashCode() == technicalPosition2.hashCode());
    }
}
