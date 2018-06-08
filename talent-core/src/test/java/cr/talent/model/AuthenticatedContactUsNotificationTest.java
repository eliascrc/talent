package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the AuthenticatedContactUsNotification methods, to know all the different paths they could take.
 *
 * @author Fabián Roberto Leandro
 */
public class AuthenticatedContactUsNotificationTest {
    
    private static final TechnicalResource TECHNICAL_RESOURCE = mock(TechnicalResource.class);
    private static final TechnicalResource TECHNICAL_RESOURCE2 = mock(TechnicalResource.class);
    private static final String ISSUE = "issue1";
    private static final String ISSUE2 = "issue2";
    private static final String ID = "1234";
    private static final String ID2 = "12345";
    
    @Test
    public void coreTest() {
        ContactUsIssueType contactUsIssueType = ContactUsIssueType.ACCOUNT_CLOSING;
        Date dateSolved = new Date(); 

        // Test constructor
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        
        // Test sets
        contactUsNotification.setId(ID);
        contactUsNotification.setIssueType(contactUsIssueType);
        contactUsNotification.setIssue(ISSUE);
        contactUsNotification.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification.setDateSolved(dateSolved);
        
        // Test gets
        assertEquals(ID,contactUsNotification.getId());
        assertEquals(contactUsIssueType, contactUsNotification.getIssueType());
        assertEquals(ISSUE, contactUsNotification.getIssue());
        assertEquals(TECHNICAL_RESOURCE,contactUsNotification.getTechnicalResource());
        assertEquals(dateSolved,contactUsNotification.getDateSolved());
    }
    
    @Test
    public void testEqualForSameObject() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();

        assertTrue(contactUsNotification.equals(contactUsNotification));
    }

    @Test
    public void testEqualForDifferentClass() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        assertFalse(contactUsNotification.equals(new Image()));
    }

    @Test
    public void testEqualForPersistentAuthenticatedContactUsNotification() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        contactUsNotification.setId(ID);

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();
        contactUsNotification2.setId(ID);

        assertTrue(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testNonEqualForPersistentAuthenticatedContactUsNotification() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        contactUsNotification.setId(ID);

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();
        contactUsNotification2.setId(ID2);

        assertFalse(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testEqualForNonPersistentAuthenticatedContactUsNotification() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        contactUsNotification.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification.setIssue(ISSUE);

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();
        contactUsNotification2.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification2.setIssue(ISSUE);
        
        assertTrue(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testNonEqualForNonPersistentAuthenticatedContactUsNotification() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        contactUsNotification.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification.setIssue(ISSUE);

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();
        contactUsNotification2.setTechnicalResource(TECHNICAL_RESOURCE2);
        contactUsNotification2.setIssue(ISSUE2);

        assertFalse(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testEqualForNonPersistentAuthenticatedContactUsNotificationNullValues() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();

        assertTrue(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testNonEqualForNonPersistentAuthenticatedContactUsNotificationNullTechnicalResource() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();
        contactUsNotification2.setTechnicalResource(TECHNICAL_RESOURCE);

        assertFalse(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testNonEqualForNonPersistentAuthenticatedContactUsNotificationNullIssue() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();
        contactUsNotification2.setIssue(ISSUE);

        assertFalse(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testNonEqualForNonPersistentAuthenticatedContactUsNotificationNullTechnicalResourceFirstAuthenticatedContactUsNotification() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        contactUsNotification.setTechnicalResource(TECHNICAL_RESOURCE);

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();

        assertFalse(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testEqualHashCodeForPersistentAuthenticatedContactUsNotification() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        contactUsNotification.setId(ID);

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();
        contactUsNotification2.setId(ID);

        assertTrue(contactUsNotification.hashCode() == contactUsNotification2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentAuthenticatedContactUsNotification() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        contactUsNotification.setId(ID);

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();
        contactUsNotification2.setId(ID2);

        assertFalse(contactUsNotification.hashCode() == contactUsNotification2.hashCode());
    }



    @Test
    public void testEqualHashCodeForNonPersistentAuthenticatedContactUsNotification() {

        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        contactUsNotification.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification.setIssue(ISSUE);

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();
        contactUsNotification2.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification2.setIssue(ISSUE);

        assertTrue(contactUsNotification.hashCode() == contactUsNotification2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentAuthenticatedContactUsNotification() {
        AuthenticatedContactUsNotification contactUsNotification = new AuthenticatedContactUsNotification();
        contactUsNotification.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification.setIssue(ISSUE);

        AuthenticatedContactUsNotification contactUsNotification2 = new AuthenticatedContactUsNotification();
        contactUsNotification2.setTechnicalResource(TECHNICAL_RESOURCE2);
        contactUsNotification2.setIssue(ISSUE2);

        assertFalse(contactUsNotification.hashCode() == contactUsNotification2.hashCode());
    }
}
