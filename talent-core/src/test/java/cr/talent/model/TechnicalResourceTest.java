package cr.talent.model;

import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the TechnicalResource methods to know all the different paths they could take.
 *
 * @author Josue Leon Sarkis
 */
public class TechnicalResourceTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String USERNAME = "username";
    private static final String USERNAME2 = "username2";
    private static final String PASSWORD = "password";
    private static final String PASSWORD2 = "password2";
    private static final Organization ORGANIZATION = mock(Organization.class);
    private static final Organization ORGANIZATION2 = mock(Organization.class);

    @Test
    public void coreTest() {

        // Inheritted from BasicEntity
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Inheritted from User
        String firstName = "firstName";
        String lastName = "lastName";
        boolean enabled = true;
        boolean passwordNeedsChange = true;
        Date lastLoginTimeStamp = new Date();

        // Declared in TechnicalResource
        boolean isAdministrator = false;
        Date lastLevelAssessment = new Date();
        Date lastPerformanceReview = new Date();
        Image profilePicture = mock(Image.class);
        JobPosition jobPosition = mock(JobPosition.class);
        TechnicalPosition technicalPosition = mock(TechnicalPosition.class);
        CareerPath careerPath = mock(CareerPath.class);
        TechnicalManager technicalManager = mock(TechnicalManager.class);
        Language language = mock(Language.class);
        String timeZone = "CST";
        int levelAssessmentTimeGap = 5;
        OrganizationCapabilityLevel organizationCapabilityLevel = mock(OrganizationCapabilityLevel.class);
        TwoStepVerification twoStepVerification = mock(TwoStepVerification.class);

        //Verify the constructor
        TechnicalResource technicalResource = new TechnicalResource();

        // Verify the sets
        technicalResource.setId(ID);
        technicalResource.setEntityCreationTimestamp(entityCreationTimestamp);
        technicalResource.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        technicalResource.setEntityVersion(entityVersion);

        technicalResource.setUsername(USERNAME);
        technicalResource.setFirstName(firstName);
        technicalResource.setLastName(lastName);
        technicalResource.setPassword(PASSWORD);
        technicalResource.setEnabled(enabled);
        technicalResource.setPasswordNeedsChange(passwordNeedsChange);
        technicalResource.setLastLoginTimestamp(lastLoginTimeStamp);
        technicalResource.setStatus(User.Status.ACTIVE);

        technicalResource.setAdministrator(isAdministrator);
        technicalResource.setLastLevelAssessment(lastLevelAssessment);
        technicalResource.setLastPerformanceReview(lastPerformanceReview);
        technicalResource.setProfilePicture(profilePicture);
        technicalResource.setOrganization(ORGANIZATION);
        technicalResource.setJobPosition(jobPosition);
        technicalResource.setTechnicalPosition(technicalPosition);
        technicalResource.setCareerPath(careerPath);
        technicalResource.setTechnicalManager(technicalManager);
        technicalResource.setLanguage(language);
        technicalResource.setTimeZone(timeZone);
        technicalResource.setLevelAssessmentTimeGap(levelAssessmentTimeGap);
        technicalResource.setOrganizationCapabilityLevel(organizationCapabilityLevel);
        technicalResource.setTwoStepVerification(twoStepVerification);
        technicalResource.setSkills(new HashSet<>());
        technicalResource.setEducationRecords(new HashSet<>());
        technicalResource.setProjectPositions(new HashSet<>());
        technicalResource.setObservations(new HashSet<>());
        technicalResource.setEmergencyContacts(new HashSet<>());
        technicalResource.setMadeKudo(new HashSet<>());

        // Verify the gets
        assertEquals(ID, technicalResource.getId());
        assertEquals(entityCreationTimestamp, technicalResource.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, technicalResource.getLastUpdatedTimestamp());
        assertEquals(entityVersion, technicalResource.getEntityVersion());

        assertEquals(USERNAME, technicalResource.getUsername());
        assertEquals(firstName, technicalResource.getFirstName());
        assertEquals(lastName, technicalResource.getLastName());
        assertEquals(PASSWORD, technicalResource.getPassword());
        assertEquals(enabled, technicalResource.isEnabled());
        assertEquals(passwordNeedsChange, technicalResource.getPasswordNeedsChange());
        assertEquals(lastLoginTimeStamp, technicalResource.getLastLoginTimestamp());
        assertEquals(User.Status.ACTIVE, technicalResource.getStatus());

        assertEquals(isAdministrator, technicalResource.isAdministrator());
        assertEquals(lastLevelAssessment, technicalResource.getLastLevelAssessment());
        assertEquals(lastPerformanceReview, technicalResource.getLastPerformanceReview());
        assertEquals(profilePicture, technicalResource.getProfilePicture());
        assertEquals(ORGANIZATION, technicalResource.getOrganization());
        assertEquals(jobPosition, technicalResource.getJobPosition());
        assertEquals(technicalPosition, technicalResource.getTechnicalPosition());
        assertEquals(careerPath, technicalResource.getCareerPath());
        assertEquals(technicalManager, technicalResource.getTechnicalManager());
        assertEquals(language, technicalResource.getLanguage());
        assertEquals(timeZone, technicalResource.getTimeZone());
        assertEquals(levelAssessmentTimeGap, technicalResource.getLevelAssessmentTimeGap());
        assertEquals(organizationCapabilityLevel, technicalResource.getOrganizationCapabilityLevel());
        assertEquals(twoStepVerification, technicalResource.getTwoStepVerification());
        assertNotNull(technicalResource.getEducationRecords());
        assertNotNull(technicalResource.getProjectPositions());
        assertNotNull(technicalResource.getObservations());
        assertNotNull(technicalResource.getEmergencyContacts());
        assertNotNull(technicalResource.getMadeKudo());
        assertNotNull(technicalResource.getSkills());

    }

    //ON EQUALS TESTS.
    @Test
    public void testEqualForSameObject() {
        TechnicalResource technicalResource = new TechnicalResource();

        assertTrue(technicalResource.equals(technicalResource));
    }

    @Test
    public void testEqualForDifferentClass() {
        TechnicalResource technicalResource = new TechnicalResource();

        assertFalse(technicalResource.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentTechnicalResource() {
        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setId(ID);

        TechnicalResource technicalResource2 = new TechnicalResource();
        technicalResource2.setId(ID);

        assertTrue(technicalResource.equals(technicalResource2));
    }

    @Test
    public void testNonEqualForPersistentTechnicalResource() {
        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setId(ID);

        TechnicalResource technicalResource2 = new TechnicalResource();
        technicalResource2.setId(ID2);

        assertFalse(technicalResource.equals(technicalResource2));
    }

    @Test
    public void testEqualForNonPersistentTechnicalResource() {
        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setUsername(USERNAME);
        technicalResource.setPassword(PASSWORD);
        technicalResource.setOrganization(ORGANIZATION);

        TechnicalResource technicalResource2 = new TechnicalResource();
        technicalResource2.setUsername(USERNAME);
        technicalResource2.setPassword(PASSWORD);
        technicalResource2.setOrganization(ORGANIZATION);

        assertTrue(technicalResource.equals(technicalResource2));
    }

    @Test
    public void testNonEqualForNonPersistentTechnicalResource() {
        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setUsername(USERNAME);
        technicalResource.setPassword(PASSWORD);
        technicalResource.setOrganization(ORGANIZATION);

        TechnicalResource technicalResource2 = new TechnicalResource();
        technicalResource2.setUsername(USERNAME2);
        technicalResource2.setPassword(PASSWORD2);
        technicalResource2.setOrganization(ORGANIZATION2);

        assertFalse(technicalResource.equals(technicalResource2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentTechnicalResource() {
        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setId(ID);

        TechnicalResource technicalResource2 = new TechnicalResource();
        technicalResource2.setId(ID);

        assertTrue(technicalResource.hashCode() == technicalResource2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentTechnicalResource() {
        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setId(ID);

        TechnicalResource technicalResource2 = new TechnicalResource();
        technicalResource2.setId(ID2);

        assertFalse(technicalResource.hashCode() == technicalResource2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentTechnicalResource() {
        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setUsername(USERNAME);
        technicalResource.setPassword(PASSWORD);
        technicalResource.setOrganization(ORGANIZATION);

        TechnicalResource technicalResource2 = new TechnicalResource();
        technicalResource2.setUsername(USERNAME);
        technicalResource2.setPassword(PASSWORD);
        technicalResource2.setOrganization(ORGANIZATION);

        assertTrue(technicalResource.hashCode() == technicalResource2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentTechnicalResource() {
        TechnicalResource technicalResource = new TechnicalResource();
        technicalResource.setUsername(USERNAME);
        technicalResource.setPassword(PASSWORD);
        technicalResource.setOrganization(ORGANIZATION);

        TechnicalResource technicalResource2 = new TechnicalResource();
        technicalResource2.setUsername(USERNAME2);
        technicalResource2.setPassword(PASSWORD2);
        technicalResource2.setOrganization(ORGANIZATION2);

        assertFalse(technicalResource.hashCode() == technicalResource2.hashCode());
    }
}
