package cr.talent.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class UnauthenticatedContactEmailTest {
    @Test
    public void coreTest () {

        ContactUsIssueType issueType = ContactUsIssueType.ACCOUNT_CLOSING;
        String content = "content";
        String fileName = "fileName";
        String subject = "subject";
        String from = "from";
        String to =  "to";
        String firstName =  "firstName";
        String lastName =  "lastName";

        // Verify the constructor
        UnauthenticatedContactEmail contactEmail = new UnauthenticatedContactEmail();

        // Verify the sets
        contactEmail.setTo(to);
        contactEmail.setIssueType(issueType );
        contactEmail.setContent(content);
        contactEmail.setFileName(fileName);
        contactEmail.setFrom(from);
        contactEmail.setSubject(subject);
        contactEmail.setFirstName(firstName);
        contactEmail.setLastName(lastName);

        // Verify the gets
        assertEquals(to, contactEmail.getTo());
        assertEquals(issueType , contactEmail.getIssueType());
        assertEquals(content, contactEmail.getContent());
        assertEquals(fileName, contactEmail.getFileName());
        assertEquals(from, contactEmail.getFrom());
        assertEquals(subject, contactEmail.getSubject());
        assertEquals(firstName, contactEmail.getFirstName());
        assertEquals(lastName, contactEmail.getLastName());
    }
}
