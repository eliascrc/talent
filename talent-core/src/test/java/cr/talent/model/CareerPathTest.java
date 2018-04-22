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

    @Test
    public void testEqualForSameObject() {
        CareerPath careerPath = new CareerPath();

        assertTrue(careerPath.equals(careerPath));
    }

    @Test
    public void testEqualForDifferentClass() {
        CareerPath careerPath = new CareerPath();

        assertFalse(careerPath.equals(new Object()));
    }

    @Test
    public void testEqualForPersistentProject() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setId(ID);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setId(ID);

        assertTrue(careerPath1.equals(careerPath2));
    }

    @Test
    public void testNonEqualForPersistentProject() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setId(ID);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setId(ID2);

        assertFalse(careerPath1.equals(careerPath2));
    }

    @Test
    public void testEqualForNonPersistentProject() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setTechnicalResource(TECHNICAL_RESOURCE);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setTechnicalResource(TECHNICAL_RESOURCE);

        assertTrue(careerPath1.equals(careerPath2));
    }

    @Test
    public void testNonEqualForNonPersistentProject() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setTechnicalResource(TECHNICAL_RESOURCE);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(careerPath1.equals(careerPath2));
    }

    //ON HASH TESTS.

    @Test
    public void testEqualHashCodeForPersistentProject() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setId(ID);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setId(ID);

        assertTrue(careerPath1.hashCode() == careerPath2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentProject() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setId(ID);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setId(ID2);

        assertFalse(careerPath1.hashCode() == careerPath2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentProject() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setTechnicalResource(TECHNICAL_RESOURCE);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setTechnicalResource(TECHNICAL_RESOURCE);

        assertTrue(careerPath1.hashCode() == careerPath2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentProject() {
        CareerPath careerPath1 = new CareerPath();
        careerPath1.setTechnicalResource(TECHNICAL_RESOURCE);

        CareerPath careerPath2 = new CareerPath();
        careerPath2.setTechnicalResource(TECHNICAL_RESOURCE2);

        assertFalse(careerPath1.hashCode() == careerPath2.hashCode());
    }

}
