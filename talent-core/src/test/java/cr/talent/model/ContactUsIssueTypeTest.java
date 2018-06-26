package cr.talent.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Class that allows to test the ContactUsIssueType methods, to know all the different paths they could take.
 *
 * @author Fabián Roberto Leandro
 */
public class ContactUsIssueTypeTest {

    @Test
    public void testFromStringValidIssue() {
        String issueTypeText = "Account closing";

        ContactUsIssueType issueType = ContactUsIssueType.fromString(issueTypeText);

        assertEquals(ContactUsIssueType.ACCOUNT_CLOSING,issueType);
        assertEquals(issueTypeText,issueType.getIssueText());
    }

    @Test
    public void testFromStringInalidIssue() {
        String issueTypeText = "invalid";

        ContactUsIssueType issueType = ContactUsIssueType.fromString(issueTypeText);

        assertEquals(issueType,null);
    }
}
