package cr.talent.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static cr.talent.model.User.Status.ACTIVE;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the SystemAdministratorTest methods to know all the different paths they could take.
 * This class contains the test of the inherited methods ans attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Fabian Roberto Leandro
 */
public class SystemAdministratorTest { 
    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String USERNAME = "username";
    private static final String USERNAME2 = "username2";
    private static final String PASSWORD = "password";
    private static final String PASSWORD2 = "password2";

    @Test
    public void coreTest() {

        // Inherited from BasicEntity
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Inherited from User
        String firstName = "firstName";
		String lastName = "lastName";
        boolean enabled = true;
        boolean passwordNeedsChange = true;
        Date lastLoginTimeStamp = new Date();
        User.Status status = ACTIVE;

        // Verify the constructor
        SystemAdministrator systemAdministrator = new SystemAdministrator();

        // Verify the setters
        // Inherited from BasicEntity
        systemAdministrator.setId(ID);
        systemAdministrator.setEntityCreationTimestamp(entityCreationTimestamp);
        systemAdministrator.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        systemAdministrator.setEntityVersion(entityVersion);

		// Inherited from User
        systemAdministrator.setUsername(USERNAME) ;
        systemAdministrator.setFirstName(firstName);
        systemAdministrator.setLastName(lastName);
        systemAdministrator.setPassword(PASSWORD);
        systemAdministrator.setEnabled(enabled);
        systemAdministrator.setPasswordNeedsChange(passwordNeedsChange);
        systemAdministrator.setLastLoginTimestamp(lastLoginTimeStamp);
        systemAdministrator.setStatus(status);

        // Verify the getters
        assertEquals(ID, systemAdministrator.getId());
        assertEquals(entityCreationTimestamp, systemAdministrator.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, systemAdministrator.getLastUpdatedTimestamp());
        assertEquals(entityVersion, systemAdministrator.getEntityVersion());
        assertEquals(systemAdministrator.getUsername(),USERNAME);
		assertEquals(systemAdministrator.getFirstName(),firstName);
		assertEquals(systemAdministrator.getLastName(),lastName);
		assertEquals(systemAdministrator.getPassword(),PASSWORD);
		assertEquals(systemAdministrator.isEnabled(),enabled);
		assertEquals(systemAdministrator.getPasswordNeedsChange(),passwordNeedsChange);
		assertEquals(systemAdministrator.getLastLoginTimestamp(),lastLoginTimeStamp);
		assertEquals(systemAdministrator.getStatus(),status);
    }
    
    @Test
    public void testEqualForSameObject() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();
        assertTrue(systemAdministrator.equals(systemAdministrator));
    }

    @Test
    public void testEqualForDifferentClass() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();

        assertFalse(systemAdministrator.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentSystemAdministrator() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();
        systemAdministrator.setId(ID);

        SystemAdministrator systemAdministrator2 = new SystemAdministrator();
        systemAdministrator2.setId(ID);

        assertTrue(systemAdministrator.equals(systemAdministrator2));
    }

    @Test
    public void testNonEqualForPersistentSystemAdministrator() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();
        systemAdministrator.setId(ID);

        SystemAdministrator systemAdministrator2 = new SystemAdministrator();
        systemAdministrator2.setId(ID2);

        assertFalse(systemAdministrator.equals(systemAdministrator2));
    }

    @Test
    public void testEqualForNonPersistentSystemAdministrator() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();
        systemAdministrator.setUsername(USERNAME);
        systemAdministrator.setPassword(PASSWORD);

        SystemAdministrator systemAdministrator2 = new SystemAdministrator();
        systemAdministrator2.setUsername(USERNAME);
        systemAdministrator2.setPassword(PASSWORD);

        assertTrue(systemAdministrator.equals(systemAdministrator2));
    }

    @Test
    public void testNonEqualForNonPersistentSystemAdministrator() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();
        systemAdministrator.setUsername(USERNAME);
        systemAdministrator.setPassword(PASSWORD);

        SystemAdministrator systemAdministrator2 = new SystemAdministrator();
        systemAdministrator2.setUsername(USERNAME2);
        systemAdministrator2.setPassword(PASSWORD2);
        Assert.assertFalse(systemAdministrator.equals(systemAdministrator2));
    }

    @Test
    public void testEqualHashCodeForPersistentSystemAdministrator() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();
        systemAdministrator.setId(ID);

        SystemAdministrator systemAdministrator2 = new SystemAdministrator();
        systemAdministrator2.setId(ID);

        assertTrue(systemAdministrator.hashCode() == systemAdministrator2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentSystemAdministrator() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();
        systemAdministrator.setId(ID);

        SystemAdministrator systemAdministrator2 = new SystemAdministrator();
        systemAdministrator2.setId(ID2);

        assertFalse(systemAdministrator.hashCode() == systemAdministrator2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentSystemAdministrator() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();
        systemAdministrator.setUsername(USERNAME);
        systemAdministrator.setPassword(PASSWORD);

        SystemAdministrator systemAdministrator2 = new SystemAdministrator();
        systemAdministrator2.setUsername(USERNAME);
        systemAdministrator2.setPassword(PASSWORD);

        assertTrue(systemAdministrator.hashCode() == systemAdministrator2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentSystemAdministrator() {
        SystemAdministrator systemAdministrator = new SystemAdministrator();
        systemAdministrator.setUsername(USERNAME);
        systemAdministrator.setPassword(PASSWORD);

        SystemAdministrator systemAdministrator2 = new SystemAdministrator();
        systemAdministrator2.setUsername(USERNAME2);
        systemAdministrator2.setPassword(PASSWORD2);

        Assert.assertFalse(systemAdministrator.hashCode() == systemAdministrator2.hashCode());
    }
}
