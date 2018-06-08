package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class that allows to test the Contact email methods to know all the different paths they could take.
 *
 * @author Elías Calderón
 */
public class ContactEmailTest {

    private static final String EMAIL = "test@test.com";
    private static final String EMAIL2 = "test2@test.com";

    @Test
    public void coreTest () {

        ContactUsIssueType issueType = ContactUsIssueType.ACCOUNT_CLOSING;
        String content = "test";
        String fileName = "test";
        String subject = "test";
        String from = "test";

        // Verify the constructor
        ContactEmail contactEmail = new ContactEmail();

        // Verify the sets
        contactEmail.setTo(EMAIL);
        contactEmail.setIssueType(issueType );
        contactEmail.setContent(content);
        contactEmail.setFileName(fileName);
        contactEmail.setFrom(from);
        contactEmail.setSubject(subject);

        // Verify the gets
        assertEquals(EMAIL, contactEmail.getTo());
        assertEquals(issueType , contactEmail.getIssueType());
        assertEquals(content, contactEmail.getContent());
        assertEquals(fileName, contactEmail.getFileName());
        assertEquals(from, contactEmail.getFrom());
        assertEquals(subject, contactEmail.getSubject());
    }

    @Test
    public void testEqualForSameObject() {
        ContactEmail contactEmail = new ContactEmail();

        assertTrue(contactEmail.equals(contactEmail));
    }

    @Test
    public void testEqualForDifferentObject() {
        ContactEmail contactEmail = new ContactEmail();

        Date date= new Date();

        assertFalse(contactEmail.equals(date));
    }

    @Test
    public void testEqualForNonPersistentContactEmail() {
        ContactEmail contactEmail1 = new ContactEmail();
        contactEmail1.setTo(EMAIL);

        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setTo(EMAIL);

        assertTrue(contactEmail1.equals(contactEmail2));
    }

    @Test
    public void testEqualForNonPersistentContactEmailNullEmail() {
        ContactEmail contactEmail1 = new ContactEmail();

        ContactEmail contactEmail2 = new ContactEmail();

        assertTrue(contactEmail1.equals(contactEmail2));
    }

    @Test
    public void testNonEqualForNonPersistentContactEmail() {
        ContactEmail contactEmail1 = new ContactEmail();
        contactEmail1.setTo(EMAIL);

        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setTo(EMAIL2);

        assertFalse(contactEmail1.equals(contactEmail2));
    }

    @Test
    public void testNonEqualForNonPersistentContactEmailNullEmail() {
        ContactEmail contactEmail1 = new ContactEmail();


        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setTo(EMAIL2);

        assertFalse(contactEmail1.equals(contactEmail2));
    }

    @Test
    public void testEqualHashCodeForNonPersistentContactEmailNullEmail() {
        ContactEmail contactEmail1 = new ContactEmail();


        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setTo(EMAIL);

        assertFalse(contactEmail1.hashCode() == contactEmail2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentContactEmail() {
        ContactEmail contactEmail1 = new ContactEmail();
        contactEmail1.setTo(EMAIL);

        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setTo(EMAIL);

        assertTrue(contactEmail1.hashCode() == contactEmail2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentContactEmail() {
        ContactEmail contactEmail1 = new ContactEmail();
        contactEmail1.setTo(EMAIL);

        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setTo(EMAIL2);

        assertFalse(contactEmail1.hashCode() == contactEmail2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentContactEmailNullEmailNullEmail() {
        ContactEmail contactEmail1 = new ContactEmail();


        ContactEmail contactEmail2 = new ContactEmail();


        assertTrue(contactEmail1.hashCode() == contactEmail2.hashCode());
    }

}
