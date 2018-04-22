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
    private static final String STRING_BUILDER_OUTPUT = "[username = %s, enabled = %s, passwordNeedsChange = %s]";

    @Test
    public void coreTest() {

        // Inheritted from BasicEntity
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Inheritted from User
        String username = "username";
        String password = "password";
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

		// Inheritted from User
        humanResourceManager.setUsername(username) ;
        humanResourceManager.setFirstName(firstName);
        humanResourceManager.setLastName(lastName);
        humanResourceManager.setPassword(password);
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

        // Inheritted from User
		assertEquals(humanResourceManager.getFirstName(),firstName);
		assertEquals(humanResourceManager.getLastName(),lastName);
		assertEquals(humanResourceManager.getPassword(),password);
		assertEquals(humanResourceManager.isEnabled(),enabled);
		assertEquals(humanResourceManager.getPasswordNeedsChange(),passwordNeedsChange);
		assertEquals(humanResourceManager.getLastLoginTimestamp(),lastLoginTimeStamp);
		assertEquals(humanResourceManager.getStatus(),status);

		// Declared in HumanResourceManager
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

   /**
   *  Only on this class.
   */
   @Test
   public void testStringBuilder() {
       HumanResourceManager humanResourceManager = new HumanResourceManager();
       humanResourceManager.setUsername("username");

       String expectedOutput = String.format(STRING_BUILDER_OUTPUT, humanResourceManager.getUsername(), humanResourceManager.isEnabled(), humanResourceManager.getPasswordNeedsChange());
       assertEquals(humanResourceManager.toString(), expectedOutput);
   }

}

