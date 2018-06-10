package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the sign up confirmation message methods, to know all the different paths they could take.
 * This class contains the test of the inherited methods and attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Daniel Montes de Oca
 */
public class SignUpConfirmationMessageTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String CONFIRMATION_CODE = "123456";
    private static final String CONFIRMATION_CODE2 = "654321";
    private static final TechnicalResource TECHNICAL_RESOURCE = mock(TechnicalResource.class);
    private static final TechnicalResource TECHNICAL_RESOURCE2 = mock(TechnicalResource.class);

    @Test
    public void coreTest() {

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Verify Constructor
        SignUpConfirmationMessage signUpConfirmationMessage = new SignUpConfirmationMessage();

        // Verify the sets
        signUpConfirmationMessage.setId(ID);
        signUpConfirmationMessage.setEntityCreationTimestamp(entityCreationTimestamp);
        signUpConfirmationMessage.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        signUpConfirmationMessage.setEntityVersion(entityVersion);
        signUpConfirmationMessage.setConfirmationCode(CONFIRMATION_CODE);
        signUpConfirmationMessage.setTechnicalResource(TECHNICAL_RESOURCE);

        // Verify the gets
        assertEquals(ID, signUpConfirmationMessage.getId());
        assertEquals(entityCreationTimestamp, signUpConfirmationMessage.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, signUpConfirmationMessage.getLastUpdatedTimestamp());
        assertEquals(entityVersion, signUpConfirmationMessage.getEntityVersion());
        assertEquals(CONFIRMATION_CODE, signUpConfirmationMessage.getConfirmationCode());
        assertEquals(TECHNICAL_RESOURCE, signUpConfirmationMessage.getTechnicalResource());
    }

    @Test
    public void testEqualForSameObject() {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();

        assertTrue(signUpConfirmationMessage1.equals(signUpConfirmationMessage1));
    }

    @Test
    public void testEqualForDifferentClass() {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();

        Image image= new Image();

        assertFalse(signUpConfirmationMessage1.equals(image));
    }

    @Test
    public void testEqualForPersistentSignUpConfirmationMessage() {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();
        signUpConfirmationMessage1.setId(ID);

        SignUpConfirmationMessage signUpConfirmationMessage2 = new SignUpConfirmationMessage();
        signUpConfirmationMessage2.setId(ID);

        assertTrue(signUpConfirmationMessage1.equals(signUpConfirmationMessage2));
    }

    @Test
    public void testNonEqualForPersistentSignUpConfirmationMessage() {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();
        signUpConfirmationMessage1.setId(ID);

        SignUpConfirmationMessage signUpConfirmationMessage2 = new SignUpConfirmationMessage();
        signUpConfirmationMessage2.setId(ID2);

        assertFalse(signUpConfirmationMessage1.equals(signUpConfirmationMessage2));
    }

    @Test
    public void testEqualForNonPersistentSignUpConfirmationMessage() {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();
        signUpConfirmationMessage1.setTechnicalResource(TECHNICAL_RESOURCE);

        SignUpConfirmationMessage signUpConfirmationMessage2 = new SignUpConfirmationMessage();
        signUpConfirmationMessage2.setTechnicalResource(TECHNICAL_RESOURCE);

        assertTrue(signUpConfirmationMessage1.equals(signUpConfirmationMessage2));
    }

    @Test
    public void testEqualForNonPersistentSignUpConfirmationMessageNullTechnicalResource () {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();

        SignUpConfirmationMessage signUpConfirmationMessage2 = new SignUpConfirmationMessage();

        assertTrue(signUpConfirmationMessage1.equals(signUpConfirmationMessage2));
    }

    @Test
    public void testNonEqualForNonPersistentSignUpConfirmationMessageNullTechnicalResource () {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();

        SignUpConfirmationMessage signUpConfirmationMessage2 = new SignUpConfirmationMessage();
        signUpConfirmationMessage2.setTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(signUpConfirmationMessage1.equals(signUpConfirmationMessage2));
    }

    @Test
    public void testNonEqualForNonPersistentSignUpConfirmationMessage() {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();
        signUpConfirmationMessage1.setTechnicalResource(TECHNICAL_RESOURCE);

        SignUpConfirmationMessage signUpConfirmationMessage2 = new SignUpConfirmationMessage();
        signUpConfirmationMessage2.setTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(signUpConfirmationMessage1.equals(signUpConfirmationMessage2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentSignUpConfirmationMessage() {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();
        signUpConfirmationMessage1.setId(ID);

        SignUpConfirmationMessage signUpConfirmationMessage2 = new SignUpConfirmationMessage();
        signUpConfirmationMessage2.setId(ID);

        assertTrue(signUpConfirmationMessage1.hashCode() == signUpConfirmationMessage2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentSignUpConfirmationMessage() {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();
        signUpConfirmationMessage1.setId(ID);

        SignUpConfirmationMessage signUpConfirmationMessage2 = new SignUpConfirmationMessage();
        signUpConfirmationMessage2.setId(ID2);

        assertFalse(signUpConfirmationMessage1.hashCode() == signUpConfirmationMessage2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentSignUpConfirmationMessage() {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();
        signUpConfirmationMessage1.setTechnicalResource(TECHNICAL_RESOURCE);

        SignUpConfirmationMessage signUpConfirmationMessage2 = new SignUpConfirmationMessage();
        signUpConfirmationMessage2.setTechnicalResource(TECHNICAL_RESOURCE);

        assertTrue(signUpConfirmationMessage1.hashCode() == signUpConfirmationMessage2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentSignUpConfirmationMessage() {
        SignUpConfirmationMessage signUpConfirmationMessage1 = new SignUpConfirmationMessage();
        signUpConfirmationMessage1.setTechnicalResource(TECHNICAL_RESOURCE);

        SignUpConfirmationMessage signUpConfirmationMessage2 = new SignUpConfirmationMessage();
        signUpConfirmationMessage2.setTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(signUpConfirmationMessage1.hashCode() == signUpConfirmationMessage2.hashCode());
    }

}
