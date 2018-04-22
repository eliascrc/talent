package cr.talent.model;

import java.util.Date;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

/**
 * Class that allows to test the two step verification methods to know all the different paths they could take.
 *
 * @author Otto Mena Kikut
 */
public class TwoStepVerificationTest {

    private static final TechnicalResource RESOURCE1 = mock(TechnicalResource.class);
    private static final TechnicalResource RESOURCE2 = mock(TechnicalResource.class);
    private static final String VERIFICATION_CODE1 = "code1";
    private static final String VERIFICATION_CODE2 = "code2";
    private static final String ID1 = "987";
    private static final String ID2 = "654";

    @Test
    public void coreTest() {
        TwoStepVerification twoStepVerification = new TwoStepVerification();

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        TwoStepVerificationMessage message = mock(TwoStepVerificationMessage.class);

        twoStepVerification.setVerificationCode(VERIFICATION_CODE1);
        twoStepVerification.setMessage(message);
        twoStepVerification.setResource(RESOURCE1);
        twoStepVerification.setMessage(message);
        twoStepVerification.setEntityVersion(entityVersion);
        twoStepVerification.setEntityCreationTimestamp(entityCreationTimestamp);
        twoStepVerification.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        twoStepVerification.setId(ID1);

        assertEquals(RESOURCE1, twoStepVerification.getResource());
        assertEquals(VERIFICATION_CODE1, twoStepVerification.getVerificationCode());
        assertEquals(message, twoStepVerification.getMessage());
        assertEquals(entityCreationTimestamp, twoStepVerification.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, twoStepVerification.getLastUpdatedTimestamp());
        assertEquals(entityVersion, twoStepVerification.getEntityVersion());
        assertEquals(ID1, twoStepVerification.getId());
    }


    @Test
    public void testEqualForSameObject() {
        TwoStepVerification twoStepVerification1 = new TwoStepVerification();

        assertTrue(twoStepVerification1.equals(twoStepVerification1));
    }

    @Test
    public void testEqualForDifferentClass() {
        TwoStepVerification twoStepVerification1 = new TwoStepVerification();

        assertFalse(twoStepVerification1.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentTwoStepVerification() {
        TwoStepVerification twoStepVerification1 = new TwoStepVerification();
        twoStepVerification1.setId(ID1);

        TwoStepVerification twoStepVerification2 = new TwoStepVerification();
        twoStepVerification2.setId(ID1);

        assertTrue(twoStepVerification1.equals(twoStepVerification2));
    }

    @Test
    public void testNonEqualForPersistentTwoStepVerification() {
        TwoStepVerification twoStepVerification1 = new TwoStepVerification();
        twoStepVerification1.setId(ID1);

        TwoStepVerification twoStepVerification2 = new TwoStepVerification();
        twoStepVerification2.setId(ID2);

        assertFalse(twoStepVerification1.equals(twoStepVerification2));
    }

    @Test
    public void testEqualForNonPersistentTwoStepVerification() {
        TwoStepVerification twoStepVerification1 = new TwoStepVerification();
        twoStepVerification1.setResource(RESOURCE1);
        twoStepVerification1.setVerificationCode(VERIFICATION_CODE1);

        TwoStepVerification twoStepVerification2 = new TwoStepVerification();
        twoStepVerification2.setResource(RESOURCE1);
        twoStepVerification2.setVerificationCode(VERIFICATION_CODE1);

        assertTrue(twoStepVerification1.equals(twoStepVerification2));
    }

    @Test
    public void testNonEqualForNonPersistentTwoStepVerification() {
        TwoStepVerification twoStepVerification1 = new TwoStepVerification();
        twoStepVerification1.setResource(RESOURCE1);
        twoStepVerification1.setVerificationCode(VERIFICATION_CODE1);

        TwoStepVerification twoStepVerification2 = new TwoStepVerification();
        twoStepVerification2.setResource(RESOURCE2);
        twoStepVerification2.setVerificationCode(VERIFICATION_CODE2);

        assertFalse(twoStepVerification1.equals(twoStepVerification2));
    }



    @Test
    public void testEqualHashCodeForPersistentTwoStepVerification() {
        TwoStepVerification twoStepVerification1 = new TwoStepVerification();
        twoStepVerification1.setId(ID1);

        TwoStepVerification twoStepVerification2 = new TwoStepVerification();
        twoStepVerification2.setId(ID1);

        assertTrue(twoStepVerification1.hashCode() == twoStepVerification2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentTwoStepVerification() {
        TwoStepVerification twoStepVerification1 = new TwoStepVerification();
        twoStepVerification1.setId(ID1);

        TwoStepVerification twoStepVerification2 = new TwoStepVerification();
        twoStepVerification2.setId(ID2);

        assertFalse(twoStepVerification1.hashCode() == twoStepVerification2.hashCode());
    }



    @Test
    public void testEqualHashCodeForNonPersistentTwoStepVerification() {

        TwoStepVerification twoStepVerification1 = new TwoStepVerification();
        twoStepVerification1.setResource(RESOURCE1);
        twoStepVerification1.setVerificationCode(VERIFICATION_CODE1);

        TwoStepVerification twoStepVerification2 = new TwoStepVerification();
        twoStepVerification2.setResource(RESOURCE1);
        twoStepVerification2.setVerificationCode(VERIFICATION_CODE1);

        assertTrue(twoStepVerification1.hashCode() == twoStepVerification2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentTwoStepVerification() {
        TwoStepVerification twoStepVerification1 = new TwoStepVerification();
        twoStepVerification1.setResource(RESOURCE1);
        twoStepVerification1.setVerificationCode(VERIFICATION_CODE1);

        TwoStepVerification twoStepVerification2 = new TwoStepVerification();
        twoStepVerification2.setResource(RESOURCE2);
        twoStepVerification2.setVerificationCode(VERIFICATION_CODE2);

        assertFalse(twoStepVerification1.hashCode() == twoStepVerification2.hashCode());
    }
}
