package cr.talent.model;

import org.junit.Test;

import java.util.Date;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Class that allows to test the Image methods to know all the different paths they could take.
 * This class contains the test of the inherited methods
 * from {@link cr.talent.model.BasicEntity} class.
 *
 * @author Elías Calderón
 */
public class ImageTest {

    private static final String ID = "1234";
    private static final String ID2 = "12345";
    private static final String LINK = "http://www.test.com";
    private static final String LINK2 = "http://www.test.cr";

    @Test
    public void coreTest () {

        Date entityCreationTimestamp = new Date();
        Date lastUpdatedTimestamp = new Date();
        long entityVersion = 1l;

        // Verify the constructor
        Image image = new Image();

        // Verify the sets
        image.setId(ID);
        image.setEntityCreationTimestamp(entityCreationTimestamp);
        image.setLastUpdatedTimestamp(lastUpdatedTimestamp);
        image.setEntityVersion(entityVersion);
        image.setLink(LINK);

        // Verify the gets
        assertEquals(ID, image.getId());
        assertEquals(entityCreationTimestamp, image.getEntityCreationTimestamp());
        assertEquals(lastUpdatedTimestamp, image.getLastUpdatedTimestamp());
        assertEquals(entityVersion, image.getEntityVersion());
        assertEquals(LINK, image.getLink());

    }

    @Test
    public void testEqualForSameObject() {
        Image image = new Image();

        assertTrue(image.equals(image));
    }

    @Test
    public void testEqualForDifferentClass() {
        Image image = new Image();

        Date date = new Date();

        assertFalse(image.equals(date));
    }

    @Test
    public void testEqualForPersistentImage() {
        Image image1 = new Image();
        image1.setId(ID);

        Image image2 = new Image();
        image2.setId(ID);

        assertTrue(image1.equals(image2));
    }

    @Test
    public void testNonEqualForPersistentImage() {
        Image image1 = new Image();
        image1.setId(ID);

        Image image2 = new Image();
        image2.setId(ID2);

        assertFalse(image1.equals(image2));
    }

    @Test
    public void testEqualForNonPersistentImage() {
        Image image1 = new Image();
        image1.setLink(LINK);

        Image image2 = new Image();
        image2.setLink(LINK);

        assertTrue(image1.equals(image2));
    }

    @Test
    public void testEqualForNonPersistentImageNullLink() {
        Image image1 = new Image();


        Image image2 = new Image();


        assertTrue(image1.equals(image2));
    }

    @Test
    public void testNonEqualForNonPersistentImage() {
        Image image1 = new Image();
        image1.setLink(LINK);

        Image image2 = new Image();
        image2.setLink(LINK2);

        assertFalse(image1.equals(image2));
    }

    @Test
    public void testNonEqualForNonPersistentImageNullLink() {
        Image image1 = new Image();


        Image image2 = new Image();
        image2.setLink(LINK2);

        assertFalse(image1.equals(image2));
    }

    @Test
    public void testEqualHashCodeForPersistentImage() {
        Image image1 = new Image();
        image1.setId(ID);

        Image image2 = new Image();
        image2.setId(ID);

        assertTrue(image1.hashCode() == image2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForPersistentImage() {
        Image image1 = new Image();
        image1.setId(ID);

        Image image2 = new Image();
        image2.setId(ID2);

        assertFalse(image1.hashCode() == image2.hashCode());
    }

    @Test
    public void testEqualHashCodeForNonPersistentImage() {
        Image image1 = new Image();
        image1.setLink(LINK);

        Image image2 = new Image();
        image2.setLink(LINK);

        assertTrue(image1.hashCode() == image2.hashCode());
    }

    @Test
    public void testNonEqualHashCodeForNonPersistentImage() {
        Image image1 = new Image();
        image1.setLink(LINK);

        Image image2 = new Image();
        image2.setLink(LINK2);

        assertFalse(image1.hashCode() == image2.hashCode());
    }

}
