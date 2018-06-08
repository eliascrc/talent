package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the UnauthenticatedContactUsNotification methods, to know all the different paths they could take.
 *
 * @author Fabián Roberto Leandro
 */
public class UnauthenticatedContactUsNotificationTest {
    private static final String FIRST_NAME = "first_name";
    private static final String FIRST_NAME2 = "first_name2";
    private static final String LAST_NAME = "last_name";
    private static final String LAST_NAME2 = "last_name2";
    private static final String EMAIL = "email";
    private static final String EMAIL2 = "email2";
    private static final String ISSUE = "issue1";
    private static final String ISSUE2 = "issue2";
    private static final ContactUsIssueType ISSUE_TYPE = ContactUsIssueType.ACCOUNT_CLOSING;
    private static final ContactUsIssueType ISSUE_TYPE2 = ContactUsIssueType.AUTHENTICATION_ISSUES;
    private static final String ID = "1234";
    private static final String ID2 = "12345";
    
    @Test
    public void coreTest() {
        ContactUsIssueType contactUsIssueType = ContactUsIssueType.ACCOUNT_CLOSING;
        Date dateSolved = new Date(); 

        // Test constructor
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();
        
        // Test sets
        contactUsNotification.setId(ID);
        contactUsNotification.setIssueType(contactUsIssueType);
        contactUsNotification.setIssue(ISSUE);
        contactUsNotification.setFirstName(FIRST_NAME);
        contactUsNotification.setLastName(LAST_NAME);
        contactUsNotification.setEmail(EMAIL);
        contactUsNotification.setDateSolved(dateSolved);
        
        // Test gets
        assertEquals(ID,contactUsNotification.getId());
        assertEquals(contactUsIssueType, contactUsNotification.getIssueType());
        assertEquals(ISSUE, contactUsNotification.getIssue());
        assertEquals(FIRST_NAME,contactUsNotification.getFirstName());
        assertEquals(LAST_NAME,contactUsNotification.getLastName());
        assertEquals(EMAIL,contactUsNotification.getEmail());
        assertEquals(dateSolved,contactUsNotification.getDateSolved());
    }
    
    @Test
    public void testEqualForSameObject() {
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();

        assertTrue(contactUsNotification.equals(contactUsNotification));
    }

    @Test
    public void testEqualForDifferentClass() {
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();
        assertFalse(contactUsNotification.equals(new Image()));
    }

    @Test
    public void testEqualForPersistentUnauthenticatedContactUsNotification() {
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();
        contactUsNotification.setId(ID);

        UnauthenticatedContactUsNotification contactUsNotification2 = new UnauthenticatedContactUsNotification();
        contactUsNotification2.setId(ID);

        assertTrue(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testNonEqualForPersistentUnauthenticatedContactUsNotification() {
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();
        contactUsNotification.setId(ID);

        UnauthenticatedContactUsNotification contactUsNotification2 = new UnauthenticatedContactUsNotification();
        contactUsNotification2.setId(ID2);

        assertFalse(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testEqualForNonPersistentUnauthenticatedContactUsNotification() {
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();
        contactUsNotification.setFirstName(FIRST_NAME);
        contactUsNotification.setLastName(LAST_NAME);
        contactUsNotification.setEmail(EMAIL);
        contactUsNotification.setIssueType(ISSUE_TYPE);
        contactUsNotification.setIssue(ISSUE);

        UnauthenticatedContactUsNotification contactUsNotification2 = new UnauthenticatedContactUsNotification();
        contactUsNotification2.setFirstName(FIRST_NAME);
        contactUsNotification2.setLastName(LAST_NAME);
        contactUsNotification2.setEmail(EMAIL2);
        contactUsNotification2.setIssueType(ISSUE_TYPE);
        contactUsNotification2.setIssue(ISSUE);
        
        assertTrue(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testNonEqualForNonPersistentUnauthenticatedContactUsNotification() {
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();
        contactUsNotification.setFirstName(FIRST_NAME);
        contactUsNotification.setLastName(LAST_NAME);
        contactUsNotification.setEmail(EMAIL);
        contactUsNotification.setIssueType(ISSUE_TYPE);
        contactUsNotification.setIssue(ISSUE);

        UnauthenticatedContactUsNotification contactUsNotification2 = new UnauthenticatedContactUsNotification();
        contactUsNotification2.setFirstName(FIRST_NAME2);
        contactUsNotification2.setLastName(LAST_NAME2);
        contactUsNotification2.setEmail(EMAIL2);
        contactUsNotification2.setIssueType(ISSUE_TYPE2);
        contactUsNotification2.setIssue(ISSUE2);

        assertFalse(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testEqualForNonPersistentUnauthenticatedContactUsNotificationNullValues() {
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();

        UnauthenticatedContactUsNotification contactUsNotification2 = new UnauthenticatedContactUsNotification();

        assertTrue(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testEqualHashCodeForPersistentUnauthenticatedContactUsNotification() {
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();
        contactUsNotification.setId(ID);

        UnauthenticatedContactUsNotification contactUsNotification2 = new UnauthenticatedContactUsNotification();
        contactUsNotification2.setId(ID);

        assertTrue(contactUsNotification.hashCode() == contactUsNotification2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentUnauthenticatedContactUsNotification() {
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();
        contactUsNotification.setId(ID);

        UnauthenticatedContactUsNotification contactUsNotification2 = new UnauthenticatedContactUsNotification();
        contactUsNotification2.setId(ID2);

        assertFalse(contactUsNotification.hashCode() == contactUsNotification2.hashCode());
    }



    @Test
    public void testEqualHashCodeForNonPersistentUnauthenticatedContactUsNotification() {

        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();
        contactUsNotification.setFirstName(FIRST_NAME);
        contactUsNotification.setLastName(LAST_NAME);
        contactUsNotification.setEmail(EMAIL);
        contactUsNotification.setIssueType(ISSUE_TYPE);
        contactUsNotification.setIssue(ISSUE);

        UnauthenticatedContactUsNotification contactUsNotification2 = new UnauthenticatedContactUsNotification();
        contactUsNotification2.setFirstName(FIRST_NAME);
        contactUsNotification2.setLastName(LAST_NAME);
        contactUsNotification2.setEmail(EMAIL);
        contactUsNotification2.setIssueType(ISSUE_TYPE);
        contactUsNotification2.setIssue(ISSUE);

        assertTrue(contactUsNotification.hashCode() == contactUsNotification2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentUnauthenticatedContactUsNotification() {
        UnauthenticatedContactUsNotification contactUsNotification = new UnauthenticatedContactUsNotification();
        contactUsNotification.setFirstName(FIRST_NAME);
        contactUsNotification.setLastName(LAST_NAME);
        contactUsNotification.setEmail(EMAIL);
        contactUsNotification.setIssueType(ISSUE_TYPE);
        contactUsNotification.setIssue(ISSUE);

        UnauthenticatedContactUsNotification contactUsNotification2 = new UnauthenticatedContactUsNotification();
        contactUsNotification2.setFirstName(FIRST_NAME2);
        contactUsNotification2.setLastName(LAST_NAME2);
        contactUsNotification2.setEmail(EMAIL2);
        contactUsNotification2.setIssueType(ISSUE_TYPE2);
        contactUsNotification2.setIssue(ISSUE2);

        assertFalse(contactUsNotification.hashCode() == contactUsNotification2.hashCode());
    }
}
