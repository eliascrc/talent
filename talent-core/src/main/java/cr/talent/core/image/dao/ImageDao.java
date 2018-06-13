package cr.talent.core.image.dao;

import java.io.InputStream;

/**
 * Data access object for all the {@link cr.talent.model.Image} in aws related operations.
 *
 * @author María José Cubero
 */
public interface ImageDao {

    /**
     * Upload or updates an image in the S3 bucket.
     * key: This is the full path to the file. file: The actual file containing the data to be uploaded.
     * @param key
     * @param file
     * @param folder
     */
    void uploadImage (String key, InputStream file, String folder);

    /**
     * delete image from the bucket.
     * @param key
     * @param folder
     */
    void deleteImage (String key, String folder);

    /**
     * Get an image from the bucket.
     * @param key
     * @param folder
     */
    void getImage (String key, String folder);

}
