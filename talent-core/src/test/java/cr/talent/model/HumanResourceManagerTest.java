package cr.talent.model;
import static cr.talent.model.User.Status.ACTIVE;

import java.util.Date;
import java.util.HashSet;

import static org.mockito.Mockito.mock;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Class that allows to test the HumanResourceManagerTest methods to know all the different paths they could take.
 *
 * @author Fabian Roberto Leandro
 */
public class HumanResourceManagerTest {

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

        // Inheritted from TechnicalResource
        boolean isAdministrator = true;
        Date lastLevelAssessment = new Date();
        Date lastPerformanceReview = new Date();
        Image image = mock(Image.class);
        JobPosition jobPosition = mock(JobPosition.class);
        TechnicalPosition technicalPosition = mock(TechnicalPosition.class);
        CareerPath careerPath = mock(CareerPath.class);
        TechnicalManager technicalManager = mock(TechnicalManager.class);
        Language language = mock(Language.class);
        String timeZone = "CST";
        int levelAssessmentTimeGap = 5;
        OrganizationCapabilityLevel organizationCapabilityLevel = mock(OrganizationCapabilityLevel.class);
        TwoStepVerification twoStepVerification = mock(TwoStepVerification.class);

        // Inheritted from User
        String firstName = "firstName";
		String lastName = "lastName";
        boolean enabled = true;
        boolean passwordNeedsChange = true;
        Date lastLoginTimeStamp = new Date();
        User.Status status = ACTIVE;

        // Verify the constructor
        HumanResourceManager humanResourceManager = new HumanResourceManager();

        // Verify the setters
        // Inheritted from BasicEntity
        humanResourceManager.setId(ID);
        humanResourceManager.setEntityCreationTimestamp(entityCreationTimestamp);
        humanResourceManager.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        humanResourceManager.setEntityVersion(entityVersion);

        // Inheritted from TechnicalResource
        humanResourceManager.setAdministrator(isAdministrator);
		humanResourceManager.setLastLevelAssessment(lastLevelAssessment);
		humanResourceManager.setLastPerformanceReview(lastPerformanceReview);
		humanResourceManager.setProfilePicture(image);
		humanResourceManager.setOrganization(ORGANIZATION);
		humanResourceManager.setEducationRecords(new HashSet());
		humanResourceManager.setProjectPositions(new HashSet());
		humanResourceManager.setJobPosition(jobPosition);
		humanResourceManager.setTechnicalPosition(technicalPosition);
		humanResourceManager.setCareerPath(careerPath);
		humanResourceManager.setTechnicalManager(technicalManager);
		humanResourceManager.setObservations(new HashSet());
		humanResourceManager.setEmergencyContacts(new HashSet());
		humanResourceManager.setLanguage(language);
		humanResourceManager.setTimeZone(timeZone);
		humanResourceManager.setLevelAssessmentTimeGap(levelAssessmentTimeGap);
		humanResourceManager.setOrganizationCapabilityLevel(organizationCapabilityLevel);
		humanResourceManager.setMadeKudo(new HashSet());
		humanResourceManager.setSkills(new HashSet());
		humanResourceManager.setTwoStepVerification(twoStepVerification);

		// Inheritted from User
        humanResourceManager.setUsername(USERNAME) ;
        humanResourceManager.setFirstName(firstName);
        humanResourceManager.setLastName(lastName);
        humanResourceManager.setPassword(PASSWORD);
        humanResourceManager.setEnabled(enabled);
        humanResourceManager.setPasswordNeedsChange(passwordNeedsChange);
        humanResourceManager.setLastLoginTimestamp(lastLoginTimeStamp);
        humanResourceManager.setStatus(status);

        // Declared in HumanResourceManager
        humanResourceManager.setUncheckedEducationRecords(new HashSet());

        // Verify the getters
        assertEquals(ID, humanResourceManager.getId());
        assertEquals(entityCreationTimestamp, humanResourceManager.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, humanResourceManager.getLastUpdatedTimestamp());
        assertEquals(entityVersion, humanResourceManager.getEntityVersion());
        assertEquals(humanResourceManager.isAdministrator(),isAdministrator);
        assertEquals(humanResourceManager.getLastLevelAssessment(),lastLevelAssessment);
        assertEquals(humanResourceManager.getLastPerformanceReview(),lastPerformanceReview);
        assertEquals(humanResourceManager.getProfilePicture(),image);
        assertEquals(humanResourceManager.getOrganization(),ORGANIZATION);
        assertNotNull(humanResourceManager.getEducationRecords());
        assertNotNull(humanResourceManager.getProjectPositions());
        assertEquals(humanResourceManager.getJobPosition(),jobPosition);
        assertEquals(humanResourceManager.getTechnicalPosition(),technicalPosition);
        assertEquals(humanResourceManager.getCareerPath(),careerPath);
        assertEquals(humanResourceManager.getTechnicalManager(),technicalManager);
        assertNotNull(humanResourceManager.getObservations());
        assertNotNull(humanResourceManager.getEmergencyContacts());
        assertEquals(humanResourceManager.getLanguage(),language);
        assertEquals(humanResourceManager.getTimeZone(),timeZone);
        assertEquals(humanResourceManager.getLevelAssessmentTimeGap(),levelAssessmentTimeGap);
        assertEquals(humanResourceManager.getOrganizationCapabilityLevel(),organizationCapabilityLevel);
        assertNotNull(humanResourceManager.getMadeKudo());
        assertNotNull(humanResourceManager.getSkills());
        assertEquals(humanResourceManager.getTwoStepVerification(),twoStepVerification);
        assertEquals(humanResourceManager.getUsername(),USERNAME);
		assertEquals(humanResourceManager.getFirstName(),firstName);
		assertEquals(humanResourceManager.getLastName(),lastName);
		assertEquals(humanResourceManager.getPassword(),PASSWORD);
		assertEquals(humanResourceManager.isEnabled(),enabled);
		assertEquals(humanResourceManager.getPasswordNeedsChange(),passwordNeedsChange);
		assertEquals(humanResourceManager.getLastLoginTimestamp(),lastLoginTimeStamp);
		assertEquals(humanResourceManager.getStatus(),status);

        assertNotNull(humanResourceManager.getUncheckedEducationRecords());
    }

    @Test
    public void testEqualForSameObject() {
        HumanResourceManager humanResourceManager = new HumanResourceManager();
        assertTrue(humanResourceManager.equals(humanResourceManager));
    }

    @Test
    public void testEqualForDifferentClass() {
        HumanResourceManager humanResourceManager = new HumanResourceManager();

        assertFalse(humanResourceManager.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentHumanResourceManager() {
        HumanResourceManager humanResourceManager = new HumanResourceManager();
        humanResourceManager.setId(ID);

        HumanResourceManager humanResourceManager2 = new HumanResourceManager();
        humanResourceManager2.setId(ID);

        assertTrue(humanResourceManager.equals(humanResourceManager2));
    }

    @Test
    public void testNonEqualForPersistentHumanResourceManager() {
        HumanResourceManager humanResourceManager = new HumanResourceManager();
        humanResourceManager.setId(ID);

        HumanResourceManager humanResourceManager2 = new HumanResourceManager();
        humanResourceManager2.setId(ID2);

        assertFalse(humanResourceManager.equals(humanResourceManager2));
    }

    @Test
    public void testEqualForNonPersistentHumanResourceManager() {
        HumanResourceManager humanResourceManager = new HumanResourceManager();
        humanResourceManager.setUsername(USERNAME);
        humanResourceManager.setPassword(PASSWORD);
        humanResourceManager.setOrganization(ORGANIZATION);

        HumanResourceManager humanResourceManager2 = new HumanResourceManager();
        humanResourceManager2.setUsername(USERNAME);
        humanResourceManager2.setPassword(PASSWORD);
        humanResourceManager2.setOrganization(ORGANIZATION);

        assertTrue(humanResourceManager.equals(humanResourceManager2));
    }

    @Test
    public void testNonEqualForNonPersistentHumanResourceManager() {
        HumanResourceManager humanResourceManager = new HumanResourceManager();
        humanResourceManager.setUsername(USERNAME);
        humanResourceManager.setPassword(PASSWORD);
        humanResourceManager.setOrganization(ORGANIZATION);

        HumanResourceManager humanResourceManager2 = new HumanResourceManager();
        humanResourceManager2.setUsername(USERNAME2);
        humanResourceManager2.setPassword(PASSWORD2);
        humanResourceManager2.setOrganization(ORGANIZATION2);

        assertFalse(humanResourceManager.equals(humanResourceManager2));
    }

    @Test
    public void testEqualHashCodeForPersistentHumanResourceManager() {
        HumanResourceManager humanResourceManager = new HumanResourceManager();
        humanResourceManager.setId(ID);

        HumanResourceManager humanResourceManager2 = new HumanResourceManager();
        humanResourceManager2.setId(ID);

        assertTrue(humanResourceManager.hashCode() == humanResourceManager2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentHumanResourceManager() {
        HumanResourceManager humanResourceManager = new HumanResourceManager();
        humanResourceManager.setId(ID);

        HumanResourceManager humanResourceManager2 = new HumanResourceManager();
        humanResourceManager2.setId(ID2);

        assertFalse(humanResourceManager.hashCode() == humanResourceManager2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentHumanResourceManager() {
        HumanResourceManager humanResourceManager = new HumanResourceManager();
        humanResourceManager.setUsername(USERNAME);
        humanResourceManager.setPassword(PASSWORD);
        humanResourceManager.setOrganization(ORGANIZATION);

        HumanResourceManager humanResourceManager2 = new HumanResourceManager();
        humanResourceManager2.setUsername(USERNAME);
        humanResourceManager2.setPassword(PASSWORD);
        humanResourceManager2.setOrganization(ORGANIZATION);

        assertTrue(humanResourceManager.hashCode() == humanResourceManager2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentHumanResourceManager() {
        HumanResourceManager humanResourceManager = new HumanResourceManager();
        humanResourceManager.setUsername(USERNAME);
        humanResourceManager.setPassword(PASSWORD);
        humanResourceManager.setOrganization(ORGANIZATION);

        HumanResourceManager humanResourceManager2 = new HumanResourceManager();
        humanResourceManager2.setUsername(USERNAME2);
        humanResourceManager2.setPassword(PASSWORD2);
        humanResourceManager2.setOrganization(ORGANIZATION2);

        assertFalse(humanResourceManager.hashCode() == humanResourceManager2.hashCode());
    }
}

