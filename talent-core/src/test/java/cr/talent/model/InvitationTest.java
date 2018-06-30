package cr.talent.model;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

/**
 * Class that allows to test the invitation methods to know all the different paths they could take.
 * This class contains the test of the inherited methods
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Josue Cubero
 */
public class InvitationTest{

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String TOKEN = "abc123";
    private static final String TOKEN2 = "def456";
    private static final String EMAIL = "jo96cube@gmail.com";

    @Test
    public void coreTest() {

        Organization organization = mock(Organization.class);
        boolean isValid = true;
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        String firstName = "Josue";
        String lastName = "Cubero";

        // Verify Constructor
        Invitation invitation = new Invitation();

        // Verify the sets
        invitation.setId(ID);
        invitation.setEntityCreationTimestamp(entityCreationTimestamp);
        invitation.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        invitation.setEntityVersion(entityVersion);
        invitation.setEmail(EMAIL);
        invitation.setOrganization(organization);
        invitation.setToken(TOKEN);
        invitation.setValid(isValid);
        invitation.setFirstName(firstName);
        invitation.setLastName(lastName);

        // Verify the gets
        assertEquals(ID, invitation.getId());
        assertEquals(entityCreationTimestamp, invitation.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, invitation.getLastUpdatedTimestamp());
        assertEquals(entityVersion, invitation.getEntityVersion());
        assertEquals(EMAIL, invitation.getEmail());
        assertEquals(organization, invitation.getOrganization());
        assertEquals(TOKEN, invitation.getToken());
        assertEquals(isValid, invitation.isValid());
        assertEquals(firstName, invitation.getFirstName());
        assertEquals(lastName, invitation.getLastName());
    }

    //ON EQUALS TESTS.

    @Test
    public void testEqualForSameObject() {
        Invitation invitation1 = new Invitation();

        assertTrue(invitation1.equals(invitation1));
    }

    @Test
    public void testEqualForDifferentClass() {
        Invitation invitation1 = new Invitation();

        Image image= new Image();


        assertFalse(invitation1.equals(image));
    }

    @Test
    public void testEqualForPersistentInvitation() {
        Invitation invitation1 = new Invitation();
        invitation1.setId(ID);

        Invitation invitation2 = new Invitation();
        invitation2.setId(ID);

        assertTrue(invitation1.equals(invitation2));
    }

    @Test
    public void testNonEqualForPersistentInvitation() {
        Invitation invitation1 = new Invitation();
        invitation1.setId(ID);

        Invitation invitation2 = new Invitation();
        invitation2.setId(ID2);

        assertFalse(invitation1.equals(invitation2));
    }

    @Test
    public void testEqualForNonPersistentInvitation() {
        Invitation invitation1 = new Invitation();
        invitation1.setToken(TOKEN);

        Invitation invitation2 = new Invitation();
        invitation2.setToken(TOKEN);

        assertTrue(invitation1.equals(invitation2));
    }

    @Test
    public void testEqualForNonPersistentInvitationNullToken() {
        Invitation invitation1 = new Invitation();

        Invitation invitation2 = new Invitation();

        assertTrue(invitation1.equals(invitation2));
    }

    @Test
    public void testNonEqualForNonPersistentInvitation() {
        Invitation invitation1 = new Invitation();
        invitation1.setToken(TOKEN);

        Invitation invitation2 = new Invitation();
        invitation2.setToken(TOKEN2);

        assertFalse(invitation1.equals(invitation2));
    }

    @Test
    public void testNonEqualForNonPersistentInvitationNullToken() {
        Invitation invitation1 = new Invitation();

        Invitation invitation2 = new Invitation();
        invitation2.setToken(TOKEN2);

        assertFalse(invitation1.equals(invitation2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentInvitation() {
        Invitation invitation1 = new Invitation();
        invitation1.setId(ID);

        Invitation invitation2 = new Invitation();
        invitation2.setId(ID);

        assertTrue(invitation1.hashCode() == invitation2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentInvitation() {
        Invitation invitation1 = new Invitation();
        invitation1.setId(ID);

        Invitation invitation2 = new Invitation();
        invitation2.setId(ID2);

        assertFalse(invitation1.hashCode() == invitation2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentInvitation() {
        Invitation invitation1 = new Invitation();
        invitation1.setToken(TOKEN);

        Invitation invitation2 = new Invitation();
        invitation2.setToken(TOKEN);

        assertTrue(invitation1.hashCode() == invitation2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentInvitation() {
        Invitation invitation1 = new Invitation();
        invitation1.setToken(TOKEN);

        Invitation invitation2 = new Invitation();
        invitation2.setToken(TOKEN2);

        assertFalse(invitation1.hashCode() == invitation2.hashCode());
    }
}
