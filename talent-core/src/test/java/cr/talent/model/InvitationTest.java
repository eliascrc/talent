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
 * Class that allows to test the invitation methods to know all the different paths they could take.
 * This class contains the test of the inherited methods
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Josue Cubero
 */
public class InvitationTest{

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String NAME = "talent";
    private static final String NAME2 = "talent2";
    private static final String EMAIL = "jo96cube@gmail.com";

    @Test
    public void coreTest() {

        JobPosition jobPosition = mock(JobPosition.class);
        TechnicalPosition technicalPosition = mock(TechnicalPosition.class);
        Organization organization = mock(Organization.class);
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Verify Constructor
        Invitation invitation = new Invitation();

        // Verify the sets
        invitation.setId(ID);
        invitation.setEntityCreationTimestamp(entityCreationTimestamp);
        invitation.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        invitation.setEntityVersion(entityVersion);
        invitation.setName(NAME);
        invitation.setEmail(EMAIL);
        invitation.setJobPosition(jobPosition);
        invitation.setTechnicalPosition(technicalPosition);
        invitation.setSkills(new HashSet<>());
        invitation.setOrganization(organization);

        // Verify the gets
        assertEquals(ID, invitation.getId());
        assertEquals(entityCreationTimestamp, invitation.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, invitation.getLastUpdatedTimestamp());
        assertEquals(entityVersion, invitation.getEntityVersion());
        assertEquals(NAME, invitation.getName());
        assertEquals(EMAIL, invitation.getEmail());
        assertEquals(jobPosition, invitation.getJobPosition());
        assertEquals(technicalPosition,invitation.getTechnicalPosition());
        assertNotNull(invitation.getSkills());
        assertEquals(organization, invitation.getOrganization());
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

        Date date = new Date();


        assertFalse(invitation1.equals(date));
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
        invitation1.setName(NAME);

        Invitation invitation2 = new Invitation();
        invitation2.setName(NAME);

        assertTrue(invitation1.equals(invitation2));
    }

    @Test
    public void testEqualForNonPersistentInvitationNullName() {
        Invitation invitation1 = new Invitation();

        Invitation invitation2 = new Invitation();

        assertTrue(invitation1.equals(invitation2));
    }

    @Test
    public void testNonEqualForNonPersistentInvitation() {
        Invitation invitation1 = new Invitation();
        invitation1.setName(NAME);

        Invitation invitation2 = new Invitation();
        invitation2.setName(NAME2);

        assertFalse(invitation1.equals(invitation2));
    }

    @Test
    public void testNonEqualForNonPersistentInvitationNullName() {
        Invitation invitation1 = new Invitation();

        Invitation invitation2 = new Invitation();
        invitation2.setName(NAME2);

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
        invitation1.setName(NAME);

        Invitation invitation2 = new Invitation();
        invitation2.setName(NAME);

        assertTrue(invitation1.hashCode() == invitation2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentInvitation() {
        Invitation invitation1 = new Invitation();
        invitation1.setName(NAME);

        Invitation invitation2 = new Invitation();
        invitation2.setName(NAME2);

        assertFalse(invitation1.hashCode() == invitation2.hashCode());
    }
}
