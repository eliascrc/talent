package cr.talent.model;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

/**
 * Class that allows to test the PrivacyPolicy methods, to know all the different paths they could take.
 * This class contains the test of the inherited methods and attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Daniel Montes de Oca
 */
public class PrivacyPolicyTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final Date START_DATE1 = mock(Date.class);
    private static final Date START_DATE2 = mock(Date.class);
    private static final Date END_DATE1 = mock(Date.class);
    private static final Date END_DATE2 = mock(Date.class);

    @Test
    public void coreTest() {
        
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        String content = "content";
        long entityVersion = 1l;
        boolean active = true;

        // Verify Constructor
        PrivacyPolicy privacyPolicy = new PrivacyPolicy();

        // Verify the sets
        privacyPolicy.setId(ID);
        privacyPolicy.setEntityCreationTimestamp(entityCreationTimestamp);
        privacyPolicy.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        privacyPolicy.setEntityVersion(entityVersion);
        privacyPolicy.setContent(content);
        privacyPolicy.setStartDate(START_DATE1);
        privacyPolicy.setEndDate(END_DATE1);
        privacyPolicy.setActive(active);

        // Verify the gets
        assertEquals(ID, privacyPolicy.getId());
        assertEquals(entityCreationTimestamp, privacyPolicy.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, privacyPolicy.getLastUpdatedTimestamp());
        assertEquals(entityVersion, privacyPolicy.getEntityVersion());
        assertEquals(content, privacyPolicy.getContent());
        assertEquals(START_DATE1, privacyPolicy.getStartDate());
        assertEquals(END_DATE1, privacyPolicy.getEndDate());
        assertEquals(active, privacyPolicy.isActive());
    }

    @Test
    public void testEqualForSameObject() {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();

        assertTrue(privacyPolicy1.equals(privacyPolicy1));
    }

    @Test
    public void testEqualForDifferentClass() {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();

        assertFalse(privacyPolicy1.equals(new Kudo()));
    }

    @Test
    public void testEqualForPersistentPrivacyPolicy() {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();
        privacyPolicy1.setId(ID);

        PrivacyPolicy privacyPolicy2 = new PrivacyPolicy();
        privacyPolicy2.setId(ID);

        assertTrue(privacyPolicy1.equals(privacyPolicy2));
    }

    @Test
    public void testNonEqualForPersistentPrivacyPolicy() {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();
        privacyPolicy1.setId(ID);

        PrivacyPolicy privacyPolicy2 = new PrivacyPolicy();
        privacyPolicy2.setId(ID2);

        assertFalse(privacyPolicy1.equals(privacyPolicy2));
    }

    @Test
    public void testEqualForNonPersistentPrivacyPolicy() {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();
        privacyPolicy1.setStartDate(START_DATE1);
        privacyPolicy1.setEndDate(END_DATE1);

        PrivacyPolicy privacyPolicy2 = new PrivacyPolicy();
        privacyPolicy2.setStartDate(START_DATE1);
        privacyPolicy2.setEndDate(END_DATE1);

        assertTrue(privacyPolicy1.equals(privacyPolicy2));
    }

    @Test
    public void testEqualForNonPersistentPrivacyPolicyNullStartDate () {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();

        PrivacyPolicy privacyPolicy2 = new PrivacyPolicy();

        assertTrue(privacyPolicy1.equals(privacyPolicy2));
    }

    @Test
    public void testNonEqualForNonPersistentPrivacyPolicyNullStartDate () {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();

        PrivacyPolicy privacyPolicy2 = new PrivacyPolicy();
        privacyPolicy2.setStartDate(new Date());

        assertFalse(privacyPolicy1.equals(privacyPolicy2));
    }

    @Test
    public void testNonEqualForNonPersistentPrivacyPolicy() {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();
        privacyPolicy1.setStartDate(START_DATE1);
        privacyPolicy1.setEndDate(END_DATE1);

        PrivacyPolicy privacyPolicy2 = new PrivacyPolicy();
        privacyPolicy2.setStartDate(START_DATE2);
        privacyPolicy2.setEndDate(END_DATE2);

        assertFalse(privacyPolicy1.equals(privacyPolicy2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentPrivacyPolicy() {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();
        privacyPolicy1.setId(ID);

        PrivacyPolicy privacyPolicy2 = new PrivacyPolicy();
        privacyPolicy2.setId(ID);

        assertTrue(privacyPolicy1.hashCode() == privacyPolicy2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentPrivacyPolicy() {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();
        privacyPolicy1.setId(ID);

        PrivacyPolicy privacyPolicy2 = new PrivacyPolicy();
        privacyPolicy2.setId(ID2);

        assertFalse(privacyPolicy1.hashCode() == privacyPolicy2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentPrivacyPolicy() {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();
        privacyPolicy1.setStartDate(START_DATE1);
        privacyPolicy1.setEndDate(END_DATE1);

        PrivacyPolicy privacyPolicy2 = new PrivacyPolicy();
        privacyPolicy2.setStartDate(START_DATE1);
        privacyPolicy2.setEndDate(END_DATE1);

        assertTrue(privacyPolicy1.hashCode() == privacyPolicy2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentPrivacyPolicy() {
        PrivacyPolicy privacyPolicy1 = new PrivacyPolicy();
        privacyPolicy1.setStartDate(START_DATE1);
        privacyPolicy1.setEndDate(END_DATE1);

        PrivacyPolicy privacyPolicy2 = new PrivacyPolicy();
        privacyPolicy2.setStartDate(START_DATE2);
        privacyPolicy2.setEndDate(END_DATE2);

        assertFalse(privacyPolicy1.hashCode() == privacyPolicy2.hashCode());
    }


}
