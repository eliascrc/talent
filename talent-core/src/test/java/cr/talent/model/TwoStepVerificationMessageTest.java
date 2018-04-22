package cr.talent.model;

import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Class that allows to test the TwoStepVerification methods to know all the different paths they could take.
 * @author Daniel Montes de Oca
 */
public class TwoStepVerificationMessageTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String MESSAGE = "message";
    private static final String MESSAGE2 = "message2";

    @Test
    public void coreTest() {
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Verify Constructor
        TwoStepVerificationMessage twoStepVerificationMessage = new TwoStepVerificationMessage();

        // Verify the sets
        twoStepVerificationMessage.setId(ID);
        twoStepVerificationMessage.setEntityCreationTimestamp(entityCreationTimestamp);
        twoStepVerificationMessage.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        twoStepVerificationMessage.setEntityVersion(entityVersion);
        twoStepVerificationMessage.setMessage(MESSAGE);
        twoStepVerificationMessage.setTwoStepVerification(new HashSet<>());

        // Verify the gets
        assertEquals(ID, twoStepVerificationMessage.getId());
        assertEquals(entityCreationTimestamp, twoStepVerificationMessage.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, twoStepVerificationMessage.getLastUpdatedTimestamp());
        assertEquals(entityVersion, twoStepVerificationMessage.getEntityVersion());
        assertEquals(MESSAGE, twoStepVerificationMessage.getMessage());
        assertNotNull(twoStepVerificationMessage.getTwoStepVerification());
    }

    @Test
    public void testEqualForSameObject() {
        TwoStepVerificationMessage twoStepVerificationMessage = new TwoStepVerificationMessage();

        assertTrue(twoStepVerificationMessage.equals(twoStepVerificationMessage));
    }

    @Test
    public void testEqualForDifferentClass() {
        TwoStepVerificationMessage twoStepVerificationMessage = new TwoStepVerificationMessage();

        assertFalse(twoStepVerificationMessage.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentProject() {
        TwoStepVerificationMessage twoStepVerificationMessage = new TwoStepVerificationMessage();
        twoStepVerificationMessage.setId(ID);

        TwoStepVerificationMessage twoStepVerificationMessage2 = new TwoStepVerificationMessage();
        twoStepVerificationMessage.setId(ID);

        assertTrue(twoStepVerificationMessage.equals(twoStepVerificationMessage2));
    }

    @Test
    public void testNonEqualForPersistentProject() {
        TwoStepVerificationMessage twoStepVerificationMessage = new TwoStepVerificationMessage();
        twoStepVerificationMessage.setId(ID);

        TwoStepVerificationMessage twoStepVerificationMessage2 = new TwoStepVerificationMessage();
        twoStepVerificationMessage2.setId(ID2);

        assertFalse(twoStepVerificationMessage.equals(twoStepVerificationMessage2));
    }

    @Test
    public void testEqualForNonPersistentProject() {
        TwoStepVerificationMessage twoStepVerificationMessage = new TwoStepVerificationMessage();
        twoStepVerificationMessage.setMessage(MESSAGE);

        TwoStepVerificationMessage twoStepVerificationMessage2 = new TwoStepVerificationMessage();
        twoStepVerificationMessage2.setMessage(MESSAGE);

        assertTrue(twoStepVerificationMessage.equals(twoStepVerificationMessage2));
    }

    @Test
    public void testNonEqualForNonPersistentProject() {
        TwoStepVerificationMessage twoStepVerificationMessage = new TwoStepVerificationMessage();
        twoStepVerificationMessage.setMessage(MESSAGE);

        TwoStepVerificationMessage twoStepVerificationMessage2 = new TwoStepVerificationMessage();
        twoStepVerificationMessage2.setMessage(MESSAGE2);

        assertFalse(twoStepVerificationMessage.equals(twoStepVerificationMessage2));
    }

    @Test
    public void testEqualHashCodeForPersistentProject() {
        TwoStepVerificationMessage twoStepVerificationMessage = new TwoStepVerificationMessage();
        twoStepVerificationMessage.setId(ID);

        TwoStepVerificationMessage twoStepVerificationMessage2 = new TwoStepVerificationMessage();
        twoStepVerificationMessage2.setId(ID);

        assertTrue(twoStepVerificationMessage.hashCode() == twoStepVerificationMessage2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentProject() {
        TwoStepVerificationMessage twoStepVerificationMessage = new TwoStepVerificationMessage();
        twoStepVerificationMessage.setId(ID);

        TwoStepVerificationMessage twoStepVerificationMessage2 = new TwoStepVerificationMessage();
        twoStepVerificationMessage2.setId(ID2);

        assertFalse(twoStepVerificationMessage.hashCode() == twoStepVerificationMessage.hashCode());
    }


    @Test
    public void testEqualHashCodeForNonPersistentProject() {
        TwoStepVerificationMessage twoStepVerificationMessage = new TwoStepVerificationMessage();
        twoStepVerificationMessage.setMessage(MESSAGE);

        TwoStepVerificationMessage twoStepVerificationMessage2 = new TwoStepVerificationMessage();
        twoStepVerificationMessage2.setMessage(MESSAGE);

        assertTrue(twoStepVerificationMessage.hashCode() == twoStepVerificationMessage2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentProject() {
        TwoStepVerificationMessage twoStepVerificationMessage = new TwoStepVerificationMessage();
        twoStepVerificationMessage.setMessage(MESSAGE);

        TwoStepVerificationMessage twoStepVerificationMessage2 = new TwoStepVerificationMessage();
        twoStepVerificationMessage2.setMessage(MESSAGE2);

        assertFalse(twoStepVerificationMessage.hashCode() == twoStepVerificationMessage2.hashCode());
    }

}
