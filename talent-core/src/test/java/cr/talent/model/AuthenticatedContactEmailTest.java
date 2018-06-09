package cr.talent.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class AuthenticatedContactEmailTest {
    
    @Test
    public void coreTest () {

        ContactUsIssueType issueType = ContactUsIssueType.ACCOUNT_CLOSING;
        String content = "content";
        String fileName = "fileName";
        String subject = "subject";
        String from = "from";
        String to =  "to";
        TechnicalResource technicalResource = mock(TechnicalResource.class);

        // Verify the constructor
        AuthenticatedContactEmail contactEmail = new AuthenticatedContactEmail();

        // Verify the sets
        contactEmail.setTo(to);
        contactEmail.setIssueType(issueType );
        contactEmail.setContent(content);
        contactEmail.setFileName(fileName);
        contactEmail.setFrom(from);
        contactEmail.setSubject(subject);
        contactEmail.setTechnicalResource(technicalResource);

        // Verify the gets
        assertEquals(to, contactEmail.getTo());
        assertEquals(issueType , contactEmail.getIssueType());
        assertEquals(content, contactEmail.getContent());
        assertEquals(fileName, contactEmail.getFileName());
        assertEquals(from, contactEmail.getFrom());
        assertEquals(subject, contactEmail.getSubject());
        assertEquals(technicalResource, contactEmail.getTechnicalResource());
    }
}
