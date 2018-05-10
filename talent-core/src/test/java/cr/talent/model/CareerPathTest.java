package cr.talent.model;

import org.junit.Test;

import java.util.Date;
import java.util.HashSet;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Class that allows to test the career path methods, to know all the different paths they could take.
 * This class contains the test of the inherited methods ans attributes
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Maria Jose Cubero
 */
public class CareerPathTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final TechnicalResource TECHNICAL_RESOURCE = mock(TechnicalResource.class);
    private static final TechnicalResource TECHNICAL_RESOURCE2 = mock(TechnicalResource.class);

    @Test
    public void coreTest(){

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Verify Constructor
        CareerPath careerPath = new CareerPath();

        // Verify the sets
        careerPath.setId(ID);
        careerPath.setEntityCreationTimestamp(entityCreationTimestamp);
        careerPath.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        careerPath.setEntityVersion(entityVersion);
        careerPath.setTechnicalResource(TECHNICAL_RESOURCE);
        careerPath.setPositions(new HashSet<>());

        //Verify the gets
        assertEquals(ID, careerPath.getId());
        assertEquals(entityCreationTimestamp, careerPath.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, careerPath.getLastUpdatedTimestamp());
        assertEquals(entityVersion, careerPath.getEntityVersion());
        assertEquals(TECHNICAL_RESOURCE, careerPath.getTechnicalResource());
        assertNotNull(careerPath.getPositions());

    }

    //ON EQUALS TESTS.

    public void testEqualForSameObject() {
        CareerPath careerPath = new CareerPath();

        assertTrue(careerPath.equals(careerPath));
    }

    @Test
    public void testEqualForDifferentObject() {
        CareerPath careerPath = new CareerPath();

        Image image= new Image();

        assertFalse(careerPath.equals(image));
    }


    @Test
    public void testEqualForPersistentCareerPath() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setId(ID);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setId(ID);

        assertTrue(careerPath1.equals(careerPath2));
    }

    @Test
    public void testNonEqualForPersistentCareerPath() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setId(ID);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setId(ID2);

        assertFalse(careerPath1.equals(careerPath2));
    }

    @Test
    public void testEqualForNonPersistentCareerPath() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setTechnicalResource(TECHNICAL_RESOURCE);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setTechnicalResource(TECHNICAL_RESOURCE);

        assertTrue(careerPath1.equals(careerPath2));
    }

    @Test
    public void testEqualForNonPersistentCareerPathNullResource() {
        CareerPath careerPath1 = new CareerPath();

        CareerPath careerPath2 = new CareerPath();

        assertTrue(careerPath1.equals(careerPath2));
    }

    @Test
    public void testNonEqualForNonPersistentCareerPath() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setTechnicalResource(TECHNICAL_RESOURCE);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(careerPath1.equals(careerPath2));
    }

    @Test
    public void testNonEqualForNonPersistentCareerPathNullResource() {
        CareerPath careerPath1 = new CareerPath();

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(careerPath1.equals(careerPath2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentCareerPath() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setId(ID);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setId(ID);

        assertTrue(careerPath1.hashCode() == careerPath2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentCareerPath() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setId(ID);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setId(ID2);

        assertFalse(careerPath1.hashCode() == careerPath2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentCareerPath() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setTechnicalResource(TECHNICAL_RESOURCE);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setTechnicalResource(TECHNICAL_RESOURCE);

        assertTrue(careerPath1.hashCode() == careerPath2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentCareerPath() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setTechnicalResource(TECHNICAL_RESOURCE);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(careerPath1.hashCode() == careerPath2.hashCode());
    }

}
