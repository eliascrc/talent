package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class that allows to test the Contact Email methods to know all the different paths they could take.
 *
 * @author Elías Calderón
 */
public class ContactEmailTest {

    private static final String EMAIL = "test@test.com";
    private static final String EMAIL2 = "test2@test.com";

    @Test
    public void coreTest () {

        String subject = "test";
        String content = "test";

        // Verify the constructor
        ContactEmail contactEmail = new ContactEmail();

        // Verify the sets
        contactEmail.setEmail(EMAIL);
        contactEmail.setSubject(subject);
        contactEmail.setContent(content);

        // Verify the gets
        assertEquals(EMAIL, contactEmail.getEmail());
        assertEquals(subject, contactEmail.getSubject());
        assertEquals(content, contactEmail.getContent());

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
        contactEmail1.setEmail(EMAIL);

        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setEmail(EMAIL);

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
        contactEmail1.setEmail(EMAIL);

        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setEmail(EMAIL2);

        assertFalse(contactEmail1.equals(contactEmail2));
    }

    @Test
    public void testNonEqualForNonPersistentContactEmailNullEmail() {
        ContactEmail contactEmail1 = new ContactEmail();


        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setEmail(EMAIL2);

        assertFalse(contactEmail1.equals(contactEmail2));
    }

    @Test
    public void testEqualHashCodeForNonPersistentContactEmailNullEmail() {
        ContactEmail contactEmail1 = new ContactEmail();


        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setEmail(EMAIL);

        assertFalse(contactEmail1.hashCode() == contactEmail2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentContactEmail() {
        ContactEmail contactEmail1 = new ContactEmail();
        contactEmail1.setEmail(EMAIL);

        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setEmail(EMAIL);

        assertTrue(contactEmail1.hashCode() == contactEmail2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentContactEmail() {
        ContactEmail contactEmail1 = new ContactEmail();
        contactEmail1.setEmail(EMAIL);

        ContactEmail contactEmail2 = new ContactEmail();
        contactEmail2.setEmail(EMAIL2);

        assertFalse(contactEmail1.hashCode() == contactEmail2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentContactEmailNullEmailNullEmail() {
        ContactEmail contactEmail1 = new ContactEmail();


        ContactEmail contactEmail2 = new ContactEmail();


        assertTrue(contactEmail1.hashCode() == contactEmail2.hashCode());
    }

}
