package cr.talent.model;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Class that allows to test the email methods, to know all the different paths they could take.
 *
 * @author Maria Jose Cubero
 */
public class EmailTest {

    @Test
    public void coreTest () {

        String to = "qa.talent.ni@gmail.com";
        String from = "qa.talent.ni@gmail.com";
        String subject = "test";
        String content = "test";
        String fileName = "test";

        // Verify the constructor
        Email email = new Email();

        // Verify the sets
        email.setTo(to);
        email.setFrom(from);
        email.setSubject(subject);
        email.setContent(content);
        email.setFileName(fileName);

        // Verify the gets
        assertEquals(to, email.getTo());
        assertEquals(from, email.getFrom());
        assertEquals(subject, email.getSubject());
        assertEquals(content, email.getContent());
        assertEquals(fileName, email.getFileName());

    }
}
