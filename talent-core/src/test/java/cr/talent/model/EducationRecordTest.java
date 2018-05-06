package cr.talent.model;

import org.junit.Test;

import java.io.File;
import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the education record methods to know all the different paths they could take.
 * 
 * @author Daniel Montes de Oca
 */
public class EducationRecordTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String TITLE = "title";
    private static final String TITLE2 = "title2";
    private static final TechnicalResource TECHNICAL_RESOURCE = mock(TechnicalResource.class);
    private static final TechnicalResource TECHNICAL_RESOURCE2 = mock(TechnicalResource.class);

    @Test
    public void coreTest() {

        // Inherited from BasicEntity
        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // From EducationRecord
        String institution = "institution";
        String description = "description";
        Date date = new Date();
        HumanResourceManager humanResourceManager = mock(HumanResourceManager.class);
        File pdfFile = new File("test");

        // Verify the constructor
        EducationRecord educationRecord = new EducationRecord();

        // Verify the setters
        // Inherited from BasicEntity
        educationRecord.setId(ID);
        educationRecord.setEntityCreationTimestamp(entityCreationTimestamp);
        educationRecord.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        educationRecord.setEntityVersion(entityVersion);

        // From EducationRecord
        educationRecord.setTitle(TITLE);
        educationRecord.setInstitution(institution);
        educationRecord.setHumanResourceManager(humanResourceManager);
        educationRecord.setDescription(description);
        educationRecord.setPdfFile(pdfFile);
        educationRecord.setResource(TECHNICAL_RESOURCE);
        educationRecord.setDate(date);

        // Verify the getters
        assertEquals(ID, educationRecord.getId());
        assertEquals(entityCreationTimestamp, educationRecord.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, educationRecord.getLastUpdatedTimestamp());
        assertEquals(entityVersion, educationRecord.getEntityVersion());
        assertEquals(educationRecord.getTitle(), TITLE);
        assertEquals(institution, educationRecord.getInstitution());
        assertEquals(description, educationRecord.getDescription());
        assertEquals(educationRecord.getDate(), date);
        assertEquals(educationRecord.getPdfFile(), pdfFile);
        assertEquals(educationRecord.getHumanResourceManager(), humanResourceManager);
        assertEquals(educationRecord.getResource(), TECHNICAL_RESOURCE);

    }

    @Test
    public void testEqualForSameObject() {
        EducationRecord educationRecord = new EducationRecord();

        assertTrue(educationRecord.equals(educationRecord));
    }

    @Test
    public void testEqualForDifferentClass() {
        EducationRecord educationRecord = new EducationRecord();

        Date date= new Date();

        assertFalse(educationRecord.equals(date));
    }

    @Test
    public void testEqualForPersistentEducationRecord() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setId(ID);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setId(ID);

        assertTrue(educationRecord.equals(educationRecord2));
    }



    @Test
    public void testNonEqualForPersistentEducationRecord() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setId(ID);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setId(ID2);

        assertFalse(educationRecord.equals(educationRecord2));
    }



    @Test
    public void testEqualForNonPersistentEducationRecord() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setTitle(TITLE);
        educationRecord.setResource(TECHNICAL_RESOURCE);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setTitle(TITLE);
        educationRecord2.setResource(TECHNICAL_RESOURCE);

        assertTrue(educationRecord.equals(educationRecord2));
    }

    @Test
    public void testEqualForNonPersistentEducationRecordNullResource() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setTitle(TITLE);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setTitle(TITLE);


        assertTrue(educationRecord.equals(educationRecord2));
    }

    @Test
    public void testEqualForNonPersistentEducationRecordNullTitle() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setResource(TECHNICAL_RESOURCE);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setResource(TECHNICAL_RESOURCE);

        assertTrue(educationRecord.equals(educationRecord2));
    }

    @Test
    public void testEqualForNonPersistentEducationRecordNullTitleNullResource() {
        EducationRecord educationRecord = new EducationRecord();

        EducationRecord educationRecord2 = new EducationRecord();

        assertTrue(educationRecord.equals(educationRecord2));
    }

    @Test
    public void testNonEqualForNonPersistentEducationRecord() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setTitle(TITLE);
        educationRecord.setResource(TECHNICAL_RESOURCE);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setTitle(TITLE2);
        educationRecord2.setResource(TECHNICAL_RESOURCE2);

        assertFalse(educationRecord.equals(educationRecord2));
    }

    @Test
    public void testNonEqualForNonPersistentEducationRecordNullResource() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setTitle(TITLE);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setTitle(TITLE2);
        educationRecord2.setResource(TECHNICAL_RESOURCE2);

        assertFalse(educationRecord.equals(educationRecord2));
    }

    @Test
    public void testNonEqualForNonPersistentEducationRecordNullTitle() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setResource(TECHNICAL_RESOURCE);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setTitle(TITLE2);
        educationRecord2.setResource(TECHNICAL_RESOURCE);

        assertFalse(educationRecord.equals(educationRecord2));
    }

    @Test
    public void testNonEqualForNonPersistentEducationRecordNullTitleNullResource() {
        EducationRecord educationRecord = new EducationRecord();


        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setTitle(TITLE2);
        educationRecord2.setResource(TECHNICAL_RESOURCE2);

        assertFalse(educationRecord.equals(educationRecord2));
    }

    @Test
    public void testEqualHashCodeForPersistentEducationRecord() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setId(ID);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setId(ID);

        assertTrue(educationRecord.hashCode() == educationRecord2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentEducationRecord() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setId(ID);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setId(ID2);

        assertFalse(educationRecord.hashCode() == educationRecord2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentEducationRecord() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setTitle(TITLE);
        educationRecord.setResource(TECHNICAL_RESOURCE);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setTitle(TITLE);
        educationRecord2.setResource(TECHNICAL_RESOURCE);

        assertTrue(educationRecord.hashCode() == educationRecord2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentEducationRecord() {
        EducationRecord educationRecord = new EducationRecord();
        educationRecord.setTitle(TITLE);
        educationRecord.setResource(TECHNICAL_RESOURCE);

        EducationRecord educationRecord2 = new EducationRecord();
        educationRecord2.setTitle(TITLE2);
        educationRecord2.setResource(TECHNICAL_RESOURCE2);

        assertFalse(educationRecord.hashCode() == educationRecord2.hashCode());
    }
}
