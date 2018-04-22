package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

/**
 * Class that allows to test the Contact Email methods to know all the different paths they could take.
 * This class contains the test of the inherited methods
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
public class ContactEmailTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";

    @Test
    public void coreTest () {

        // Verify the constructor
        ContactEmail contactEmail = new ContactEmail();
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;
        String email = "test@test.com";
        String subject = "test";
        String content = "test";

        // Verify the sets
        contactEmail.setEmail(email);
        contactEmail.setSubject(subject);
        contactEmail.setContent(content);

        // Verify the gets
        assertEquals(email, contactEmail.getEmail());
        assertEquals(subject, contactEmail.getSubject());
        assertEquals(content, contactEmail.getContent());

    }

}
