package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the TermsOfService methods to know all the different paths they could take.
 *
 * @author Josue Leon Sarkis
 */
public class TermsOfServiceTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final Date START_DATE = mock(Date.class);
    private static final Date START_DATE2 = mock(Date.class);

    @Test
    public void coreTest() {

        // Inherited from BasicEntity
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Declared in TermsOfService
        String ToSContent = "Terms of service";
        Date endDate = new Date();
        boolean isActive = true;

        //Verify the constructor
        TermsOfService termsOfService = new TermsOfService();

        // Verify the sets
        termsOfService.setId(ID);
        termsOfService.setEntityCreationTimestamp(entityCreationTimestamp);
        termsOfService.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        termsOfService.setEntityVersion(entityVersion);
        termsOfService.setToSContent(ToSContent);
        termsOfService.setEndDate(endDate);
        termsOfService.setActive(isActive);
        termsOfService.setStartDate(START_DATE);
        termsOfService.setPlatform(Platform.ANDROID);

        // Verify the gets
        assertEquals(ID, termsOfService.getId());
        assertEquals(entityCreationTimestamp, termsOfService.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, termsOfService.getLastUpdatedTimestamp());
        assertEquals(entityVersion, termsOfService.getEntityVersion());
        assertEquals(ToSContent, termsOfService.getToSContent());
        assertEquals(endDate, termsOfService.getEndDate());
        assertEquals(isActive, termsOfService.isActive());
        assertEquals(START_DATE, termsOfService.getStartDate());
        assertEquals(Platform.ANDROID, termsOfService.getPlatform());
    }

    //ON EQUALS TESTS.
    @Test
    public void testEqualForSameObject() {
        TermsOfService termsOfService1 = new TermsOfService();

        assertTrue(termsOfService1.equals(termsOfService1));
    }

    @Test
    public void testEqualForDifferentClass() {
        TermsOfService termsOfService1 = new TermsOfService();

        assertFalse(termsOfService1.equals(new Image()));
    }

    @Test
    public void testEqualForPersistentTermsOfService() {
        TermsOfService termsOfService1 = new TermsOfService();
        termsOfService1.setId(ID);

        TermsOfService termsOfService2 = new TermsOfService();
        termsOfService2.setId(ID);

        assertTrue(termsOfService1.equals(termsOfService2));
    }

    @Test
    public void testNonEqualForPersistentTermsOfService() {
        TermsOfService termsOfService1 = new TermsOfService();
        termsOfService1.setId(ID);

        TermsOfService termsOfService2 = new TermsOfService();
        termsOfService2.setId(ID2);

        assertFalse(termsOfService1.equals(termsOfService2));
    }

    @Test
    public void testEqualForNonPersistentTermsOfService() {
        TermsOfService termsOfService1 = new TermsOfService();
        termsOfService1.setStartDate(START_DATE);

        TermsOfService termsOfService2 = new TermsOfService();
        termsOfService2.setStartDate(START_DATE);

        assertTrue(termsOfService1.equals(termsOfService2));
    }

    @Test
    public void testNonEqualForNonPersistentTermsOfService() {
        TermsOfService termsOfService1 = new TermsOfService();
        termsOfService1.setStartDate(START_DATE);

        TermsOfService termsOfService2 = new TermsOfService();
        termsOfService2.setStartDate(START_DATE2);

        assertFalse(termsOfService1.equals(termsOfService2));
    }

    @Test
    public void testEqualForNonPersistentTermsOfServiceNullStartDate() {
        TermsOfService termsOfService1 = new TermsOfService();

        TermsOfService termsOfService2 = new TermsOfService();

        assertTrue(termsOfService1.equals(termsOfService2));
    }

    @Test
    public void testNonEqualForNonPersistentTermsOfServiceNullStartDate() {
        TermsOfService termsOfService1 = new TermsOfService();

        TermsOfService termsOfService2 = new TermsOfService();
        termsOfService2.setStartDate(START_DATE);

        assertFalse(termsOfService1.equals(termsOfService2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentTermsOfService() {
        TermsOfService termsOfService1 = new TermsOfService();
        termsOfService1.setId(ID);

        TermsOfService termsOfService2 = new TermsOfService();
        termsOfService2.setId(ID);

        assertTrue(termsOfService1.hashCode() == termsOfService2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentTermsOfService() {
        TermsOfService termsOfService1 = new TermsOfService();
        termsOfService1.setId(ID);

        TermsOfService termsOfService2 = new TermsOfService();
        termsOfService2.setId(ID2);

        assertFalse(termsOfService1.hashCode() == termsOfService2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentTermsOfService() {
        TermsOfService termsOfService1 = new TermsOfService();
        termsOfService1.setStartDate(START_DATE);

        TermsOfService termsOfService2 = new TermsOfService();
        termsOfService2.setStartDate(START_DATE);

        assertTrue(termsOfService1.hashCode() == termsOfService2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentTermsOfService() {
        TermsOfService termsOfService1 = new TermsOfService();
        termsOfService1.setStartDate(START_DATE);

        TermsOfService termsOfService2 = new TermsOfService();
        termsOfService2.setStartDate(START_DATE2);

        assertFalse(termsOfService1.hashCode() == termsOfService2.hashCode());
    }
}
