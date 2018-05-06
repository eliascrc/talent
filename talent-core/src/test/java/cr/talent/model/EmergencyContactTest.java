package cr.talent.model;


import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import java.util.Date;

/**
 * Class that allows to test the emergency contact methods to know all the different paths they could take.
 *
 * @author Otto Mena Kikut
 */
public class EmergencyContactTest {

    private static final TechnicalResource RESOURCE1 = mock(TechnicalResource.class);
    private static final TechnicalResource RESOURCE2 = mock(TechnicalResource.class);
    private static final String EMAIL1 = "generic email1";
    private static final String EMAIL2 = "generic email2";
    private static final String ID1 = "987";
    private static final String ID2 = "654";

    @Test
    public void coreTest() {

        String name = "generic name";
        String telephone = "123";
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        //Verify Constructor
        EmergencyContact emergencyContact = new EmergencyContact();

        //Verify sets
        emergencyContact.setName(name);
        emergencyContact.setTelephone(telephone);
        emergencyContact.setEmail(EMAIL1);
        emergencyContact.setTechnicalResource(RESOURCE1);
        emergencyContact.setEntityVersion(entityVersion);
        emergencyContact.setEntityCreationTimestamp(entityCreationTimestamp);
        emergencyContact.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        emergencyContact.setId(ID1);

        //Verify gets
        assertEquals(name, emergencyContact.getName());
        assertEquals(telephone, emergencyContact.getTelephone());
        assertEquals(EMAIL1, emergencyContact.getEmail());
        assertEquals(RESOURCE1,emergencyContact.getTechnicalResource());
        assertEquals(entityCreationTimestamp, emergencyContact.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, emergencyContact.getLastUpdatedTimestamp());
        assertEquals(entityVersion, emergencyContact.getEntityVersion());
        assertEquals(ID1, emergencyContact.getId());
    }


    @Test
    public void testEqualForSameObject() {
        EmergencyContact emergencyContact1 = new EmergencyContact();

        assertTrue(emergencyContact1.equals(emergencyContact1));
    }

    @Test
    public void testEqualForDifferentClass() {
        EmergencyContact emergencyContact1 = new EmergencyContact();

        Date date = new Date();

        assertFalse(emergencyContact1.equals(date));
    }

    @Test
    public void testEqualForPersistentEmergencyContact() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setId(ID1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact1.setId(ID1);

        assertTrue(emergencyContact1.equals(emergencyContact2));
    }

    @Test
    public void testNonEqualForPersistentEmergencyContact() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setId(ID1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setId(ID2);

        assertFalse(emergencyContact1.equals(emergencyContact2));
    }

    @Test
    public void testEqualForNonPersistentEmergencyContact() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setTechnicalResource(RESOURCE1);
        emergencyContact1.setEmail(EMAIL1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setTechnicalResource(RESOURCE1);
        emergencyContact2.setEmail(EMAIL1);

        assertTrue(emergencyContact1.equals(emergencyContact2));
    }

    @Test
    public void testEqualForNonPersistentEmergencyContactNullResourceNullEmail() {
        EmergencyContact emergencyContact1 = new EmergencyContact();

        EmergencyContact emergencyContact2 = new EmergencyContact();

        assertTrue(emergencyContact1.equals(emergencyContact2));
    }

    @Test
    public void testEqualForNonPersistentEmergencyContactNullResource() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setEmail(EMAIL1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setEmail(EMAIL1);

        assertTrue(emergencyContact1.equals(emergencyContact2));
    }

    @Test
    public void testEqualForNonPersistentEmergencyContactNullEmail() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setTechnicalResource(RESOURCE1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setTechnicalResource(RESOURCE1);

        assertTrue(emergencyContact1.equals(emergencyContact2));
    }

    @Test
    public void testNonEqualForNonPersistentEmergencyContact() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setTechnicalResource(RESOURCE1);
        emergencyContact1.setEmail(EMAIL1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setTechnicalResource(RESOURCE2);
        emergencyContact2.setEmail(EMAIL2);

        assertFalse(emergencyContact1.equals(emergencyContact2));
    }

    @Test
    public void testNonEqualForNonPersistentEmergencyContactNullEmailNullResource() {
        EmergencyContact emergencyContact1 = new EmergencyContact();

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setTechnicalResource(RESOURCE2);
        emergencyContact2.setEmail(EMAIL2);

        assertFalse(emergencyContact1.equals(emergencyContact2));
    }

    @Test
    public void testNonEqualForNonPersistentEmergencyContactNullResource() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setEmail(EMAIL1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setTechnicalResource(RESOURCE2);
        emergencyContact2.setEmail(EMAIL2);

        assertFalse(emergencyContact1.equals(emergencyContact2));
    }

    @Test
    public void testNonEqualForNonPersistentEmergencyContactNullEmail() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setTechnicalResource(RESOURCE1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setTechnicalResource(RESOURCE1);
        emergencyContact2.setEmail(EMAIL2);

        assertFalse(emergencyContact1.equals(emergencyContact2));
    }



    @Test
    public void testEqualHashCodeForPersistentEmergencyContact() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setId(ID1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setId(ID1);

        assertTrue(emergencyContact1.hashCode() == emergencyContact2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentEmergencyContact() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setId(ID1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setId(ID2);

        assertFalse(emergencyContact1.hashCode() == emergencyContact2.hashCode());
    }



    @Test
    public void testEqualHashCodeForNonPersistentEmergencyContact() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setTechnicalResource(RESOURCE1);
        emergencyContact1.setEmail(EMAIL1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setTechnicalResource(RESOURCE1);
        emergencyContact2.setEmail(EMAIL1);

        assertTrue(emergencyContact1.hashCode() == emergencyContact2.hashCode());

    }

    @Test
    public void testNonEqualHashCodeForNonPersistentEmergencyContact() {
        EmergencyContact emergencyContact1 = new EmergencyContact();
        emergencyContact1.setTechnicalResource(RESOURCE1);
        emergencyContact1.setEmail(EMAIL1);

        EmergencyContact emergencyContact2 = new EmergencyContact();
        emergencyContact2.setTechnicalResource(RESOURCE2);
        emergencyContact2.setEmail(EMAIL2);

        assertFalse(emergencyContact1.hashCode() == emergencyContact2.hashCode());
    }
}