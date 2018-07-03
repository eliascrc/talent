package cr.talent.model;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

/**
 * Class that allows to test the password reset request, to know all the different paths they could take.
 * This class contains the test of the inherited methods and attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Maria Jose Cubero
 */
public class PasswordResetRequestTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final TechnicalResource TECHNICAL_RESOURCE1 = mock(TechnicalResource.class);
    private static final TechnicalResource TECHNICAL_RESOURCE2 = mock(TechnicalResource.class);
    private static final String TOKEN1 = "token";
    private static final String TOKEN2 = "token2";

    @Test
    public void coreTest() {
        // Inherited from BasicEntity
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Inherited from User
        String email = "qa.talent.ni@gmail.com";
        boolean isValid = true;

        //Verify the constructor
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();

        //Verify the sets
        passwordResetRequest.setEmail(email);
        passwordResetRequest.setValid(isValid);
        passwordResetRequest.setToken(TOKEN1);
        passwordResetRequest.setTechnicalResource(TECHNICAL_RESOURCE1);
        passwordResetRequest.setEntityCreationTimestamp(entityCreationTimestamp);
        passwordResetRequest.setId(ID);
        passwordResetRequest.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        passwordResetRequest.setEntityVersion(entityVersion);

        //Verify the gets
        assertEquals(ID, passwordResetRequest.getId());
        assertEquals(entityCreationTimestamp, passwordResetRequest.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, passwordResetRequest.getLastUpdatedTimestamp());
        assertEquals(entityVersion, passwordResetRequest.getEntityVersion());

        assertEquals(TECHNICAL_RESOURCE1, passwordResetRequest.getTechnicalResource());
        assertEquals(TOKEN1, passwordResetRequest.getToken());
        assertEquals(isValid, passwordResetRequest.isValid());
        assertEquals(email, passwordResetRequest.getEmail());
    }

    //ON EQUALS TESTS.
    @Test
    public void testEqualForSameObject() {
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();

        assertTrue(passwordResetRequest.equals(passwordResetRequest));
    }

    @Test
    public void testEqualForDifferentClass() {
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();

        assertFalse(passwordResetRequest.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentTechnicalResource() {
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
        passwordResetRequest.setId(ID);

        PasswordResetRequest passwordResetRequest2 = new PasswordResetRequest();
        passwordResetRequest.setId(ID);

        assertTrue(passwordResetRequest.equals(passwordResetRequest2));
    }

    @Test
    public void testNonEqualForPersistentTechnicalResource() {
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
        passwordResetRequest.setId(ID);

        PasswordResetRequest passwordResetRequest2 = new PasswordResetRequest();
        passwordResetRequest2.setId(ID2);

        assertFalse(passwordResetRequest.equals(passwordResetRequest2));
    }

    @Test
    public void testEqualForNonPersistentTechnicalResource() {
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
        passwordResetRequest.setTechnicalResource(TECHNICAL_RESOURCE1);
        passwordResetRequest.setToken(TOKEN1);

        PasswordResetRequest passwordResetRequest2 = new PasswordResetRequest();
        passwordResetRequest2.setTechnicalResource(TECHNICAL_RESOURCE1);
        passwordResetRequest2.setToken(TOKEN1);

        assertTrue(passwordResetRequest.equals(passwordResetRequest2));
    }

    @Test
    public void testNonEqualForNonPersistentTechnicalResource() {
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
        passwordResetRequest.setTechnicalResource(TECHNICAL_RESOURCE1);
        passwordResetRequest.setToken(TOKEN1);

        PasswordResetRequest passwordResetRequest2 = new PasswordResetRequest();
        passwordResetRequest2.setTechnicalResource(TECHNICAL_RESOURCE2);
        passwordResetRequest2.setToken(TOKEN2);

        assertFalse(passwordResetRequest.equals(passwordResetRequest2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentTechnicalResource() {
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
        passwordResetRequest.setId(ID);

        PasswordResetRequest passwordResetRequest2 = new PasswordResetRequest();
        passwordResetRequest2.setId(ID);

        assertTrue(passwordResetRequest.hashCode() == passwordResetRequest2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentTechnicalResource() {
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
        passwordResetRequest.setId(ID);

        PasswordResetRequest passwordResetRequest2 = new PasswordResetRequest();
        passwordResetRequest2.setId(ID2);

        assertFalse(passwordResetRequest.hashCode() == passwordResetRequest2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentTechnicalResource() {
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
        passwordResetRequest.setTechnicalResource(TECHNICAL_RESOURCE1);
        passwordResetRequest.setToken(TOKEN1);

        PasswordResetRequest passwordResetRequest2 = new PasswordResetRequest();
        passwordResetRequest2.setTechnicalResource(TECHNICAL_RESOURCE1);
        passwordResetRequest2.setToken(TOKEN1);

        assertTrue(passwordResetRequest.hashCode() == passwordResetRequest2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentTechnicalResource() {
        PasswordResetRequest passwordResetRequest = new PasswordResetRequest();
        passwordResetRequest.setTechnicalResource(TECHNICAL_RESOURCE1);
        passwordResetRequest.setToken(TOKEN1);

        PasswordResetRequest passwordResetRequest2 = new PasswordResetRequest();
        passwordResetRequest2.setTechnicalResource(TECHNICAL_RESOURCE2);
        passwordResetRequest2.setToken(TOKEN2);

        assertFalse(passwordResetRequest.hashCode() == passwordResetRequest2.hashCode());
    }
}
