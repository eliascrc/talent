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

}
