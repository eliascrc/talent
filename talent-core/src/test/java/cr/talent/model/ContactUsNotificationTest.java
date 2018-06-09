package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ContactUsNotificationTest {
    /*
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
        ContactUsNotification contactUsNotification = new ContactUsNotification();
        
        // Test sets
        contactUsNotification.setId(ID);
        contactUsNotification.setIssueType(contactUsIssueType);
        contactUsNotification.setIssue(ISSUE);
        contactUsNotification.setDateSolved(dateSolved);
        
        // Test gets
        assertEquals(ID,contactUsNotification.getId());
        assertEquals(contactUsIssueType, contactUsNotification.getIssueType());
        assertEquals(ISSUE, contactUsNotification.getIssue());
        assertEquals(dateSolved,contactUsNotification.getDateSolved());
    }
    
    @Test
    public void testEqualForSameObject() {
        ContactUsNotification contactUsNotification = new ContactUsNotification();

        assertTrue(contactUsNotification.equals(contactUsNotification));
    }

    @Test
    public void testEqualForDifferentClass() {
        ContactUsNotification contactUsNotification = new ContactUsNotification();
        assertFalse(contactUsNotification.equals(new Image()));
    }

    @Test
    public void testEqualForPersistentContactUsNotification() {
        ContactUsNotification contactUsNotification = new ContactUsNotification();
        contactUsNotification.setId(ID);

        ContactUsNotification contactUsNotification2 = new ContactUsNotification();
        contactUsNotification2.setId(ID);

        assertTrue(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testNonEqualForPersistentContactUsNotification() {
        ContactUsNotification contactUsNotification = new ContactUsNotification();
        contactUsNotification.setId(ID);

        ContactUsNotification contactUsNotification2 = new ContactUsNotification();
        contactUsNotification2.setId(ID2);

        assertFalse(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testEqualForNonPersistentContactUsNotification() {
        ContactUsNotification contactUsNotification = new ContactUsNotification();
        contactUsNotification.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification.setIssueType(ISSUE_TYPE);
        contactUsNotification.setIssue(ISSUE);

        ContactUsNotification contactUsNotification2 = new ContactUsNotification();
        contactUsNotification2.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification2.setIssueType(ISSUE_TYPE);
        contactUsNotification2.setIssue(ISSUE);
        
        assertTrue(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testNonEqualForNonPersistentContactUsNotification() {
        ContactUsNotification contactUsNotification = new ContactUsNotification();
        contactUsNotification.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification.setIssueType(ISSUE_TYPE);
        contactUsNotification.setIssue(ISSUE);

        ContactUsNotification contactUsNotification2 = new ContactUsNotification();
        contactUsNotification2.setTechnicalResource(TECHNICAL_RESOURCE2);
        contactUsNotification2.setIssueType(ISSUE_TYPE2);
        contactUsNotification2.setIssue(ISSUE2);

        assertFalse(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testEqualForNonPersistentContactUsNotificationNullValues() {
        ContactUsNotification contactUsNotification = new ContactUsNotification();

        ContactUsNotification contactUsNotification2 = new ContactUsNotification();

        assertTrue(contactUsNotification.equals(contactUsNotification2));
    }

    @Test
    public void testEqualHashCodeForPersistentContactUsNotification() {
        ContactUsNotification contactUsNotification = new ContactUsNotification();
        contactUsNotification.setId(ID);

        ContactUsNotification contactUsNotification2 = new ContactUsNotification();
        contactUsNotification2.setId(ID);

        assertTrue(contactUsNotification.hashCode() == contactUsNotification2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentContactUsNotification() {
        ContactUsNotification contactUsNotification = new ContactUsNotification();
        contactUsNotification.setId(ID);

        ContactUsNotification contactUsNotification2 = new ContactUsNotification();
        contactUsNotification2.setId(ID2);

        assertFalse(contactUsNotification.hashCode() == contactUsNotification2.hashCode());
    }
    
    @Test
    public void testEqualHashCodeForNonPersistentContactUsNotification() {

        ContactUsNotification contactUsNotification = new ContactUsNotification();
        contactUsNotification.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification.setIssueType(ISSUE_TYPE);
        contactUsNotification.setIssue(ISSUE);

        ContactUsNotification contactUsNotification2 = new ContactUsNotification();
        contactUsNotification2.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification2.setIssueType(ISSUE_TYPE);
        contactUsNotification2.setIssue(ISSUE);

        assertTrue(contactUsNotification.hashCode() == contactUsNotification2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentContactUsNotification() {
        ContactUsNotification contactUsNotification = new ContactUsNotification();
        contactUsNotification.setTechnicalResource(TECHNICAL_RESOURCE);
        contactUsNotification.setIssueType(ISSUE_TYPE);
        contactUsNotification.setIssue(ISSUE);

        ContactUsNotification contactUsNotification2 = new ContactUsNotification();
        contactUsNotification2.setTechnicalResource(TECHNICAL_RESOURCE2);
        contactUsNotification2.setIssueType(ISSUE_TYPE2);
        contactUsNotification2.setIssue(ISSUE2);

        assertFalse(contactUsNotification.hashCode() == contactUsNotification2.hashCode());
    }*/
}
